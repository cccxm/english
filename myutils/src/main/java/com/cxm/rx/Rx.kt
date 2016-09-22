package com.cxm.rx

import android.os.Handler
import com.cxm.utils.RandomUtils
import com.cxm.utils.ThreadUtils
import java.util.*
import java.util.concurrent.Executors

/**
 * beta rx
 * 陈小默 16/9/19.
 */
class Rx {

    class Observable<T> {
        companion object {
            private val pool = Executors.newCachedThreadPool()
        }

        private var from: List<T>? = null
        private var isLoop = false
        private var timer: (Int) -> Long = { 0L }
        private var observable: ((Int) -> T?)? = null
        private var subscriber: Subscriber<T>? = null
        private var filterList: MutableList<((T?) -> Boolean)>? = null
        private var counter = 0
        private var subscribeOn = Schedule.MAIN
        private var observableOn = Schedule.MAIN
        private var thread = RandomUtils.getString()
        private var isStart = false
        private val handler = Handler()

        fun from(vararg array: T): Observable<T> {
            val result = ArrayList<T>()
            for (a in array) {
                result.add(a)
            }
            from = result
            return this
        }

        fun loop(): Observable<T> = loop(0)

        fun loop(time: Long): Observable<T> {
            if (time >= 0) {
                return loop { time }
            }
            throw IllegalArgumentException("time $time don't less than 0")
        }

        fun loop(timer: (Int) -> Long): Observable<T> {
            this.timer = timer
            isLoop = true
            observableOn = Schedule.THREAD
            return this
        }

        fun subscribeOn(schedule: Schedule): Observable<T> {
            subscribeOn = schedule
            return this
        }

        fun observableOn(schedule: Schedule): Observable<T> {
            observableOn = schedule
            return this
        }

        fun observable(observable: ((Int) -> T?)): Observable<T> {
            this.observable = observable
            return this
        }

        fun filterNotNull(): Observable<T> {
            return filter { res -> res != null }
        }

        fun filter(filter: ((T?) -> Boolean)): Observable<T> {
            if (filterList == null) filterList = LinkedList<((T?) -> Boolean)>()
            filterList!!.add(filter)
            return this
        }

        fun subscribe(subscribe: ((T?) -> Unit)): Observable<T> {
            subscribe(object : Subscriber<T> {
                override fun onNext(next: T?) {
                    subscribe.invoke(next)
                }

                override fun onComplete() {
                }

                override fun onError(e: Throwable) {
                    throw e
                }
            })
            return this
        }

        fun subscribe(subscriber: Subscriber<T>): Observable<T> {
            this.subscriber = subscriber
            return this
        }

        fun start() {
            if (isStart) throw RuntimeException("already start")
            isStart = true
            if (isLoop) {
                when (observableOn) {
                    Schedule.IO -> {
                        pool.execute {
                            observable?.invoke(counter++)
                        }
                    }
                    Schedule.THREAD -> {
                        startThread()
                    }
                    Schedule.MAIN -> {
                        observable?.invoke(counter++)
                    }
                }
            }
        }

        private fun startThread() {
            ThreadUtils.build(thread, {
                while (ThreadUtils.isAlive(thread)) {
                    Thread.sleep(timer.invoke(counter))
                    try {
                        val res = observable!!.invoke(counter++)
                        result(res)
                    } catch (e: Throwable) {
                        subscriber!!.onError(e)
                    }
                }
            })
            ThreadUtils.start(thread)
        }

        private fun result(res: T?) {
            if (!filter(res)) return
            when (subscribeOn) {
                Schedule.IO, Schedule.THREAD -> {
                    subscriber!!.onNext(res)
                }
                Schedule.MAIN -> {
                    handler.post { subscriber!!.onNext(res) }
                }
            }
        }

        private fun filter(res: T?): Boolean {
            filterList ?: return true
            for (filter in filterList!!) {
                if (!filter.invoke(res)) return false
            }
            return true
        }

        fun stop() {
            ThreadUtils.stop(thread)
        }
    }
}

interface Subscriber<T> {
    fun onNext(next: T?)
    fun onComplete()
    fun onError(e: Throwable)
}

enum class Schedule {
    IO, THREAD, MAIN
}
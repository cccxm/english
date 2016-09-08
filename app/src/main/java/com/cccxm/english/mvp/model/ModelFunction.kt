package com.cccxm.english.mvp.model

import com.cxm.lib.rx.DBSubscriber
import com.cxm.lib.rx.NetSubscriber
import org.greenrobot.greendao.query.QueryBuilder
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * 菩提本无树
 * 明镜亦非台
 * 本来无一物
 * 何处惹尘埃
 * 陈小默 16/9/1.
 */
fun <T> Observable<T>.netCallback(cb: (T) -> Unit) {
    this.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : NetSubscriber<T>() {
                override fun onNext(t: T) {
                    cb.invoke(t)
                }
            })
}

fun <T> QueryBuilder<T>.query(cb: (MutableList<T>) -> Unit) {
    Observable.just(this)
            .map { builder ->
                builder.list()
            }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DBSubscriber<MutableList<T>>() {
                override fun onNext(t: MutableList<T>) {
                    cb.invoke(t)
                }
            })
}
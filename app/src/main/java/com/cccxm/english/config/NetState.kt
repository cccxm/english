package com.cccxm.english.config

import com.cxm.utils.NetworkType
import com.cxm.utils.RandomUtils
import java.util.*
import kotlin.properties.Delegates

/**
 * 菩提本无树
 * 明镜亦非台
 * 本来无一物
 * 何处惹尘埃
 * 陈小默 16/9/22.
 */
object NetState {
    private val subscribers = HashMap<String, (NetworkType) -> Unit>()
    fun subscribe(subscriber: (NetworkType) -> Unit): String {
        subscriber.invoke(state)
        val tag = RandomUtils.getString()
        subscribers.put(tag, subscriber)
        return tag
    }

    fun unSubscribe(tag: String) {
        subscribers.remove(tag)
    }

    var state: NetworkType by Delegates.observable(NetworkType.DISCONNECTION) { property, old, new ->
        subscribers.iterator().forEach { e ->
            e.value.invoke(new)
        }
    }
}
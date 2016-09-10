package com.cccxm.english.mvp.present

import com.cccxm.dao.Tongue
import com.cccxm.english.config.UserHolder
import com.cccxm.english.mvp.contract.ReciteContract
import java.util.*

/**
 * 陈小默 16/9/9.
 */
class RecitePresenter(val model: ReciteContract.IReciteModel,
                      val view: ReciteContract.IReciteView) : ReciteContract.IRecitePresenter {
    val list: MutableList<Tongue> = LinkedList()
    var item: Tongue? = null
    var site = 0
    var hideCh = false
    var hideEn = true
    var sum = 0
    var current = 0
    override fun better() {
        current++
        view.getCounter().text = "$current/$sum"
        val item = list.removeAt(site)
        model.upgrade(item)
        next()
    }

    override fun bad() {
        next()
    }

    override fun next() {
        if (current == sum) {
            view.enable()
            return
        }
        site = (Math.random() * list.size).toInt()
        item = list[site]
        view.getCHPanel().text = if (hideCh) "点击查看" else item!!.ch
        view.getENPanel().text = if (hideEn) "点击查看" else item!!.en
    }

    override fun submit() {
        model.LibList().filter { lib ->
            !lib.submit
        }.filter { lib ->
            val list = model.tongueList(lib.id)
            list.size == lib.count
        }.forEach { lib ->
            model.submit(lib, UserHolder.getUser(view.context())!!.token)
        }
    }

    override fun start() {
        list.addAll(model.current())
        if (list.size < 100) {
            list.addAll(model.random())
        }
        sum = list.size
        view.initView()
        if (sum != 0) view.register()
        view.getCounter().text = "$current/$sum"
        next()
    }

    override fun clickEn() {
        if (hideEn) {
            view.getENPanel().text = item!!.en
        } else {
            hideEn = true
            hideCh = false
        }
    }

    override fun clickCh() {
        if (hideCh) {
            view.getCHPanel().text = item!!.ch
        } else {
            hideCh = true
            hideEn = false
        }
    }
}
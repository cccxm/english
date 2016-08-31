package com.cccxm.english.mvp.present

import android.util.Log
import com.cccxm.english.R
import com.cccxm.english.bean.TongueLibBean
import com.cccxm.english.config.UserHolder
import com.cccxm.english.mvp.contract.TongueLibContract
import com.cccxm.english.mvp.view.holder.TongueLibItemHolder
import com.cxm.view.adapter.CommonBaseAdapter

/**
 * 菩提本无树
 * 明镜亦非台
 * 本来无一物
 * 何处惹尘埃
 * 陈小默 16/8/31.
 */
class TongueLibPresenter(val model: TongueLibContract.ITongueLibModel,
                         val view: TongueLibContract.ITongueLibView) : TongueLibContract.ITongueLibPresenter {

    val adapter: CommonBaseAdapter<TongueLibBean, TongueLibItemHolder>
    var isLoading = false

    init {
        adapter = object : CommonBaseAdapter<TongueLibBean, TongueLibItemHolder>(TongueLibItemHolder::class.java, R.layout.item_tongue_lib, view.context()) {
            override fun viewHolder(holder: TongueLibItemHolder, item: TongueLibBean, position: Int) {
                holder.name!!.text = item.lib_name
            }
        }
    }


    override fun loadList(page: Int) {
        if (isLoading) return
        isLoading = true
        model.loadList(page, UserHolder.getUser(view.context())!!.token, { res ->
            isLoading = false
            if (res.isSuccess) {
                adapter.list = res.data
            } else {

            }
        })
    }

    override fun start() {
        view.initView()
        view.register()
        view.getListView().adapter = adapter
    }
}

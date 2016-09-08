package com.cccxm.english.mvp.present

import android.util.Log
import android.util.SparseBooleanArray
import com.cccxm.dao.TongueLib
import com.cccxm.english.R
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

    val adapter: CommonBaseAdapter<TongueLib, TongueLibItemHolder>
    var isLoading = false
    val justNet = false
    var page = 0
    val isDownload = SparseBooleanArray()

    init {
        adapter = object : CommonBaseAdapter<TongueLib, TongueLibItemHolder>(TongueLibItemHolder::class.java, R.layout.item_tongue_lib, view.context()) {
            override fun viewHolder(holder: TongueLibItemHolder, item: TongueLib, position: Int) {
                holder.name!!.text = item.lib_name
                if (isDownload.get(item.id.toInt()) == false) {
                    holder.name.setBackgroundResource(R.drawable.gray_text_button)
                } else {
                    holder.name.setBackgroundResource(R.drawable.text_button)
                }
            }
        }
    }

    override fun click(position: Int) {
        if (position == 0) {
            Log.e("---message--", "继续学习")//TODO
        } else {
            val item = adapter.getItem(position - 1)
            if (isDownload.get(item.id.toInt())) {
                Log.e("--download--", "true")//TODO
            } else {
                view.showLibInfo(item.lib_name, item.level, item.score, UserHolder.getUser()!!.score)
            }
        }
    }


    override fun loadList() {
        if (isLoading) return
        if (!justNet) {
            isLoading = true
            val lastId = if (adapter.count > 0) adapter.getItem(adapter.count - 1).id.toInt() else 0
            model.loadDB(lastId, 20, { list ->
                isLoading = false
                val size = list.size
                if (size != 0) {
                    adapter.addAllData(list)
                } else {
                    loadNet()
                }
            })
        } else {
            loadNet()
        }
    }

    private fun loadNet() {
        isLoading = true
        model.loadList(page++, UserHolder.getUser(view.context())!!.token, { res ->
            isLoading = false
            if (res.isSuccess) {
                adapter.list = res.data
                res.data.filter { bean ->
                    !adapter.list.contains(bean)
                }.forEach { bean ->
                    isDownload.put(bean.id.toInt(), false)
                    adapter.addData(bean)
                }
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

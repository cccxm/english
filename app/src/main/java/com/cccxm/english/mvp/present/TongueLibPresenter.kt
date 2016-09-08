package com.cccxm.english.mvp.present

import android.util.Log
import android.util.SparseBooleanArray
import com.cccxm.dao.Tongue
import com.cccxm.dao.TongueLib
import com.cccxm.english.R
import com.cccxm.english.bean.TongueBean
import com.cccxm.english.config.UserHolder
import com.cccxm.english.mvp.contract.TongueLibContract
import com.cccxm.english.mvp.view.holder.TongueLibItemHolder
import com.cccxm.master.DBMaster
import com.cxm.view.adapter.CommonBaseAdapter
import com.google.gson.Gson
import java.util.*

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
    val isDownload = ArrayList<Int>()

    init {
        adapter = object : CommonBaseAdapter<TongueLib, TongueLibItemHolder>(TongueLibItemHolder::class.java, R.layout.item_tongue_lib, view.context()) {
            override fun viewHolder(holder: TongueLibItemHolder, item: TongueLib, position: Int) {
                holder.name!!.text = item.lib_name
                if (!isDownload.contains(item.id.toInt())) {
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
            if (isDownload.contains(item.id.toInt())) {
                Log.e("--download--", "true")//TODO
            } else {
                view.showLibInfo(item.lib_name, item.level, item.score, UserHolder.getUser()!!.score, position - 1)
            }
        }
    }

    override fun libDownload(position: Int) {
        val score = UserHolder.getUser(view.context())!!.score
        val item = adapter.getItem(position)
        if (score < item.level) return
        model.downloadLib(item.uri, UserHolder.getUser(view.context())!!.token, { res ->
            val bean = Gson().fromJson(res, TongueBean::class.java)
            val session = DBMaster.newSession()
            session.tongueLibDao.insert(item)
            bean.list.forEach { t ->
                val tongue = Tongue(null, item.id.toInt(), t.en, t.ch, null, null)
                session.tongueDao.insert(tongue)
            }
            isDownload.add(item.id.toInt())
            view.hideLibInfo()
            page = 0
            loadList()
        })
    }


    override fun loadList() {
        if (isLoading) return
        if (!justNet) {
            isLoading = true
            val lastId = if (adapter.count > 0) adapter.getItem(adapter.count - 1).id.toInt() else 0
            model.loadDB(lastId, 20, { list ->
                isLoading = false
                val size = list.size
                list.forEach { lib ->
                    isDownload.add(lib.id.toInt())
                }
                if (size != 0)
                    adapter.list = list
                if (size != 20)
                    loadNet()
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
                res.data.filter { bean ->
                    !isDownload.contains(bean.id.toInt())
                }.forEach { bean ->
                    adapter.list.add(bean)
                }
                adapter.notifyDataSetChanged()
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

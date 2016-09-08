package com.cccxm.english.mvp.contract

import android.widget.ListView
import com.cccxm.dao.TongueLib
import com.cccxm.english.bean.HttpListResponse
import com.cccxm.english.bean.TongueLibBean
import com.cxm.mvp.IPresenter
import com.cxm.mvp.IView

/**
 * 菩提本无树
 * 明镜亦非台
 * 本来无一物
 * 何处惹尘埃
 * 陈小默 16/8/31.
 */
interface TongueLibContract {
    interface ITongueLibView : IView {
        fun initView()
        fun register()
        fun startDetailActivity(bean: TongueLibBean)
        fun message(msg: String)
        fun getListView(): ListView
        fun showLibInfo(name: String, level: Int, score: Int, my: Int, position: Int)
        fun hideLibInfo()
    }

    interface ITongueLibPresenter : IPresenter {
        fun loadList()
        fun click(position: Int)
        fun libDownload(position: Int)
    }

    interface ITongueLibModel {
        fun loadList(page: Int, token: String, cb: (HttpListResponse<TongueLib>) -> Unit)

        fun loadDB(id: Int, size: Int, cb: (MutableList<TongueLib>) -> Unit)

        fun downloadLib(uri: String, token: String, cb: (String) -> Unit)
    }
}
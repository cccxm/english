package com.cccxm.english.mvp.contract

import android.widget.ListView
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
    }

    interface ITongueLibPresenter : IPresenter {
        fun loadList(page: Int)
    }

    interface ITongueLibModel {
        fun loadList(page: Int, token: String, cb: (HttpListResponse<TongueLibBean>) -> Unit)
    }
}
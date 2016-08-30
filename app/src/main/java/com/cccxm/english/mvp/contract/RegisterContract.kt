package com.cccxm.english.mvp.contract

import com.cccxm.english.bean.HttpResponse
import com.cccxm.english.bean.UserBean
import com.cxm.mvp.IPresenter
import com.cxm.mvp.IView

/**
 * 菩提本无树
 * 明镜亦非台
 * 本来无一物
 * 何处惹尘埃
 * 陈小默 16/8/29.
 */
interface RegisterContract {
    interface IRegisterView : IView {
        fun getUsername(): String
        fun getPassword1(): String
        fun getPassword2(): String
        fun errUsername(msg: String)
        fun errPassword1(msg: String)
        fun errPassword2(msg: String)
        fun showMessage(msg: String)
        fun mainActivity()
        fun loginActivity()
        fun registerState()
        fun normalState()
    }

    interface IRegisterPresenter : IPresenter {
        fun register()
    }

    interface IRegisterModel {
        fun register(username: String, password: String, cb: (HttpResponse<UserBean>) -> Unit)
    }
}
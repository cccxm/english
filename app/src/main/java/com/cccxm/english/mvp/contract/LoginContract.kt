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
interface LoginContract {
    interface ILoginView : IView {
        fun setUsername(username: String)
        fun getUsername(): String
        fun setPassword(password: String)
        fun getPassword(): String
        fun usernameError(msg: String)
        fun passwordError(msg: String)
        fun loginMessage(msg: String)
        fun mainActivity()
        fun registerActivity()
        fun loginStyle()
        fun normalStyle()
    }

    interface ILoginPresent : IPresenter {
        fun login()
    }

    interface ILoginModel {
        fun login(username: String, password: String, cb: (HttpResponse<UserBean>) -> Unit)
    }
}
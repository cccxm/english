package com.cccxm.english.mvp.model

import com.cccxm.english.bean.HttpResponse
import com.cccxm.english.bean.UserBean
import com.cccxm.english.bean.WelcomeBean
import com.cccxm.english.config.Urls
import com.cccxm.english.mvp.contract.LoginContract
import com.cccxm.english.net.LoginService
import com.cxm.lib.retrofit.RetrofitUtils
import com.cxm.lib.rx.NetSubscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * 菩提本无树
 * 明镜亦非台
 * 本来无一物
 * 何处惹尘埃
 * 陈小默 16/8/29.
 */
class LoginModel : LoginContract.ILoginModel {
    override fun login(username: String, password: String, cb: (HttpResponse<UserBean>) -> Unit) {
        val service = RetrofitUtils.getService(Urls.HOST, LoginService::class.java)
        service.login(username, password)
                .netCallback(cb)
    }
}
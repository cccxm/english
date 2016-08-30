package com.cccxm.english.mvp.model

import com.cccxm.english.bean.HttpResponse
import com.cccxm.english.bean.UserBean
import com.cccxm.english.config.Urls
import com.cccxm.english.mvp.contract.WelcomeContract
import com.cccxm.english.net.LoginService
import com.cccxm.english.net.WelcomeService
import com.cxm.lib.retrofit.RetrofitUtils
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * 菩提本无树
 * 明镜亦非台
 * 本来无一物
 * 何处惹尘埃
 * 陈小默 16/8/29.
 */
class WelcomeModel : WelcomeContract.IWelcomeModel {
    override fun loadData(present: WelcomeContract.IWelcomePresent) {
        val service = RetrofitUtils.getService(Urls.HOST, WelcomeService::class.java)
        service.getAdvertisement()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { res ->
                    present.callback(res)
                }
    }

    override fun login(username: String, password: String, cb: (HttpResponse<UserBean>) -> Unit) {
        val service = RetrofitUtils.getService(Urls.HOST, LoginService::class.java)
        service.login(username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { res ->
                    cb.invoke(res)
                }
    }
}
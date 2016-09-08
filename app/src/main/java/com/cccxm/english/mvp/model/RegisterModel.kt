package com.cccxm.english.mvp.model

import com.cccxm.english.bean.HttpResponse
import com.cccxm.english.bean.UserBean
import com.cccxm.english.config.Urls
import com.cccxm.english.mvp.contract.RegisterContract
import com.cccxm.english.net.RegisterService
import com.cxm.lib.retrofit.RetrofitUtils

/**
 * 菩提本无树
 * 明镜亦非台
 * 本来无一物
 * 何处惹尘埃
 * 陈小默 16/8/29.
 */
class RegisterModel : RegisterContract.IRegisterModel {
    override fun register(username: String, password: String, cb: (HttpResponse<UserBean>) -> Unit) {
        val service = RetrofitUtils.getService(Urls.HOST, RegisterService::class.java)
        service.register(username, password)
                .netCallback(cb)
    }
}
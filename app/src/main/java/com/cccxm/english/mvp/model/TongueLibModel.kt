package com.cccxm.english.mvp.model

import com.cccxm.english.bean.HttpListResponse
import com.cccxm.english.bean.TongueLibBean
import com.cccxm.english.config.Urls
import com.cccxm.english.mvp.contract.TongueLibContract
import com.cccxm.english.net.TongueLibService
import com.cxm.lib.retrofit.RetrofitUtils
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * 菩提本无树
 * 明镜亦非台
 * 本来无一物
 * 何处惹尘埃
 * 陈小默 16/8/31.
 */
class TongueLibModel : TongueLibContract.ITongueLibModel {
    override fun loadList(page: Int, token: String, cb: (HttpListResponse<TongueLibBean>) -> Unit) {
        val service = RetrofitUtils.getService(Urls.HOST, TongueLibService::class.java)
        service.getLibList(page, token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { res ->
                    cb.invoke(res)
                }
    }
}
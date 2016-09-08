package com.cccxm.english.mvp.model

import android.util.Log
import com.cccxm.dao.TongueLib
import com.cccxm.dao.TongueLibDao
import com.cccxm.english.bean.HttpListResponse
import com.cccxm.english.config.Urls
import com.cccxm.english.mvp.contract.TongueLibContract
import com.cccxm.english.net.DownloadService
import com.cccxm.english.net.TongueLibService
import com.cccxm.master.DBMaster
import com.cxm.lib.retrofit.RetrofitUtils
import com.cxm.lib.rx.NetSubscriber
import okhttp3.ResponseBody
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
    override fun loadList(page: Int, token: String, cb: (HttpListResponse<TongueLib>) -> Unit) {
        val service = RetrofitUtils.getService(Urls.HOST, TongueLibService::class.java)
        service.getLibList(page, token)
                .netCallback(cb)
    }

    override fun loadDB(id: Int, size: Int, cb: (MutableList<TongueLib>) -> Unit) {
        val builder = DBMaster.newSession().tongueLibDao.queryBuilder().where(TongueLibDao.Properties.Id.gt(id)).limit(size)
        builder.query(cb)
    }

    override fun downloadLib(uri: String, token: String, cb: (String) -> Unit) {
        val service = RetrofitUtils.getService(Urls.HOST, DownloadService::class.java)
        service.download(uri, token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : NetSubscriber<ResponseBody>() {
                    override fun onNext(t: ResponseBody?) {
                        cb.invoke(t!!.string())
                    }
                })
    }
}
package com.cccxm.english.mvp.model

import com.cccxm.dao.TongueLib
import com.cccxm.dao.TongueLibDao
import com.cccxm.english.bean.HttpListResponse
import com.cccxm.english.config.Urls
import com.cccxm.english.mvp.contract.TongueLibContract
import com.cccxm.english.net.TongueLibService
import com.cccxm.master.DBMaster
import com.cxm.lib.retrofit.RetrofitUtils

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
}
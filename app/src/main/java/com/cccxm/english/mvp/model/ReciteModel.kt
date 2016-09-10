package com.cccxm.english.mvp.model

import com.cccxm.dao.Tongue
import com.cccxm.dao.TongueDao
import com.cccxm.dao.TongueLib
import com.cccxm.english.bean.HttpResponse
import com.cccxm.english.config.LevelTable
import com.cccxm.english.config.Urls
import com.cccxm.english.mvp.contract.ReciteContract
import com.cccxm.english.net.SubmitService
import com.cccxm.master.DBMaster
import com.cxm.lib.retrofit.RetrofitUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * 陈小默 16/9/9.
 */
class ReciteModel : ReciteContract.IReciteModel {
    override fun random(): MutableList<Tongue> {
        val session = DBMaster.newSession()
        return session.tongueDao.queryRaw("where progress=-1 order by random() limit 30")
    }

    override fun current(): MutableList<Tongue> {
        val session = DBMaster.newSession()
        val current = System.currentTimeMillis()
        return session.tongueDao.queryBuilder().where(TongueDao.Properties.Next.le(current)).where(TongueDao.Properties.Progress.gt(-1)).build().list()
    }

    override fun upgrade(item: Tongue) {
        val currentLevel = item.progress
        if (!LevelTable.over(currentLevel)) {
            val level = currentLevel + 1
            val next = LevelTable.next(level) + System.currentTimeMillis()
            val session = DBMaster.newSession()
            item.progress = level
            item.next = next
            session.tongueDao.update(item)
        }
    }

    override fun LibList(): MutableList<TongueLib> {
        val session = DBMaster.newSession()
        return session.tongueLibDao.queryBuilder().build().list()
    }

    override fun tongueList(lib_id: Long): MutableList<Tongue> {
        val session = DBMaster.newSession()
        return session.tongueDao.queryBuilder().where(TongueDao.Properties.Lib_id.eq(lib_id)).where(TongueDao.Properties.Progress.gt(5)).build().list()
    }

    override fun submit(lib: TongueLib, token: String) {
        val service = RetrofitUtils.getService(Urls.HOST, SubmitService::class.java)
        service.submit(lib.id, token).enqueue(object : Callback<HttpResponse<String>> {
            override fun onResponse(call: Call<HttpResponse<String>>?, response: Response<HttpResponse<String>>?) {
            }

            override fun onFailure(call: Call<HttpResponse<String>>?, t: Throwable?) {
            }
        })
    }
}
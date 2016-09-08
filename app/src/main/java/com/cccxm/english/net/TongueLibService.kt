package com.cccxm.english.net

import com.cccxm.dao.TongueLib
import com.cccxm.english.bean.HttpListResponse
import com.cccxm.english.config.Urls
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

/**
 * 菩提本无树
 * 明镜亦非台
 * 本来无一物
 * 何处惹尘埃
 * 陈小默 16/8/31.
 */
interface TongueLibService {
    @GET(Urls.TONGUE_LIB_LIST)
    fun getLibList(@Query("page") page: Int, @Query("token") token: String): Observable<HttpListResponse<TongueLib>>
}
package com.cccxm.english.net

import com.cccxm.english.bean.HttpResponse
import com.cccxm.english.config.Urls
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * 菩提本无树
 * 明镜亦非台
 * 本来无一物
 * 何处惹尘埃
 * 陈小默 16/9/9.
 */
interface SubmitService {
    @FormUrlEncoded
    @POST(Urls.SUBMIT)
    fun submit(@Field("libId") libId: Long, @Query("token") token: String): Call<HttpResponse<String>>
}
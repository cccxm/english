package com.cccxm.english.net

import com.cccxm.english.bean.HttpResponse
import com.cccxm.english.bean.UserBean
import com.cccxm.english.config.Urls
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import rx.Observable

/**
 * 菩提本无树
 * 明镜亦非台
 * 本来无一物
 * 何处惹尘埃
 * 陈小默 16/8/29.
 */
interface RegisterService {
    @FormUrlEncoded
    @POST(Urls.REGISTER)
    fun register(@Field("username") username: String, @Field("password") password: String): Observable<HttpResponse<UserBean>>
}
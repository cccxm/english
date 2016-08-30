package com.cccxm.english.net

import com.cccxm.english.bean.HttpResponse
import com.cccxm.english.bean.WelcomeBean
import com.cccxm.english.config.Urls
import retrofit2.http.GET
import rx.Observable

/**
 * 菩提本无树
 * 明镜亦非台
 * 本来无一物
 * 何处惹尘埃
 * 陈小默 16/8/29.
 */
interface WelcomeService {
    @GET(Urls.WELCOME_ADVERTISEMENT)
    fun getAdvertisement(): Observable<HttpResponse<WelcomeBean>>
}
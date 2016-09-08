package com.cccxm.english.net

import com.cccxm.english.config.Urls
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

/**
 * 菩提本无树
 * 明镜亦非台
 * 本来无一物
 * 何处惹尘埃
 * 陈小默 16/9/8.
 */
interface DownloadService {
    @GET(Urls.DOWNLOAD_LIB)
    fun download(@Query("uri") uri: String, @Query("token") token: String): Observable<ResponseBody>
}
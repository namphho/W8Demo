package com.hnam.w8demo.service

import com.hnam.w8demo.API_KEY
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by nampham on 5/26/20.
 */
class AuthInterceptor : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url.newBuilder()
            .addQueryParameter("api_key", API_KEY).build()
        val finalRequest = request.newBuilder().url(url).build()
        return chain.proceed(finalRequest)
    }

}
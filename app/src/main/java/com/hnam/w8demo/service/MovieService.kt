package com.hnam.w8demo.service

import com.hnam.w8demo.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by nampham on 5/26/20.
 */
class MovieService {
    companion object{
        private var api: MovieApi? = null

        fun getApi() : MovieApi = api ?: synchronized(this){
            api ?: createInstance().also { api = it }
        }

        private fun createInstance() : MovieApi{
            val okHttpClient = OkHttpClient.Builder().addInterceptor(AuthInterceptor())
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
            return retrofit.create(MovieApi::class.java)
        }
    }
}
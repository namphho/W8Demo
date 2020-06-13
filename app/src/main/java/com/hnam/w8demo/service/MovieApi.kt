package com.hnam.w8demo.service

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by nampham on 5/26/20.
 * https://developers.themoviedb.org/3/movies/get-now-playing
 */
interface MovieApi {
    @GET("movie/top_rated")
    fun getTopRateMovie() : Call<VideoResponse>

    @GET("movie/now_playing")
    fun getNowPlaying(): Call<VideoResponse>

    @GET("movie/now_playing")
    suspend fun getNowPlayingCoroutine(): VideoResponse

    @GET("movie/top_rated")
    suspend fun getTopRateMovieCoroutine() : VideoResponse
}
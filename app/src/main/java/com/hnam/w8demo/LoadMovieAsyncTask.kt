package com.hnam.w8demo

import android.graphics.Movie
import android.os.AsyncTask
import android.util.Log
import com.hnam.w8demo.service.MovieService
import com.hnam.w8demo.service.Video
import java.io.IOException
import java.net.SocketTimeoutException

/**
 * Created by nampham on 6/8/20.
 */
data class LoadMovieResult(val status: Int, val errorMessage: String, val data: List<Video>)

class LoadMovieAsyncTask(private var callback: onCallback?) : AsyncTask<Void, Void, LoadMovieResult>() {
    companion object {
        private val TAG = LoadMovieAsyncTask::class.java.simpleName
    }
    interface onCallback{
        fun onResult(videos: List<Video>)
        fun onError(msg: String)
    }

    override fun doInBackground(vararg params: Void?): LoadMovieResult? {
        var status = -1;
        var errMsg = ""
        var data = emptyList<Video>()
        try {
            Log.e(TAG, "doInBackground: ${Thread.currentThread().name}")
            val resp = MovieService.getApi().getNowPlaying().execute()
            if (resp.isSuccessful){
                val code = resp.code()
                when(code){
                    200 -> {
                        status = 200
                        data = resp.body()?.result ?: emptyList()
                    }
                    else -> {
                        status = code
                        errMsg = resp.message()
                    }
                }
            }
        } catch (e : IOException){
            status = -1
            errMsg = e.message.toString()
        } finally {
            return LoadMovieResult(status, errMsg, data)
        }
    }

    override fun onPostExecute(result: LoadMovieResult) {
        super.onPostExecute(result)
        Log.e(TAG, "onPostExecute: ${Thread.currentThread().name}")
        when(result.status){
            200 -> callback?.onResult(result.data)
            else -> callback?.onError(result.errorMessage)
        }
    }

    override fun onCancelled() {
        super.onCancelled()
        callback = null;
    }
}
package com.hnam.w8demo.screens.coroutine

import android.graphics.Movie
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.hnam.w8demo.R
import com.hnam.w8demo.service.MovieService
import com.hnam.w8demo.workers.sleep
import kotlinx.android.synthetic.main.activity_coroutine.*
import kotlinx.coroutines.*

class CoroutinesActivity : AppCompatActivity() {
    companion object {
        private val TAG = CoroutinesActivity::class.java.simpleName
    }
    lateinit var scope: CoroutineScope;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine)

        //demo
        scope = CoroutineScope(Dispatchers.Main)
        scope.launch {
            Log.e(TAG, Thread.currentThread().toString())
            val nowResp = withContext(Dispatchers.IO){
                Log.e(TAG,"==== get network: " + Thread.currentThread().toString())
                MovieService.getApi().getNowPlayingCoroutine()
            }
            val topResp = withContext(Dispatchers.IO){
                MovieService.getApi().getTopRateMovieCoroutine()
            }
            Log.e(TAG, "show result content")
            tv_content.text = "now resp: ${nowResp.totalPages}  top Resp: ${topResp.totalPages}"
        }
        //demo cancels
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }
}

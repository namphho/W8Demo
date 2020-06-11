package com.hnam.w8demo.screens.asynctask

import android.app.DownloadManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hnam.w8demo.LoadMovieAsyncTask
import com.hnam.w8demo.R
import com.hnam.w8demo.service.Video
import kotlinx.android.synthetic.main.activity_async_task.*
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class AsyncTaskActivity : AppCompatActivity(), LoadMovieAsyncTask.onCallback {
    companion object {
        private val TAG = AsyncTaskActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_async_task)
        button.setOnClickListener {
            LoadMovieAsyncTask(this).execute()
        }
        val d: DownloadManager
    }

    override fun onResult(videos: List<Video>) {
        Timber.tag(TAG).e(videos.toString())
        tv.text = "total size: ${videos.size}"
    }

    override fun onError(msg: String) {
        tv.text = "error: $msg"
    }
}

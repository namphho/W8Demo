package com.hnam.w8demo.screens.coroutine

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

        //demo problems
        //demo execute
        //demo cancels
    }

}

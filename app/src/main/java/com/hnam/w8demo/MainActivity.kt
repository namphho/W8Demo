package com.hnam.w8demo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hnam.w8demo.screens.asynctask.AsyncTaskActivity
import com.hnam.w8demo.screens.coroutine.CoroutinesActivity
import com.hnam.w8demo.screens.imagescreen.SelectImageActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_async_task.setOnClickListener {
            val i = Intent(this, AsyncTaskActivity::class.java)
            startActivity(i)
        }
        btn_work_manager.setOnClickListener {
            val i = Intent(this, SelectImageActivity::class.java)
            startActivity(i)
        }
        btn_coroutines.setOnClickListener {
            val i = Intent(this, CoroutinesActivity::class.java)
            startActivity(i)
        }
    }


}


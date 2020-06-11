package com.hnam.w8demo.workers

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.text.TextUtils
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.example.background.KEY_IMAGE_URI
import timber.log.Timber

/**
 * Created by nampham on 6/9/20.
 */
class BlurWorker(ctx: Context, params: WorkerParameters ) : Worker(ctx, params) {
    override fun doWork(): Result {
        makeStatusNotification("start blur", applicationContext)

        // ADD THIS TO SLOW DOWN THE WORKER
        sleep()
        // ^^^^

        return try {
            val resourceUri = inputData.getString(KEY_IMAGE_URI)
            if (TextUtils.isEmpty(resourceUri)) {
                Timber.e("Invalid input uri")
                throw IllegalArgumentException("Invalid input uri")
            }
            val resolver = applicationContext.contentResolver

            val picture = BitmapFactory.decodeStream(
                    resolver.openInputStream(Uri.parse(resourceUri)))

            val bitmap = blurBitmap(picture, applicationContext)
            val outputUri = writeBitmapToFile(applicationContext, bitmap)

            val outputData = workDataOf(KEY_IMAGE_URI to outputUri.toString(), "TEST" to 1)
            Result.success(outputData)

        } catch (throwable: Throwable) {
            Timber.d(throwable, "error applying blur")
            Result.failure()
        }
    }
}
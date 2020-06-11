package com.hnam.w8demo.service

import com.google.gson.annotations.SerializedName

/**
 * Created by nampham on 5/26/20.
 * "page": 1,
"total_results": 7350,
"total_pages": 368,
"results"
 */

data class VideoResponse(
    val page: Int,
    @SerializedName("total_results") val totalResult: Int,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("results") val result: List<Video>
)
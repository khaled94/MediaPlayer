package com.example.mediaplayer.data.api

import com.example.mediaplayer.data.model.ApiResponse
import io.reactivex.Single
import retrofit2.http.GET

interface ServiceApi {
    @GET("demo/video/list/samples.json")
    fun fetchVideos(): Single<ApiResponse>
}
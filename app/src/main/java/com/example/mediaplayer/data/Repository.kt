package com.example.mediaplayer.data

import com.example.mediaplayer.data.api.ServiceApi
import com.example.mediaplayer.data.model.ApiResponse
import io.reactivex.Single

class Repository (private val remoteApi: ServiceApi){

    fun getVideos(): Single<ApiResponse> {
        return remoteApi.fetchVideos()
    }
}
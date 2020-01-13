package com.example.mediaplayer.data

import com.example.mediaplayer.data.api.ServiceApi
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object VideosService {
    private const val BASE_URL = "https://res.cloudinary.com/"
    fun fetchVideos() = createCloudinaryVideoService().fetchVideos()

    private fun createRetrofitInstance() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private fun createCloudinaryVideoService() = createRetrofitInstance().create(ServiceApi::class.java)
}
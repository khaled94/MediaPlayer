package com.example.mediaplayer.ui.main

import com.example.mediaplayer.data.model.Resource

interface MainContract {

    interface Presenter {

        fun fetchSampleVideos()

        fun deactivate()

        fun showVideoScreen(videoUrl: String)
    }

    interface View {

        fun renderVideos(videos: List<Resource>)

        fun showErrorMessage()
    }
}
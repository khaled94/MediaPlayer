package com.example.mediaplayer.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mediaplayer.R
import com.example.mediaplayer.data.model.Resource
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.Subject
import kotlinx.android.synthetic.main.video_item.view.*
import java.util.concurrent.TimeUnit

class MainAdapter  : RecyclerView.Adapter<MainAdapter.VideoViewHolder>() {
    companion object {
        const val CLICK_THROTTLE_WINDOW_MILLIS = 300L
    }

    private val onVideoClickSubject: Subject<Resource> = BehaviorSubject.create()

    private var videos: List<Resource> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.video_item, parent, false)
        return VideoViewHolder(itemView, onVideoClickSubject)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) = holder.setVideo(videos[position])

    override fun getItemCount() = videos.size

    fun onVideosUpdate(videos: List<Resource>) {
        this.videos = videos
        notifyDataSetChanged()
    }

    fun onItemClick() = onVideoClickSubject.throttleFirst(CLICK_THROTTLE_WINDOW_MILLIS, TimeUnit.MILLISECONDS)

    class VideoViewHolder(val view: View,
                          private val clickSubject: Subject<Resource>
    ) : RecyclerView.ViewHolder(view) {

        private lateinit var video: Resource

        fun setVideo(video: Resource) {
            this.video = video
            itemView.tv_main_video_title.text = video.publicId
            itemView.main_video_item_container.setOnClickListener { onMovieClick() }
        }

        private fun onMovieClick() = clickSubject.onNext(video)
    }
}
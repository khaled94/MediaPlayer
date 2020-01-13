package com.example.mediaplayer.ui.main

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mediaplayer.R
import com.example.mediaplayer.data.model.Resource

class MainActivity : AppCompatActivity() ,MainContract.View{

    private lateinit var progressBar: ProgressBar
    private lateinit var videosList: RecyclerView
    private lateinit var emptyText: TextView
    private lateinit var presenter: MainContract.Presenter
    private lateinit var videosAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.deactivate()
    }

    override fun renderVideos(videos: List<Resource>) {
        hideLoadingIndicator()
        hideEmptyView()
        videosAdapter.onVideosUpdate(videos)
    }

    override fun showErrorMessage() {
        hideLoadingIndicator()
        //showEmptyView()
    }

    private fun init() {
        progressBar = findViewById(R.id.pb_main)
        videosList = findViewById(R.id.rv_videos)
        emptyText = findViewById(R.id.tv_empty)

        initializeRecyclerView()

        presenter = MainPresenter(this)

        presenter.fetchSampleVideos()
        showLoadingIndicator()
        hideEmptyView()
    }

    @SuppressLint("CheckResult")
    private fun initializeRecyclerView() {
        videosList.layoutManager = LinearLayoutManager(this)
        videosList.setHasFixedSize(true)

        videosAdapter = MainAdapter()
        videosAdapter.onItemClick().subscribe(this::onVideoItemClick)
        videosList.adapter = videosAdapter
    }

    private fun onVideoItemClick(video: Resource) {
        presenter.showVideoScreen(createVideoUrl(video))
    }

    private fun showLoadingIndicator() {
        progressBar.visibility = View.VISIBLE
    }

    private fun hideLoadingIndicator() {
        progressBar.visibility = View.GONE
    }

    private fun hideEmptyView() {
        emptyText.visibility = View.GONE
    }

    private fun showEmptyView() {
        emptyText.visibility = View.VISIBLE
    }

    private fun createVideoUrl(video: Resource) =
        "https://res.cloudinary.com/demo/video/${video.type}/v${video.version}/${video.publicId}.${video.format}"
}

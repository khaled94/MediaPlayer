package com.example.mediaplayer.ui.video

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mediaplayer.R
import com.google.android.exoplayer2.ui.PlayerView

class VideoViewActivity : AppCompatActivity() {

    companion object {
        const val VIDEO_URL_EXTRA = "video_url_extra"
    }

    private lateinit var videoView: PlayerView

    private lateinit var presenter: VideoContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_view)
        init()
    }

    private fun init() {
        presenter = VideoPresenter(this)
        val videoUrl = intent.getStringExtra(VIDEO_URL_EXTRA)
        videoView = findViewById(R.id.ep_video_view)
        videoView.player = presenter.getPlayer().getPlayerImpl(this)
        presenter.play(videoUrl)
    }

    override fun onPause() {
        super.onPause()
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            presenter.releasePlayer()
        }
    }

    override fun onStop() {
        super.onStop()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            presenter.releasePlayer()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.deactivate()
        presenter.setMediaSessionState(false)
    }


}
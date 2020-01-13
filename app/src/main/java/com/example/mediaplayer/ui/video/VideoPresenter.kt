package com.example.mediaplayer.ui.video

import com.example.mediaplayer.ui.player.MediaPlayerImp
import java.lang.ref.WeakReference

class VideoPresenter(videoViewView: VideoViewActivity) : VideoContract.Presenter {

    private val view = WeakReference(videoViewView)

    private val mediaPlayer = MediaPlayerImp()

    override fun deactivate() {
    }

    override fun getPlayer() = mediaPlayer

    override fun play(url: String) = mediaPlayer.play(url)

    override fun releasePlayer() = mediaPlayer.releasePlayer()

    override fun setMediaSessionState(isActive: Boolean) {
    mediaPlayer.setMediaSessionState(isActive)
    }
}
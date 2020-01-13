package com.example.mediaplayer.ui.player

import android.content.Context
import android.net.Uri
import com.example.mediaplayer.R
import com.google.android.exoplayer2.DefaultLoadControl
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util

class MediaPlayerImp : MediaPlayer {

    companion object {
        private const val TAG = "MediaPlayerTag"
    }

    private lateinit var exoPlayer: ExoPlayer
    private lateinit var context: Context

    private fun initializePlayer() {
        val trackSelector = DefaultTrackSelector()
        val loadControl = DefaultLoadControl()
        //val renderersFactory = DefaultRenderersFactory(context)
        exoPlayer = ExoPlayerFactory.newSimpleInstance(context, trackSelector, loadControl)
    }
    override fun play(url: String) {
        val userAgent = Util.getUserAgent(context, context.getString(R.string.app_name))
        val mediaSource = ProgressiveMediaSource
            .Factory(DefaultDataSourceFactory(context, userAgent))
            .createMediaSource(Uri.parse(url))

        exoPlayer.prepare(mediaSource)
        exoPlayer.playWhenReady = true
    }

    override fun getPlayerImpl(context: Context): ExoPlayer {
        this.context = context
        initializePlayer()
        return exoPlayer
    }

    override fun releasePlayer() {
        exoPlayer.stop()
        exoPlayer.release()
    }

    override fun setMediaSessionState(isActive: Boolean) {
       // mediaSession.isActive = isActive
    }

}
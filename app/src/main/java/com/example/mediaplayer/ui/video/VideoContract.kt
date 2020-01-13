package com.example.mediaplayer.ui.video

import com.example.mediaplayer.ui.player.MediaPlayer

interface VideoContract {

    interface Presenter {

        fun deactivate()

        fun getPlayer(): MediaPlayer

        fun play(url: String)

        fun releasePlayer()

        fun setMediaSessionState(isActive: Boolean)
    }

    interface View
}
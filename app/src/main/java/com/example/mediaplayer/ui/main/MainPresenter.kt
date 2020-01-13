package com.example.mediaplayer.ui.main

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.util.Log
import com.example.mediaplayer.data.VideosService
import com.example.mediaplayer.data.model.ApiResponse
import com.example.mediaplayer.ui.video.VideoViewActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.lang.ref.WeakReference

class MainPresenter(view: MainContract.View) : MainContract.Presenter {

    private val view = WeakReference<MainContract.View>(view)
    private val disposables = CompositeDisposable()

    @SuppressLint("CheckResult")
    override fun fetchSampleVideos() {
        /*
        disposables.add(
            VideosService.fetchVideos()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { apiResponse -> onVideosFetchedSuccessfully(apiResponse) },
                    { throwable -> onVideosFetchError(throwable) }
                ))*/
            VideosService.fetchVideos()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                { apiResponse -> onVideosFetchedSuccessfully(apiResponse)
                    Log.e("check1 -> ","true")
                }, {
                        throwable -> onVideosFetchError(throwable)
                    Log.e("check1 -> ",throwable.toString())
                }
            )
    }

    override fun showVideoScreen(videoUrl: String) {
        val intent = Intent((view.get() as Activity), VideoViewActivity::class.java)
        intent.putExtra(VideoViewActivity.VIDEO_URL_EXTRA, videoUrl)
        (view.get() as Activity).startActivity(intent)
    }

    override fun deactivate() {
        disposables.clear()
    }

    private fun onVideosFetchedSuccessfully(videoData: ApiResponse?) {
        view.get()?.renderVideos(videoData?.resources!!)
    }

    private fun onVideosFetchError(throwable: Throwable) {
        view.get()?.showErrorMessage()
    }
}
package com.example.mediaplayer.data.model


import com.google.gson.annotations.SerializedName

data class Context(
    @SerializedName("custom")
    val custom: Custom
)
package com.example.mediaplayer.data.model


import com.google.gson.annotations.SerializedName

data class Custom(
    @SerializedName("alt")
    val alt: String,
    @SerializedName("campaign")
    val campaign: String,
    @SerializedName("caption")
    val caption: String,
    @SerializedName("title")
    val title: String
)
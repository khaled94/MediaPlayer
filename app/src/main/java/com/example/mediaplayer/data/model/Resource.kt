package com.example.mediaplayer.data.model


import com.google.gson.annotations.SerializedName

data class Resource(
    @SerializedName("context")
    val context: Context,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("format")
    val format: String,
    @SerializedName("height")
    val height: Int,
    @SerializedName("public_id")
    val publicId: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("version")
    val version: Int,
    @SerializedName("width")
    val width: Int
)
package com.example.mediaplayer.data.model


import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("resources")
    val resources: List<Resource>,
    @SerializedName("updated_at")
    val updatedAt: String
)
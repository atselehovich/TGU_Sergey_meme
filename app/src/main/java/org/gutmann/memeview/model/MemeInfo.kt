package org.gutmann.memeview.model

import com.google.gson.annotations.SerializedName

data class MemeInfo(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("image_url") val imageUrl: String,
    @SerializedName("rating") val rating: Float = .0f
)
package org.gutmann.memeview.model

import com.google.gson.annotations.SerializedName

data class Memes(
    @SerializedName("memes") val memes: List<MemeInfo>
)
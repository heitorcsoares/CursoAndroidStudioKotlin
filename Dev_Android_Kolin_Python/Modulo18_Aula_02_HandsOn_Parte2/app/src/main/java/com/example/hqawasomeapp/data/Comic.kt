package com.example.hqawasomeapp.data

import android.media.Image
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Comic(
    val id: Int?,
    val title: String?,
    val description: String?,
    val textObject: List<TextObject?>,
    val thumbnail: Image?
)

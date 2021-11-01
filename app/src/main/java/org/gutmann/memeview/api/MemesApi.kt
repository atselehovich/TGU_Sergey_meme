package org.gutmann.memeview.api

import org.gutmann.memeview.model.Memes
import retrofit2.http.GET

interface MemesApi {
    @GET("test2.json")
    suspend fun getMemes(): Memes
}
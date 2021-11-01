package org.gutmann.memeview.model

import org.gutmann.memeview.api.MemesApi

class RESTApiMemesRepository(private val memesApi: MemesApi) : MemesRepository {
    override suspend fun getMemes(): List<MemeInfo> = memesApi.getMemes().memes
}
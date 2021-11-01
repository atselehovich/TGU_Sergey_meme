package org.gutmann.memeview.model

import javax.inject.Inject

class MemoryMemesRepository @Inject constructor() : MemesRepository {
    override suspend fun getMemes(): List<MemeInfo> {
        return (0..100).map { i ->
            MemeInfo(
                id = i,
                name = "meme #$i",
                description = "meme description $i",
                imageUrl = "",
                rating = i.toFloat()
            )
        }
    }
}
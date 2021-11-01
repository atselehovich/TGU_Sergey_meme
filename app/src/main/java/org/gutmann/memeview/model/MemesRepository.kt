package org.gutmann.memeview.model

interface MemesRepository {
    suspend fun getMemes(): List<MemeInfo>

    suspend fun getMemeInfo(memeId: Int): MemeInfo {
        val memeInfo = getMemes().find { memeInfo -> memeInfo.id == memeId }
        return memeInfo ?: throw NoSuchElementException("Meme with id == $memeId not found")
    }
}
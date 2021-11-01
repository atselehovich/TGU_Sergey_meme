package org.gutmann.memeview.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import org.gutmann.memeview.model.MemeInfo
import org.gutmann.memeview.model.MemesRepository
import javax.inject.Inject

@HiltViewModel
class MemeInfoViewModel @Inject constructor(private val memesRepository: MemesRepository)
    : ViewModel() {
    fun getMemeInfo(memeId: Int): LiveData<MemeInfo> = liveData {
        val memeInfo = memesRepository.getMemeInfo(memeId)
        emit(memeInfo)
    }
}
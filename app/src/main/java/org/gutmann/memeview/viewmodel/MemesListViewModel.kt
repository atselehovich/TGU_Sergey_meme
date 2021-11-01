package org.gutmann.memeview.viewmodel

import androidx.lifecycle.LiveData

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import org.gutmann.memeview.model.MemeInfo
import org.gutmann.memeview.model.MemesRepository
import javax.inject.Inject

@HiltViewModel
class MemesListViewModel @Inject constructor(private val memesRepository: MemesRepository)
    : ViewModel() {
    val memesList: LiveData<List<MemeInfo>> = liveData {
        val memesList = loadMemesList()
        emit(memesList)
    }

    private suspend fun loadMemesList() : List<MemeInfo> = memesRepository.getMemes()
}
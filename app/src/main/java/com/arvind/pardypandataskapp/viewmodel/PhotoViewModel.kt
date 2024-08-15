package com.arvind.pardypandataskapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arvind.pardypandataskapp.domain.models.PhotoDataResponse
import com.arvind.pardypandataskapp.domain.repository.PhotoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotoViewModel @Inject constructor(
    private val repository: PhotoRepository
) : ViewModel() {
    private val _photos = MutableLiveData<List<PhotoDataResponse>>()
    val photos: LiveData<List<PhotoDataResponse>> get() = _photos

    fun fetchPhotos() {
        viewModelScope.launch {
            val photos = repository.getPhotos()
            _photos.postValue(photos)
        }
    }
}

package com.arvind.pardypandataskapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.arvind.pardypandataskapp.domain.models.PhotoDataResponse
import com.arvind.pardypandataskapp.domain.repository.PhotoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PhotoViewModel @Inject constructor(
    private val repository: PhotoRepository
) : ViewModel() {
    val photos: LiveData<List<PhotoDataResponse>> = repository.getPhotos()
}

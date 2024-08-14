package com.arvind.pardypandataskapp.domain.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.arvind.pardypandataskapp.data.local.PhotoDao
import com.arvind.pardypandataskapp.data.remote.APIService
import com.arvind.pardypandataskapp.domain.models.PhotoDataResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PhotoRepository @Inject constructor(
    private val apiService: APIService,
    private val photoDao: PhotoDao
) {
    fun getPhotos(): LiveData<List<PhotoDataResponse>> = liveData {
        val photos = apiService.getPhotos()
        photoDao.insertAll(photos)
        emitSource(photoDao.getPhotos())
    }
}

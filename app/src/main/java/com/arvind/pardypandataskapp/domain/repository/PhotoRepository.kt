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
    suspend fun getPhotos(): List<PhotoDataResponse> {
        return try {
            val response = apiService.getPhotos()
            photoDao.insertAll(response)
            response
        } catch (e: Exception) {
            photoDao.getPhotos()
        }
    }
}

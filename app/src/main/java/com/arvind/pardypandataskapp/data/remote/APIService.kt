package com.arvind.pardypandataskapp.data.remote

import com.arvind.pardypandataskapp.domain.models.PhotoDataResponse
import retrofit2.http.GET

interface APIService {
    @GET("photos")
    suspend fun getPhotos(): List<PhotoDataResponse>
}
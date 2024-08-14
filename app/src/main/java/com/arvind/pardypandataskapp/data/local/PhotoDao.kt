package com.arvind.pardypandataskapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.arvind.pardypandataskapp.domain.models.PhotoDataResponse

@Dao
interface PhotoDao {
    @Query("SELECT * FROM Photos")
    fun getPhotos(): LiveData<List<PhotoDataResponse>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(photos: List<PhotoDataResponse>)
}

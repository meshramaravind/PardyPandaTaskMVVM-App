package com.arvind.pardypandataskapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.arvind.pardypandataskapp.domain.models.PhotoDataResponse

@Database(entities = [PhotoDataResponse::class], version = 1)
abstract class PhotoDatabase : RoomDatabase() {
    abstract fun photoDao(): PhotoDao
}

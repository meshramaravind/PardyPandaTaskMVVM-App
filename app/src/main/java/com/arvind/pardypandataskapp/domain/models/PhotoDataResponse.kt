package com.arvind.pardypandataskapp.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "photos")
data class PhotoDataResponse(
    @PrimaryKey var id: Int? = null,
    @SerializedName("albumId") var albumId: Int? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("url") var url: String? = null,
    @SerializedName("thumbnailUrl") var thumbnailUrl: String? = null,
)

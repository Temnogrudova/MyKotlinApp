package com.example.myapplication.models
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

class PixabayModel {
    data class Images(
        @SerializedName("total") val total: Int?,
        @SerializedName("totalHits") val totalHits: Int?,
        @SerializedName("hits") val hits: ArrayList<Image>?
    )
    @Parcelize
    data class Image(
        @SerializedName("id") val id: Long?,
        @SerializedName("previewURL") val previewURL: String?,
        @SerializedName("webformatURL") val webformatURL: String?
    ): Parcelable
    @Parcelize
    class Hits: ArrayList<Image>(), Parcelable
}
package com.example.myapplication.network

import com.example.myapplication.models.PixabayModel
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService{
    @GET("api")
    suspend fun getImages(@Query("key") key: String, @Query("page") page: String, @Query("per_page") perPage: String) : PixabayModel.Images
}
package com.example.myapplication.repos

import androidx.lifecycle.LiveData
import com.example.myapplication.models.PixabayModel

/**
 * A repository pattern for retrieving MyNews related data
 */
interface ImagesRepository {


    /**
     * Holds the [MyNews.TagsBank] data
     */
    val mImagesLiveData: LiveData<PixabayModel.Images?>


    /**
     * Fetch and updates the data of [myNewsTotalTagsLiveData]. the query state is published threw [myNewsTotalTagsQueryStateLiveData]
     */
    suspend fun fetchImages(page:String)

}
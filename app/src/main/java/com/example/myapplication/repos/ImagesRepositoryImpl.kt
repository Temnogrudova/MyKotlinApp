package com.goldtouch.ynet.repos.my_news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.repos.ImagesRepository
import com.example.myapplication.models.PixabayModel
import com.example.myapplication.network.ImagesWebService
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject
import javax.inject.Singleton

@ActivityRetainedScoped
class ImagesRepositoryImpl  @Inject constructor(
    private val imagesWebService: ImagesWebService
): ImagesRepository {

    private val imagesMtbl: MutableLiveData<PixabayModel.Images?> = MutableLiveData(null)


    override val mImagesLiveData: LiveData<PixabayModel.Images?>
        get() = imagesMtbl


    override suspend fun fetchImages(page: String) {
        val article = try {
        imagesWebService.getImages("12733826-a936f08aa28501a4cc137e2d7", page, "5")
        } catch (cause: Throwable) {
            throw cause
        }
        imagesMtbl.value = article
    }
}
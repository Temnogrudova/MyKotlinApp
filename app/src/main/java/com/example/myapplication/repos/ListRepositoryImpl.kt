package com.example.myapplication.repos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.models.PixabayModel
import com.example.myapplication.network.WebService
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class ListRepositoryImpl  @Inject constructor(
    private val webService: WebService
): ListRepository {

    private val imagesMtbl: MutableLiveData<PixabayModel.Images?> = MutableLiveData(null)


    override val mImagesLiveData: LiveData<PixabayModel.Images?>
        get() = imagesMtbl


    override suspend fun fetchItems(page: String) {
        val item = try {
        webService.getImages("12733826-a936f08aa28501a4cc137e2d7", page, "5")
        } catch (cause: Throwable) {
            throw cause
        }
        imagesMtbl.value = item
    }
}
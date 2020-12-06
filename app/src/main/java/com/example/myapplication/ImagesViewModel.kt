package com.example.myapplication

import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.navigation.findNavController
import com.example.myapplication.models.PixabayModel
import com.example.myapplication.repos.ImagesRepository
import com.example.myapplication.repos.LocalDb
import com.goldtouch.ynet.repos.saved_articles.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ImagesViewModel  @ViewModelInject constructor(
    private val imagesRepository: ImagesRepository,
    private val db: LocalDb
): ViewModel()
{
    val imagesLiveData =   Transformations.map(imagesRepository.mImagesLiveData) { mImages ->
        mImages?.hits ?:  PixabayModel.Hits()
    }
    private val favouriteArticles = MutableLiveData<ArrayList<Item>>()

    fun fetchImages(page: String) {
        viewModelScope.launch {
            imagesRepository.fetchImages(page)
        }
    }
    fun saveArticle(item: PixabayModel.Image) {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                db.savedArticle().insert(Item(item =  item, type = Item.TYPE_FAVOURITE))
            }
        }
    }
    fun onItemClick(v: View, url: String) {
        v.findNavController().navigate(ImagesFragmentDirections.actionImagesFragmentToImageFragment(url))
    }
    fun onLongClick(item: PixabayModel.Image) {
        saveArticle(item)
    }
    fun getFavouritesArticles(): LiveData<ArrayList<Item>> {
        fetchFavouriteArticles()
        return favouriteArticles
    }
    private fun fetchFavouriteArticles() {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                val allArticles = db.savedArticle().getFavouriteArticles() as ArrayList
                favouriteArticles.postValue(allArticles)
            }
        }
    }
}
package com.example.myapplication.ui

import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.navigation.findNavController
import com.example.myapplication.models.PixabayModel
import com.example.myapplication.repos.ListRepository
import com.example.myapplication.repos.LocalDb
import com.example.myapplication.repos.saved_items.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListViewModel  @ViewModelInject constructor(
        private val listRepository: ListRepository,
        private val db: LocalDb
): ViewModel()
{
    val imagesLiveData =   Transformations.map(listRepository.mImagesLiveData) { mImages ->
        mImages?.hits ?:  PixabayModel.Hits()
    }

    private val favouriteItems = MutableLiveData<ArrayList<Item>>()

    fun fetchImages(page: String) {
        viewModelScope.launch {
            listRepository.fetchItems(page)
        }
    }

    private fun saveItem(item: PixabayModel.Image) {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                db.savedItem().insert(Item(item =  item, type = Item.TYPE_FAVOURITE))
            }
        }
    }

    fun onItemClick(v: View, url: String) {
        v.findNavController().navigate(ListFragmentDirections.actionListFragmentToItemFragment(url))
    }

    fun onLongClick(item: PixabayModel.Image) {
        saveItem(item)
    }

    fun getFavouritesItems(): LiveData<ArrayList<Item>> {
        fetchFavouriteItems()
        return favouriteItems
    }

    private fun fetchFavouriteItems() {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                val items = db.savedItem().getFavouriteItems() as ArrayList
                favouriteItems.postValue(items)
            }
        }
    }
}
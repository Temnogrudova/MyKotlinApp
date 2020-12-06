package com.example.myapplication

import android.view.View
import android.widget.Toast
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import com.example.myapplication.models.PixabayModel
import com.example.myapplication.repos.ImagesRepository
import kotlinx.coroutines.launch

class ImagesViewModel  @ViewModelInject constructor(
    private val imagesRepository: ImagesRepository
): ViewModel()
{
    val imagesLiveData =   Transformations.map(imagesRepository.mImagesLiveData) { mImages ->
        mImages?.hits ?:  PixabayModel.Hits()
    }

    fun fetchImages(page: String) {
        viewModelScope.launch {
            imagesRepository.fetchImages(page)
        }
    }

    fun onItemClick(v: View, url: String) {
        v.findNavController().navigate(ImagesFragmentDirections.actionImagesFragmentToImageFragment(url))
    }
}
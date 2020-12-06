package com.example.myapplication.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.BaseFragment
import com.example.myapplication.R
import com.example.myapplication.models.PixabayModel
import com.example.myapplication.utils.loadImage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_image.view.*

@AndroidEntryPoint
class ImageFragment: BaseFragment(R.layout.fragment_image) {
    private val safeArgs:ImageFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.image.loadImage( safeArgs.url, 0, 0)
    }
}
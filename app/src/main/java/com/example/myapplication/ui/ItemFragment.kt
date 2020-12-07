package com.example.myapplication.ui

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.example.myapplication.R
import com.example.myapplication.utils.loadImage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_item.view.*

@AndroidEntryPoint
class ItemFragment: BaseFragment(R.layout.fragment_item) {
    private val safeArgs:ItemFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.image.loadImage( safeArgs.url, 0, 0)
    }
}
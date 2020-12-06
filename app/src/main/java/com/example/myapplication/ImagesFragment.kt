package com.example.myapplication

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.models.PixabayModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_images.*

@AndroidEntryPoint
class ImagesFragment: BaseFragment (R.layout.fragment_images) {
    private val viewModel: ImagesViewModel by activityViewModels()
    private var images: ArrayList<PixabayModel.Image?> = arrayListOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserves()
        viewModel.fetchImages("1")
        rvImages.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = ImagesAdapter(images, adapterInteractionCallbackImpl)
        }
    }
    private val adapterInteractionCallbackImpl = object: ImagesAdapter.InteractionListener {
        override fun onItemClick(v: View, url: String) {
            viewModel.onItemClick(v, url)
        }
    }

    private fun initObserves() {
        viewModel.imagesLiveData.observe(viewLifecycleOwner, Observer {
            this.images.clear()
            this.images.addAll(it)
            rvImages.adapter?.notifyDataSetChanged()
        })
    }
}
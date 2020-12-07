package com.example.myapplication.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.models.PixabayModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_list.*

@AndroidEntryPoint
class ListFragment: BaseFragment(R.layout.fragment_list) {
    private val listViewModel: ListViewModel by activityViewModels()
    private var items: ArrayList<PixabayModel.Image?> = arrayListOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserves()
        listViewModel.fetchImages("1")
        rvImages.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = ListAdapter(
                items,
                adapterInteractionCallbackImpl
            )
        }
    }
    private val adapterInteractionCallbackImpl = object:
        ListAdapter.InteractionListener {
        override fun onItemClick(v: View, url: String) {
            listViewModel.onItemClick(v, url)
        }

        override fun onLongClick(item: PixabayModel.Image) {
            listViewModel.onLongClick(item)
        }
    }

    private fun initObserves() {
        listViewModel.imagesLiveData.observe(viewLifecycleOwner, Observer {
            this.items.clear()
            this.items.addAll(it)
            rvImages.adapter?.notifyDataSetChanged()
        })
        listViewModel.getFavouritesItems().observe(viewLifecycleOwner, Observer {
            it.forEach {
                Log.e("Katya", it.previewURL)
            }
        })
    }
}
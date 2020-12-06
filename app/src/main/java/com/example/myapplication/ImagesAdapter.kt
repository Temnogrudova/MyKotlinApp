package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.models.PixabayModel
import com.example.myapplication.utils.loadImage
import kotlinx.android.synthetic.main.item.view.*

class ImagesAdapter(
    private var images: List<PixabayModel.Image?>,
    private val interactionListener: InteractionListener
) : RecyclerView.Adapter<ImagesAdapter.BaseViewHolder>() {
    interface InteractionListener {
        fun onItemClick(v: View, url: String)
        fun onLongClick(item: PixabayModel.Image)
    }

    private lateinit var inflater: LayoutInflater
  //  private var images: List<PixabayModel.Image?> = arrayListOf()

    abstract class BaseViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView)
    inner class ImagesViewHolder (itemView: View) : BaseViewHolder(itemView) {
       // private val inflater = LayoutInflater.from(itemView.context)
        private var item: PixabayModel.Image? = null
        fun bind(item: PixabayModel.Image?) {
            this.item = item
            item?.previewURL?.let {
                itemView.imageView.loadImage(it, 0, 0)

                itemView.setOnClickListener { v ->
                    interactionListener.onItemClick(itemView, it)
                }
                itemView.setOnLongClickListener { v->
                    interactionListener.onLongClick(item)
                    return@setOnLongClickListener true
                }
             /*   itemView.setOnLongClickListener { v ->
                    interactionListener.onLongClick(item)
                }*/
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        if (!::inflater.isInitialized)
            inflater = LayoutInflater.from(parent.context)

        val view: View = inflater.inflate(R.layout.item, parent, false)
        return ImagesViewHolder(view)
    }

    override fun getItemCount(): Int = images.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        (holder as ImagesViewHolder).bind(images[position])
    }
}

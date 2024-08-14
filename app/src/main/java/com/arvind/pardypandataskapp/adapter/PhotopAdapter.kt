package com.arvind.pardypandataskapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.arvind.pardypandataskapp.R
import com.arvind.pardypandataskapp.domain.models.PhotoDataResponse
import com.bumptech.glide.Glide

class PhotoAdapter(private val photos: List<PhotoDataResponse>) :
    RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {

    class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.photoTitle)
        private val image: ImageView = itemView.findViewById(R.id.photoImage)

        fun bind(photo: PhotoDataResponse) {
            title.text = photo.title
            Glide.with(itemView.context).load(photo.url).into(image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false)
        return PhotoViewHolder(view)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(photos[position])
    }

    override fun getItemCount(): Int = photos.size
}

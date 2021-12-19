package com.example.vuelos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class GalleryAdapter(private var images: List<Int>): RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>(){

    // El layout manager invoca este método para renderizar cada elemento
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.gallery_list, parent, false)
        return GalleryViewHolder(view)
    }

    // Este método asigna valores para cada elemento de la lista
    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        holder.bind(images[position])
    }

    class GalleryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val featuredImage: ImageView = view.findViewById(R.id.image)

        fun bind(image: Int) {
            featuredImage.setImageResource(image)
        }
    }

    // Cantidad de elementos del RecyclerView
    // Puede ser más complejo (por ejm, si implementamos filtros o búsquedas)
    override fun getItemCount() = images.size

}

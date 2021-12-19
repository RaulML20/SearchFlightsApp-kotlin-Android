package com.example.vuelos

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GalleryAdapterR(context : Context, images : List<Int>, text : List<String>) : BaseAdapter() {
    private var context: Context? = null
    private var images: List<Int>
    private var text: List<String>

    init{
        this.context = context
        this.images = images
        this.text = text
    }
    override fun getCount() : Int {
        return images.size
    }

    override fun getItem(i : Int) : Any {
        return i
    }

    override fun getItemId(i : Int) : Long{
        return i.toLong()
    }

    override fun getView(position : Int, convertView : View?, parent : ViewGroup) : View? {
        val viewHolder : ViewHolder
        var convertV = convertView
        if (convertView == null) {
            viewHolder = ViewHolder()
            val inflater = LayoutInflater.from(context)
            convertV = inflater.inflate(R.layout.gallery_list2, parent, false)
            viewHolder.images = convertV.findViewById(R.id.image) as ImageView
            viewHolder.text = convertV.findViewById(R.id.textoC) as TextView
            convertV?.tag = viewHolder
        } else {
            viewHolder = convertV?.tag as ViewHolder
        }
        viewHolder.images?.setImageResource(images[position])
        viewHolder.text?.text = text[position]
        return convertV
    }

    private class ViewHolder {
        var images : ImageView? = null
        var text : TextView? = null
    }
}
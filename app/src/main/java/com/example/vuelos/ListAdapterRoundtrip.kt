package com.example.vuelos

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class ListAdapterRoundtrip(context : Context, textL : List<String>, textD : List<String>, textP : List<String>, stars : List<Int>, icon : List<Int>, date : List<String>, sits : List<String>) : BaseAdapter() {
    private var context: Context? = null
    private var textL: List<String>
    private var textD: List<String>
    private var textP: List<String>
    private var date: List<String>
    private var stars: List<Int>
    private var icon: List<Int>
    private var sits: List<String>

    init{
        this.context = context
        this.textL = textL
        this.textD = textD
        this.textP = textP
        this.date = date
        this.stars = stars
        this.icon = icon
        this.sits = sits
    }

    override fun getCount() : Int {
        return icon.size
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
            convertV = inflater.inflate(R.layout.listavuelos_roundtrip, parent, false)
            viewHolder.textL = convertV.findViewById(R.id.textF) as TextView
            viewHolder.icon = convertV.findViewById(R.id.company) as ImageView
            viewHolder.stars = convertV.findViewById(R.id.stars) as ImageView
            viewHolder.textD = convertV.findViewById(R.id.textD) as TextView
            viewHolder.textP = convertV.findViewById(R.id.textP) as TextView
            viewHolder.date = convertV.findViewById(R.id.date) as TextView
            viewHolder.sits= convertV.findViewById(R.id.sits) as TextView
            convertV?.tag = viewHolder
        } else {
            viewHolder = convertV?.tag as ViewHolder
        }
        viewHolder.textL?.text = textL[position]
        viewHolder.textD?.text = textD[position]
        viewHolder.textP?.text = textP[position]
        viewHolder.date?.text = date[position]
        viewHolder.sits?.text = sits[position]
        viewHolder.stars?.setImageResource(stars[position])
        viewHolder.icon?.setImageResource(icon[position])
        return convertV
    }

    private class ViewHolder {
        var textL : TextView? = null
        var textD : TextView? = null
        var textP : TextView? = null
        var date : TextView? = null
        var sits : TextView? = null
        var stars : ImageView? = null
        var icon : ImageView? = null
    }
}
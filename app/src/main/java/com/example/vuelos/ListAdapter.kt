package com.example.vuelos

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class ListAdapter(context : Context, textL : List<String>, icon : List<Int>) : BaseAdapter() {
    private var context: Context? = null
    private var textL: List<String>
    private var icon: List<Int>

    init{
        this.context = context
        this.textL = textL
        this.icon = icon
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
        val result : View
        if (convertView == null) {
            viewHolder = ViewHolder()
            val inflater = LayoutInflater.from(context)
            convertV = inflater.inflate(R.layout.listavuelos, parent, false)
            viewHolder.txtName = convertV.findViewById(R.id.textF) as TextView
            viewHolder.icon = convertV.findViewById(R.id.company) as ImageView
            result = convertV
            convertV?.tag = viewHolder
        } else {
            viewHolder = convertV?.tag as ViewHolder
            result = convertV
        }
        viewHolder.txtName?.text = textL[position]
        viewHolder.icon?.setImageResource(icon[position])
        return convertV
    }

    private class ViewHolder {
        var txtName : TextView? = null
        var txtVersion : TextView? = null
        var icon : ImageView? = null
    }
}
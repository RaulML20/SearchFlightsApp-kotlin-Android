package com.example.vuelos

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ReservationsAdapter(context : Context, textL : List<String>, textD : List<String>, textP : List<String>, date : List<String>, id : List<String>, round : List<String>, cities : List<String>) : BaseAdapter() {
    private var context: Context? = null
    private var textL: List<String>
    private var textD: List<String>
    private var textP: List<String>
    private var date: List<String>
    private var id: List<String>
    private var round: List<String>
    private var cities: List<String>

    init{
        this.context = context
        this.textL = textL
        this.textD = textD
        this.textP = textP
        this.id = id
        this.date = date
        this.round = round
        this.cities = cities
    }

    override fun getCount() : Int {
        return id.size
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
            convertV = inflater.inflate(R.layout.listareservations, parent, false)
            viewHolder.textL = convertV.findViewById(R.id.textF) as TextView
            viewHolder.id = convertV.findViewById(R.id.id) as TextView
            viewHolder.textD = convertV.findViewById(R.id.textD) as TextView
            viewHolder.textP = convertV.findViewById(R.id.textP) as TextView
            viewHolder.date = convertV.findViewById(R.id.date) as TextView
            viewHolder.round = convertV.findViewById(R.id.round) as TextView
            viewHolder.cities = convertV.findViewById(R.id.cities) as TextView
            convertV?.tag = viewHolder
        } else {
            viewHolder = convertV?.tag as ViewHolder
        }
        viewHolder.textL?.text = textL[position]
        viewHolder.textD?.text = textD[position]
        viewHolder.textP?.text = textP[position]
        viewHolder.date?.text = date[position]
        viewHolder.id?.text = id[position]
        viewHolder.round?.text = round[position]
        viewHolder.cities?.text = cities[position]
        return convertV
    }

    private class ViewHolder {
        var textL : TextView? = null
        var textD : TextView? = null
        var textP : TextView? = null
        var date : TextView? = null
        var id : TextView? = null
        var round : TextView? = null
        var cities : TextView? = null
    }
}
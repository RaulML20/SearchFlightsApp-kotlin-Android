package com.example.vuelos

class Itinerarios (val idI : Int, val origin : String, val destination : String, val bPrice: Int){
    companion object{val itinerates = mutableListOf<Itinerarios>()}
}
package com.example.vuelos

class Reservas (val idR : Int, val idF : Int, val name: String, val ret : Boolean){
    companion object{val reservations = mutableListOf<Reservas>()}
}
package com.example.vuelos

import java.time.LocalDate
import java.util.*

class Vuelos (val idF : Int, val date : LocalDate, val hourDeaperture : Int, val hourArrival : Int, val sits : Int, val sitsAviable : Int, val aereonav : String, val company : String, val idI : Int){
    companion object{val flights = mutableListOf<Vuelos>()}
}

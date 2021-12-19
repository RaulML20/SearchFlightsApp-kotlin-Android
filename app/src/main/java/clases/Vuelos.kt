package clases

import java.time.LocalDate

class Vuelos (val idF : Int, val date : LocalDate, val hourDeaperture : Int, val hourArrival : Int, val sits : Int, var sitsAviable : Int, val aereonav : String, val company : String, val idI : Int, val minutesA : Int, val minutesR : Int, val stops : Int){
    companion object{val flights = mutableListOf<Vuelos>()}
}

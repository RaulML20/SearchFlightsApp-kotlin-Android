package com.example.vuelos

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate

interface Datos {
    //FUNCION CON LOS VALORES DEL PROGRAMA
    @RequiresApi(Build.VERSION_CODES.O)
    fun datos(){
        val admin = Usuarios("Admin","Admin", "1234")
        val raul = Usuarios("Raul","Meizoso Lopez", "1234")
        val itinerario1 = Itinerarios(1, "Madrid", "Barcelona", 300)
        val itinerario2 = Itinerarios(2, "Madrid", "Roma", 600)
        var date = LocalDate.of(2022, 12, 30)
        var date2 = LocalDate.of(2023, 10, 1)
        val vuelo1 = Vuelos(10, date, 15, 20,200,145, "2b","Iberia",1)
        val vuelo2= Vuelos(11, date, 17, 23,200,148, "2b","AirEuropa",1)
        val vuelo3 = Vuelos(12, date, 18, 1,200,149, "2b","Avianca",1)
        val vuelo4 = Vuelos(13, date, 19, 3,200,150, "2b","Iberia",1)
        val vuelo5 = Vuelos(14, date, 20, 4,200,152, "2b","AirEuropa",1)
        val vuelo6 = Vuelos(15, date, 23, 7,200,154, "2b","Avianca",1)
        val vuelo7 = Vuelos(16, date2, 15, 20,200,145, "2b","Iberia",2)
        val vuelo8= Vuelos(17, date2, 17, 23,200,148, "2b","AirEuropa",2)
        val vuelo9 = Vuelos(18, date2, 18, 1,200,149, "2b","Avianca",2)
        val vuelo10 = Vuelos(18, date2, 18, 1,200,149, "2b","Avianca",2)
        Vuelos.flights.add(vuelo1)
        Vuelos.flights.add(vuelo2)
        Vuelos.flights.add(vuelo3)
        Vuelos.flights.add(vuelo4)
        Vuelos.flights.add(vuelo5)
        Vuelos.flights.add(vuelo6)
        Vuelos.flights.add(vuelo7)
        Vuelos.flights.add(vuelo8)
        Vuelos.flights.add(vuelo9)
        Itinerarios.itinerates.add(itinerario1)
        Itinerarios.itinerates.add(itinerario2)
        Usuarios.users.add(admin)
        Usuarios.users.add(raul)
    }
}
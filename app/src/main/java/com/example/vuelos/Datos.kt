package com.example.vuelos

import clases.Itinerarios
import clases.Usuarios
import clases.Vuelos
import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate

interface Datos {
    //FUNCION CON LOS VALORES DEL PROGRAMA
    @RequiresApi(Build.VERSION_CODES.O)
    fun datos(){
        val admin = Usuarios(1,"Admin","Admin", "1234", "admin@gmail.com")
        val raul = Usuarios(2,"Raul","Meizoso Lopez", "1234", "meizosolopezraul@gmail.com")
        val itinerario1 = Itinerarios(1, "Madrid", "Barcelona", 100, "Madrid", "Barcelona", 40.41844849379473, -3.7042859512556667, 41.38828143982857, 2.164296915053347)
        val itinerario2 = Itinerarios(2, "Madrid", "Roma", 600, "Madrid", "Roma", 40.41844849379473, -3.7042859512556667, 41.902065023536906, 12.495478871475273)
        val itinerario3 = Itinerarios(3, "London", "Venice", 800, "London", "Venice", 51.514459162967064, -0.1290703868329177, 45.435277590011516, 12.340032763229267)
        val itinerario4 = Itinerarios(4, "Paris", "Tokio", 800, "Paris", "Tokio", 48.87102955169289, 2.337763717294489, 35.80540664454881, 137.98155987658387)

        var date = LocalDate.of(2021, 11, 27)
        var date2 = LocalDate.of(2021, 11, 28)
        var date3 = LocalDate.of(2021, 11, 29)
        var date4 = LocalDate.of(2021, 11, 30)
        var date6 = LocalDate.of(2021, 11, 10)
        val vuelo1 = Vuelos(10, date, 15, 20,200,145, "2b","Iberia",1, 30, 20, 1)
        val vuelo2= Vuelos(11, date, 17, 23,200,148, "2b","AirEuropa",1, 55, 10, 0)
        val vuelo3 = Vuelos(12, date, 18, 1,200,149, "2b","Avianca",1, 20, 15, 0)
        val vuelo4 = Vuelos(13, date, 19, 3,200,150, "2b","Iberia",1, 20, 35, 0)
        val vuelo5 = Vuelos(14, date, 20, 4,200,152, "2b","AirEuropa",1, 12, 45, 0)
        val vuelo6 = Vuelos(15, date, 23, 7,200,154, "2b","Avianca",1, 15, 50, 1)
        val vuelo7 = Vuelos(16, date2, 15, 20,200,145, "2b","Iberia",2, 35, 50, 1)
        val vuelo8= Vuelos(17, date2, 17, 23,200,148, "2b","AirEuropa",2, 15, 30, 1)
        val vuelo9 = Vuelos(18, date2, 18, 1,200,149, "2b","Avianca",2, 30, 10, 2)
        val vuelo10 = Vuelos(19, date2, 18, 1,200,149, "2b","Avianca",2, 40, 10, 3)
        val vuelo11 = Vuelos(20, date2, 23, 7,200,154, "2b","Avianca",1, 15, 50, 1)
        val vuelo12 = Vuelos(25, date3, 23, 7,200,154, "2b","Avianca",1, 15, 50, 1)
        val vuelo13 = Vuelos(26, date4, 23, 7,200,154, "2b","Avianca",1, 15, 50, 1)
        val vuelo14 = Vuelos(27, date6, 23, 7,200,154, "2b","Avianca",1, 15, 50, 1)
        val vuelo15 = Vuelos(28, date, 15, 20,200,145, "2b","Iberia",3, 30, 20, 1)
        val vuelo16= Vuelos(29, date, 17, 23,200,148, "2b","AirEuropa",3, 55, 10, 1)
        val vuelo17 = Vuelos(30, date, 15, 20,200,145, "2b","Iberia",4, 30, 20, 1)
        val vuelo18= Vuelos(31, date, 17, 23,200,148, "2b","AirEuropa",4, 55, 10, 1)
        val vuelo19 = Vuelos(32, date2, 23, 7,200,154, "2b","Avianca",4, 15, 50, 1)
        Vuelos.flights.add(vuelo1)
        Vuelos.flights.add(vuelo2)
        Vuelos.flights.add(vuelo3)
        Vuelos.flights.add(vuelo4)
        Vuelos.flights.add(vuelo5)
        Vuelos.flights.add(vuelo6)
        Vuelos.flights.add(vuelo7)
        Vuelos.flights.add(vuelo8)
        Vuelos.flights.add(vuelo9)
        Vuelos.flights.add(vuelo10)
        Vuelos.flights.add(vuelo11)
        Vuelos.flights.add(vuelo12)
        Vuelos.flights.add(vuelo13)
        Vuelos.flights.add(vuelo14)
        Vuelos.flights.add(vuelo15)
        Vuelos.flights.add(vuelo16)
        Vuelos.flights.add(vuelo17)
        Vuelos.flights.add(vuelo18)
        Vuelos.flights.add(vuelo19)
        Itinerarios.itinerates.add(itinerario1)
        Itinerarios.itinerates.add(itinerario2)
        Itinerarios.itinerates.add(itinerario3)
        Itinerarios.itinerates.add(itinerario4)
        Usuarios.users.add(admin)
        Usuarios.users.add(raul)
    }
}
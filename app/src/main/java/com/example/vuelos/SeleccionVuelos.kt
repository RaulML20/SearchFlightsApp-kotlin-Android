package com.example.vuelos

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import clases.Itinerarios.Companion.itinerates
import clases.Reservas

import clases.Vuelos
import clases.Vuelos.Companion.flights
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import clases.Reservas.Companion.reservations
import com.example.vuelos.databinding.ActivitySeleccionVuelosBinding
import java.time.format.DateTimeFormatter
import androidx.core.content.ContextCompat

class SeleccionVuelos : AppCompatActivity() {

    private lateinit var binding : ActivitySeleccionVuelosBinding


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeleccionVuelosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //INICIALIZACIÓN DE VARIABLES Y LLAMADA A LOS COMPONENTES
        binding.itinerate.setBackgroundResource(R.drawable.gradient)
        var contSearch = 0
        var idRegister  = 0
        var bPrice  = 0
        val listF = mutableListOf<Vuelos>()
        val flightsList = mutableListOf<String>()
        val flightsList2 = mutableListOf<String>()
        val price = mutableListOf<String>()
        val retDate = mutableListOf<String>()
        val date = mutableListOf<String>()
        val images = mutableListOf<Int>()
        val stars = mutableListOf<Int>()
        val sits = mutableListOf<String>()
        val fly = mutableListOf<Vuelos>()

        //NOTIFICATION

        //MAP NAVIGATON ORIGIN CITY
        var coords1 = 0.0
        var coords2 = 0.0

        for(it in itinerates){
            if(it.origin == intent.getStringExtra("from") && it.destination == intent.getStringExtra("to")){
                coords1 = it.coords1O
                coords2 = it.coords20
                break
            }
        }

        binding.cityO.setOnClickListener {
            val mapCO = Intent(this, MapCityO::class.java).apply {
                putExtra("coords1", coords1)
                putExtra("coords2", coords2)
            }
            startActivity(mapCO)
        }


        //MAP NAVIGATION DESTINATION CITY
        var coords1D = 0.0
        var coords2D = 0.0

        for(it in itinerates){
            if(it.origin == intent.getStringExtra("from") && it.destination == intent.getStringExtra("to")){
                coords1D = it.coords1D
                coords2D = it.coords2D
                break
            }
        }

        binding.cityD.setOnClickListener {
            val mapCD = Intent(this, MapCityD::class.java).apply {
                putExtra("coords1D", coords1D)
                putExtra("coords2D", coords2D)
            }
            startActivity(mapCD)
        }

        //DATOS DEL ITINERARIO
        binding.origin.text = "Origin: ${intent.getStringExtra("from")}"
        binding.destination.text = "Destination: ${intent.getStringExtra("to")}"

        //CREACIÓN DE UN ADAPTADOR VACÍO PARA VACIAR LA LISTA DE VUELOS
        if(intent.getBooleanExtra("roundtrip", false)){
            val adaptador1 = ListAdapterRoundtrip(this, flightsList, flightsList2, price, stars, images, date, sits)
            binding.flights.adapter = adaptador1
        }else{
            val adaptador1 = ListAdapter(this, flightsList, flightsList2, price, stars, images, date, sits)
            binding.flights.adapter = adaptador1
        }


        val dateS = intent.getStringExtra("depart")

        //Selección de vuelos según sus datos
        for(i in itinerates){
            if(i.origin == intent.getStringExtra("from") && i.destination == intent.getStringExtra("to")){
                idRegister = i.idI
                bPrice = i.bPrice
            }
        }

        flights.forEach {
            val formattedDate: String = it.date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
            val condicion1 = it.idI == idRegister && formattedDate == dateS && !intent.getBooleanExtra("roundtrip", false)
            val condicion2 = it.idI == idRegister && formattedDate == dateS && intent.getBooleanExtra("roundtrip", true)

            if(condicion1 && (intent.getIntExtra("stops", 1) == it.stops)) {
                val result = "${it.hourDeaperture}:${it.minutesA}"
                val result2 = "${it.hourArrival}:${it.minutesR}"
                val result3 = "$bPrice€"
                flightsList.add(result)
                flightsList2.add(result2)
                price.add(result3)
                date.add(formattedDate)
                listF.add(it)
                sits.add("${it.sitsAviable} Sits")
                fly.add(it)
                retDate.add(intent.getStringExtra("retDate").toString())
                contSearch++


                when (it.company) {
                    "Iberia" -> {
                        stars.add(R.drawable.stars4)
                        images.add(R.drawable.iberia)
                    }
                    "AirEuropa" -> {
                        stars.add(R.drawable.stars4)
                        images.add(R.drawable.aireuropa)
                    }
                    else -> {
                        stars.add(R.drawable.stars3)
                        images.add(R.drawable.avianca)
                    }
                }
            }else if(condicion2 && (intent.getIntExtra("stops", 1) == it.stops)){
                val result = "${it.hourDeaperture}:${it.minutesA}\n\n${it.hourArrival}:${it.minutesR}"
                val result2 ="${it.hourArrival}:${it.minutesR}\n\n${it.hourDeaperture}:${it.minutesA}"
                val result3 = "${bPrice * 2}€"
                flightsList.add(result)
                flightsList2.add(result2)
                price.add(result3)
                val dateR = "$formattedDate | ${intent.getStringExtra("retDate").toString()}"
                date.add(dateR)
                listF.add(it)
                sits.add("${it.sitsAviable} Sits")
                fly.add(it)
                retDate.add(intent.getStringExtra("retDate").toString())
                contSearch++

                when (it.company) {
                    "Iberia" -> {
                        stars.add(R.drawable.stars4)
                        images.add(R.drawable.iberia)
                    }
                    "AirEuropa" -> {
                        stars.add(R.drawable.stars4)
                        images.add(R.drawable.aireuropa)
                    }
                    else -> {
                        stars.add(R.drawable.stars3)
                        images.add(R.drawable.avianca)
                    }
                }
            }
        }

        //Añadir datos a la lista de reservas de vuelos
        if(intent.getBooleanExtra("roundtrip", false)){
            val adaptador = ListAdapterRoundtrip(this, flightsList, flightsList2, price, stars, images, date, sits)
            binding.flights.adapter = adaptador
        }else{
            val adaptador = ListAdapter(this, flightsList, flightsList2, price, stars, images, date, sits)
            binding.flights.adapter = adaptador
        }

        //Establece por pantalla un texto con el número de vuelos encontrados.
        binding.search.text = "$contSearch Search Results"

        //Función que comprueba si las fotos del itinerario coinciden con la información pasada
        itinerates.forEach {
            if(it.photoO == intent.getStringExtra("from") && it.photoD == intent.getStringExtra("to")){
                val path1 = "@drawable/${it.photoO.lowercase()}"
                val path2 = "@drawable/${it.photoD.lowercase()}"
                val image = resources.getIdentifier(path1, null, packageName)
                val image2 = resources.getIdentifier(path2, null, packageName)
                val imagen = ContextCompat.getDrawable(applicationContext, image)
                val imagen2 = ContextCompat.getDrawable(applicationContext, image2)

                binding.cityO.setImageDrawable(imagen)
                binding.cityD.setImageDrawable(imagen2)
            }
        }

        //Dialogo para realizar las reservas.
        binding.flights.setOnItemClickListener{adapterView: AdapterView<*>, view1: View, i: Int, l: Long ->
            val alertDialog: AlertDialog = this.let {
                val builder = AlertDialog.Builder(it)
                builder.setMessage(R.string.title)
                builder.apply {
                    setPositiveButton(R.string.reserva
                    ) { dialog, id ->
                        val reservation: Reservas

                        for(f in flights){
                            if(f.idF == fly[i].idF){
                                f.sitsAviable -= intent.getIntExtra("passengers", 1)
                                break
                            }
                        }
                        val num = (1..10000).random()
                        val name = intent.getStringExtra("name").toString()


                        reservation = if (intent.getBooleanExtra("roundtrip", false)) {
                            Reservas(num, listF[i].idF, name, true, intent.getIntExtra("passengers", 1), retDate[i])
                        } else {
                            Reservas(num, listF[i].idF, name, false, intent.getIntExtra("passengers", 1), retDate[i])
                        }
                        reservations.add(reservation)
                        finish()
                    }
                    setNegativeButton(R.string.cancelar
                    ) { dialog, id ->
                        dialog.dismiss()
                    }
                }
                builder.create()
                builder.show()
            }
        }
    }
}
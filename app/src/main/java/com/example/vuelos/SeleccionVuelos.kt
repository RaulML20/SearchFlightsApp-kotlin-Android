package com.example.vuelos

import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.vuelos.Itinerarios.Companion.itinerates
import com.example.vuelos.Vuelos.Companion.flights
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import com.example.vuelos.Reservas.Companion.reservations
import java.lang.Math.random
import java.time.format.DateTimeFormatter

class SeleccionVuelos : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seleccion_vuelos)

        //INICIALIZACIÓN DE VARIABLES Y LLAMADA A LOS COMPONENTES
        var idRegister : Int = 0
        var bPrice : Int = 0
        val listF = mutableListOf<Vuelos>()
        val flightsList = mutableListOf<String>()
        val images = mutableListOf<Int>()
        val flight = findViewById<View>(R.id.flights) as ListView
        val origin = findViewById<View>(R.id.origin) as TextView
        val destination = findViewById<View>(R.id.destination) as TextView

        //DATOS DEL ITINERARIO
        origin.text = "Origen: ${intent.getStringExtra("from")}"
        destination.text = "Destino: ${intent.getStringExtra("to")}"

        //CREACIÓN DE UN ADAPTADOR VACÍO PARA VACIAR LA LISTA DE VUELOS
        val adaptador1 = ListAdapter(this, flightsList, images)
        flight.adapter = adaptador1


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
            val s = intent.getStringExtra("retDate")
            val condicion1 = it.idI == idRegister && formattedDate == dateS && !intent.getBooleanExtra("roundtrip", false)
            val condicion2 = it.idI == idRegister && formattedDate == dateS && intent.getBooleanExtra("roundtrip", true)

            if(condicion1) {
                val result = "\nAvión: ${it.idF}\nfecha: ${formattedDate}\nhora salida: ${it.hourDeaperture}\nhora llegada: ${it.hourArrival}\nprecio: $bPrice"
                flightsList.add(result)
                listF.add(it)

                when (it.company) {
                    "Iberia" -> {
                        images.add(R.drawable.iberia)
                    }
                    "AirEuropa" -> {
                        images.add(R.drawable.aireuropa)
                    }
                    else -> {
                        images.add(R.drawable.avianca)
                    }
                }
            }else if(condicion2){
                val result = "\nAvión: ${it.idF}\nfecha salida: $formattedDate        IDA Y VUELTA\nfecha regreso: $s\nhora salida: ${it.hourDeaperture}\nhora llegada: ${it.hourArrival}\nprecio: ${bPrice*2}"
                flightsList.add(result)
                listF.add(it)

                when (it.company) {
                    "Iberia" -> {
                        images.add(R.drawable.iberia)
                    }
                    "AirEuropa" -> {
                        images.add(R.drawable.aireuropa)
                    }
                    else -> {
                        images.add(R.drawable.avianca)
                    }
                }
            }
        }

        //Añadir datos a la lista de reservas de vuelos
        val adaptador = ListAdapter(this, flightsList, images)
        flight.adapter = adaptador

        flight.setOnItemClickListener{adapterView: AdapterView<*>, view1: View, i: Int, l: Long ->
            val alertDialog: AlertDialog = this.let {
                val builder = AlertDialog.Builder(it)
                builder.setMessage(R.string.title)
                builder.apply {
                    setPositiveButton(R.string.reserva,
                        DialogInterface.OnClickListener { dialog, id ->
                            val reservation : Reservas
                            val num = (1..10000).random()
                            val name = intent.getStringExtra("name").toString()

                            reservation = if(intent.getBooleanExtra("roundtrip", false)){
                                Reservas(num, listF[i].idF, name, true)
                            }else{
                                Reservas(num, listF[i].idF, name, false)
                            }

                            reservations.add(reservation)
                            finish()
                        })
                    setNegativeButton(R.string.cancelar,
                        DialogInterface.OnClickListener { dialog, id ->

                        })
                }
                builder.create()
                builder.show()
            }
        }
    }
}
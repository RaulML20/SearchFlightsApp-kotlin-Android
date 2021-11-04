package com.example.vuelos

import android.content.DialogInterface
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import com.example.vuelos.Reservas.Companion.reservations
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ListaReservas : AppCompatActivity() {
    private val lista = mutableListOf<String>()
    private val listD = mutableListOf<LocalDate>()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_reservas)

        //IMPRIMIR LISTA DE RESERVAS
        val listaR = findViewById<View>(R.id.reservationList) as ListView
        var bPrice = 0
        var idR : Int
        var idReservation : Int
        var ret : Boolean
        var name : String

        reservations.forEach {
            idR = it.idF
            ret = it.ret
            idReservation = it.idR
            name = it.name

            for(v in Vuelos.flights) {
                for(e in Itinerarios.itinerates){
                    if(e.idI == v.idI){
                        bPrice = e.bPrice
                    }
                }
                if(v.idF == idR && name == intent.getStringExtra("name")){
                    val formattedDate = v.date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                    val retDate = intent.getStringExtra("retDate")

                    if(ret){
                        val result = "\nID de la reserva: ${idReservation }\nfecha salida: ${formattedDate}\n" +"fecha regreso: ${retDate}\nhora salida: ${v.hourDeaperture}\nhora llegada: ${v.hourArrival}\nprecio: ${bPrice*2}\nIDA Y VUELTA"
                        lista.add(result)
                        listD.add(v.date)
                        break
                    }else{
                        val result = "\nID de la reserva: ${idReservation }\nfecha: ${formattedDate}\nhora salida: ${v.hourDeaperture}\nhora llegada: ${v.hourArrival}\nprecio: $bPrice"
                        lista.add(result)
                        listD.add(v.date)
                        break
                    }
                }
            }
        }
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lista)
        listaR.adapter = adapter

        //Eliminar o modificar reserva.
        listaR.setOnItemClickListener{adapter : AdapterView<*>, view: View, p : Int, l : Long ->
            val alertDialog : AlertDialog = this.let{
                val builder = AlertDialog.Builder(it)
                builder.setMessage(R.string.modificar)
                builder.apply {
                    setPositiveButton(R.string.cancel,
                        DialogInterface.OnClickListener { dialog, id ->
                            val date = LocalDate.now()
                            val date2 = listD[p]
                            if(date2.isAfter(date)){
                                reservations.removeAt(p)
                            }
                            finish()
                        })
                    setNegativeButton(R.string.mod,
                        DialogInterface.OnClickListener { dialog, id ->

                        })
                }
                builder.create()
                builder.show()
            }
        }
    }
}
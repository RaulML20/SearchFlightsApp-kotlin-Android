package com.example.vuelos

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import clases.Itinerarios
import clases.Reservas
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import clases.Itinerarios.Companion.itinerates
import clases.Reservas.Companion.reservations
import clases.Vuelos.Companion.flights
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.*

class ListaReservas : AppCompatActivity() {
    private val textL = mutableListOf<String>()
    private val textD = mutableListOf<String>()
    private val textP = mutableListOf<String>()
    private val id = mutableListOf<String>()
    private val round = mutableListOf<String>()
    private val cities = mutableListOf<String>()
    private val date = mutableListOf<String>()
    private val listD = mutableListOf<LocalDate>()
    private val retModify = mutableListOf<Boolean>()
    private val retModifyT = mutableListOf<String>()
    private val numP = mutableListOf<Int>()
    private val idI = mutableListOf<Int>()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_reservas)

        //Variable contador que contará los vuelos mostrados para mostrarlo por pantalla
        val listaR = findViewById<View>(R.id.reservationList) as ListView
        var bPrice = 0
        var idR : Int
        var idReservation : Int
        var ret : Boolean
        var name : String
        var cityO : String = ""
        var cityD : String = ""
        var idIt = 0

        reservations.forEach { it ->
            idR = it.idF
            ret = it.ret
            idReservation = it.idR
            name = it.name

            for(v in flights) {
                for(e in itinerates){
                    if(idR == v.idF){
                        if(e.idI == v.idI){
                            bPrice = e.bPrice
                            idIt = e.idI
                            break
                        }
                    }
                }
                if(v.idF == idR && name == intent.getStringExtra("name")){
                    val formattedDate = v.date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                    val retDate = intent.getStringExtra("retDate")

                    itinerates.forEach { iti ->
                        if(iti.idI == v.idI){
                            cityO = iti.origin
                            cityD = iti.destination
                        }
                    }
                    if(ret){
                        textL.add(v.hourDeaperture.toString()+":"+v.minutesA)
                        textD.add(v.hourArrival.toString()+":"+v.minutesR)
                        textP.add(((bPrice*2)*it.numP).toString()+"€")
                        date.add(formattedDate+"\n"+it.retDate)
                        retModifyT.add(it.retDate)
                        id.add("ID: $idReservation")
                        round.add("ROUNDTRIP")
                        listD.add(v.date)
                        retModify.add(it.ret)
                        idI.add(idIt)
                        numP.add(it.numP)
                        cities.add("$cityO | $cityD")
                        break
                    }else{
                        textL.add(v.hourDeaperture.toString()+":"+v.minutesA)
                        textD.add(v.hourArrival.toString()+":"+v.minutesR)
                        textP.add((bPrice*it.numP).toString()+"€")
                        date.add(formattedDate)
                        id.add("ID: $idReservation")
                        retModifyT.add(it.retDate)
                        round.add("")
                        listD.add(v.date)
                        retModify.add(it.ret)
                        idI.add(idIt)
                        numP.add(it.numP)
                        cities.add("$cityO | $cityD")
                        break
                    }
                }
            }
        }
        val adapter = ReservationsAdapter(this, textL, textD, textP, date, id, round, cities)
        listaR.adapter = adapter

        //Eliminar o modificar reserva.
        listaR.setOnItemClickListener{adapter : AdapterView<*>, view: View, p : Int, l : Long ->
            val alertDialog : AlertDialog = this.let{
                val builder = AlertDialog.Builder(it)
                builder.setMessage(R.string.modificar)
                builder.apply {
                    setPositiveButton(R.string.cancel
                    ) { dialog, id ->
                        val date = LocalDate.now()
                        val date2 = listD[p]
                        if (date2.isAfter(date)) {
                            reservations.removeAt(p)
                        }
                        finish()
                    }
                    setNegativeButton(R.string.mod
                    ) { dialog, id ->
                        val date = LocalDate.now()
                        val date2 = listD[p]
                        if (date2.isAfter(date)) {
                            val num = (1..10000).random()
                            val name2 = intent.getStringExtra("name")
                            flights.forEach { flight ->
                                val dateActually = LocalDate.now()
                                if (flight.date.isAfter(dateActually) && flight.date != listD[p] && idI[p] == flight.idI) {
                                    val rModify = Reservas(
                                        num,
                                        flight.idF,
                                        name2.toString(),
                                        retModify[p],
                                        numP[p],
                                        retModifyT[p]
                                    )
                                    reservations[p] = rModify
                                    finish()
                                }
                            }
                        }
                    }
                    setNeutralButton("Set alarm"){ dialog, id ->
                        setAlarm(listD[p].dayOfMonth-1, listD[p].monthValue-1, listD[p].year)
                    }
                }
                builder.create()
                builder.show()
            }
        }
    }

    //Función, establecer notificacion el día antes del vuelo.
    private fun setAlarm(day : Int, month : Int, year : Int){
        val objAlarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        val objCalendar = Calendar.getInstance()
        objCalendar[Calendar.YEAR] = year
        objCalendar[Calendar.MONTH] = month
        objCalendar[Calendar.DAY_OF_MONTH] = day
        objCalendar[Calendar.AM_PM] = Calendar.AM

        val alamShowIntent = Intent(this, AlarmReceiver::class.java)
        val alarmPendingIntent = PendingIntent.getActivity(this, 1, alamShowIntent, PendingIntent.FLAG_IMMUTABLE)

        objAlarmManager[AlarmManager.RTC_WAKEUP, objCalendar.timeInMillis] = alarmPendingIntent
    }
}
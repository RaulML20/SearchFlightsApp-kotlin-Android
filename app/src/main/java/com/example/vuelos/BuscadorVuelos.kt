package com.example.vuelos

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.view.isVisible

class BuscadorVuelos : AppCompatActivity() {

    //CONTADOR PASAJEROS
    private var count = 0

    //INICIALIZACION DE VARIABLES
    val retDate by lazy {findViewById<View>(R.id.ret) as EditText}
    val fly by lazy {findViewById<View>(R.id.vuelos) as Button}
    val history by lazy {findViewById<View>(R.id.history) as RadioButton}
    val roundtrip by lazy {findViewById<View>(R.id.Roundtrip) as RadioButton}
    val nonstop by lazy {findViewById<View>(R.id.nonstop) as RadioButton}
    val user by lazy {findViewById<View>(R.id.usuario) as TextView}
    val from by lazy {findViewById<View>(R.id.from) as EditText}
    val to by lazy {findViewById<View>(R.id.to) as EditText}
    val depart by lazy {findViewById<View>(R.id.depart) as EditText}
    val oneway by lazy {findViewById<View>(R.id.Oneway) as RadioButton}
    val ret by lazy {findViewById<View>(R.id.ret) as EditText}
    val passengers by lazy {findViewById<View>(R.id.passenger) as TextView}
    val onestop by lazy {findViewById<View>(R.id.onestop) as RadioButton}
    val stops by lazy {findViewById<View>(R.id.more) as RadioButton}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buscador_vuelos)

        //ESTABLECER COLORES INICIALES Y TEXTO DE USUARIO
        user.text = "Hola ${intent.getStringExtra("name")} ${intent.getStringExtra("lastname")}"
        roundtrip.setBackgroundColor(Color.rgb(120, 96, 251))
        nonstop.setBackgroundColor(Color.rgb(120, 96, 251))
        fly.setBackgroundColor(Color.rgb(93, 181, 86))

        //EVENTO DEL BOTÓN BUSCAR VUELOS
        fly.setOnClickListener {
            val comprobaciones = comprobaciones()
            if(comprobaciones){
                val name = intent.getStringExtra("name")
                val intent = Intent(this, SeleccionVuelos::class.java).apply{
                    putExtra("from", from.text.toString())
                    putExtra("to", to.text.toString())
                    putExtra("depart", depart.text.toString())
                    putExtra("name", name)
                    putExtra("retDate", retDate.text.toString())
                    if(roundtrip.isChecked){
                        putExtra("roundtrip", true)
                    }
                }
                startActivity(intent)
            }else Toast.makeText(this, "Faltan campos por rellenar", Toast.LENGTH_SHORT).show()

        }

        //IMPRIMIR LISTA DE RESERVAS
        history.setOnClickListener{
            history.setBackgroundColor(Color.rgb(120, 96, 251))
            roundtrip.setBackgroundColor(Color.WHITE)
            oneway.setBackgroundColor(Color.WHITE)

            val name = intent.getStringExtra("name")

            val lista = Intent(this, ListaReservas::class.java).apply{
                putExtra("retDate", retDate.text.toString())
                putExtra("name", name)
            }
            startActivity(lista)
        }
    }

    //DESACTIVAR CAMPOS AL HACER CLICK
    fun desactivarCampos(v: View){
        when(v.id){
            R.id.Roundtrip -> {ret.isVisible = true; roundtrip.setBackgroundColor(Color.rgb(120, 96, 251))
                oneway.setBackgroundColor(Color.WHITE); history.setBackgroundColor(Color.WHITE)}
            R.id.Oneway -> {ret.isVisible = false; oneway.setBackgroundColor(Color.rgb(120, 96, 251))
                history.setBackgroundColor(Color.WHITE); roundtrip.setBackgroundColor(Color.WHITE)}
        }
    }

    //SUMAR Y RESTAR PASAJEROS
    fun sumar(v : View){
        if(count < 20) {
            count++
            passengers.text = Integer.valueOf(count).toString()
        }
    }

    fun restar(v: View){
        if(count > 0){
            count--
            passengers.text = Integer.valueOf(count).toString()
        }
    }

    //ESTABLECER EL COLOR AL CAMBIAR DE OPCIONES
    fun color(v: View){
        when(v.id){
            R.id.nonstop -> {nonstop.setBackgroundColor(Color.rgb(120, 96, 251))
                onestop.setBackgroundColor(Color.WHITE); stops.setBackgroundColor(Color.WHITE)}
            R.id.onestop -> {nonstop.setBackgroundColor(Color.WHITE)
                onestop.setBackgroundColor(Color.rgb(120, 96, 251)); stops.setBackgroundColor(Color.WHITE)}
            R.id.more -> {nonstop.setBackgroundColor(Color.WHITE)
                onestop.setBackgroundColor(Color.WHITE); stops.setBackgroundColor(Color.rgb(120, 96, 251))}
        }
    }
    //Comprobaciones de campos
    fun comprobaciones() : Boolean{
        val condicion = if(roundtrip.isChecked){from.text.toString() == "" || to.text.toString() == "" || depart.text.toString() == "" || ret.text.toString() == "" || passengers.text.toString().toInt() == 0}else{
            from.text.toString() == "" || to.text.toString() == "" || depart.text.toString() == "" || passengers.text.toString().toInt() == 0
        }
        return !condicion
    }
}
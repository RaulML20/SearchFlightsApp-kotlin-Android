package com.example.vuelos

import android.content.Intent
import android.graphics.Color.rgb
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import com.example.vuelos.Itinerarios.Companion.itinerates
import com.example.vuelos.Usuarios.Companion.users
import com.example.vuelos.Vuelos.Companion.flights
import java.time.LocalDate

class MainActivity : AppCompatActivity(), Datos {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //VALORES DEL PROGRAMA
        datos()

        //ESTABLECER COLORES A LA INTERFAZ DE USUARIO E INICIALIZAR VARIABLES
        val background = findViewById<View>(R.id.fondo)
        val login = findViewById<View>(R.id.sesion) as Button
        val name = findViewById<View>(R.id.name) as EditText
        val password = findViewById<View>(R.id.password) as EditText

        var acces = false
        var lastname = ""

        background.setBackgroundResource(R.drawable.fondo)
        login.setBackgroundColor(rgb (25,25,112))

        //ACCIÓN BOTON INICIAR SESION
        login.setOnClickListener {
            for(i in users){
                acces = i.name == name.text.toString() && i.password == password.text.toString()
                lastname = i.lastname
                if(acces){
                    break
                }
            }

            if(acces){
                val init = Intent(this, BuscadorVuelos::class.java).apply {
                    putExtra("name",name.text.toString())
                    putExtra("lastname", lastname)
                }
                startActivity(init)
            }else Toast.makeText(this, "Nombre o contraseña incorrecta", Toast.LENGTH_SHORT).show()
        }
    }
}

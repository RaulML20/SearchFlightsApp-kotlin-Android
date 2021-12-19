package com.example.vuelos

import android.content.Intent
import android.graphics.Color.rgb
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import clases.Usuarios.Companion.users
import com.example.vuelos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), Datos {

    private lateinit var binding : ActivityMainBinding
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //VALORES DEL PROGRAMA
        datos()

        //ESTABLECER COLORES A LA INTERFAZ DE USUARIO E INICIALIZAR VARIABLES
        var acces = false
        var lastname = ""

        //ACCIÓN BOTON INICIAR SESION
        binding.sesion.setOnClickListener {
            for(i in users){
                acces = i.name == binding.name.text.toString().replace(" ", "") && i.password == binding.password.text.toString()
                lastname = i.lastname
                if(acces){
                    break
                }
            }

            if(acces){
                val init = Intent(this, AnimacionInicio::class.java).apply {
                    putExtra("name",binding.name.text.toString().replace(" ", ""))
                    putExtra("lastname", lastname)
                }
                startActivity(init)
                finish()
            }else {
                val alertDialog: AlertDialog = this.let {
                    val builder = AlertDialog.Builder(it)
                    builder.setMessage(R.string.eIncorrect)
                    builder.apply {
                        setPositiveButton(R.string.ok) { dialog, id -> dialog.dismiss() }
                    }
                    builder.create()
                    builder.show()
                }
            }
        }

        //Acción botón register
        binding.register.setOnClickListener{
            val register = Intent(this, Register::class.java)
            startActivity(register)
        }
    }
}

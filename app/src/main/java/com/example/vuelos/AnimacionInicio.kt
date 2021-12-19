package com.example.vuelos

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import com.bumptech.glide.Glide

import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import com.example.vuelos.databinding.ActivityAnimacionInicioBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AnimacionInicio : AppCompatActivity() {

    private lateinit var binding : ActivityAnimacionInicioBinding

    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimacionInicioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Creacion de animaciones
        val animation: Animation = AnimationUtils.loadAnimation(
            applicationContext,
            R.anim.fade_out
        )
        val rotate: Animation = AnimationUtils.loadAnimation(applicationContext, R.anim.rotate)

        val urlGif = "https://gifsanimados.de/img-gifsanimados.de/h/humo/steam-lrg-clr.gif"
        val uri: Uri = Uri.parse(urlGif)
        Glide.with(applicationContext).load(uri).into(binding.humo)

        //Inicio de las animaciones
        binding.airplane.startAnimation(animation)
        binding.humo.startAnimation(animation)

        binding.maleta.startAnimation(rotate)

        //Corrutina para iniciar un intent
        lifecycleScope.launch{
            delay(1000L)
            finish()
            initT()
        }
    }

    //Intent que se ejecuta con una animacion de izquierda a derecha
    private fun initT(){
        val intent = Intent(this, BuscadorVuelos::class.java).apply {
            putExtra("name", intent.getStringExtra("name"))
            putExtra("lastname", intent.getStringExtra("lastname"))
        }
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }
}
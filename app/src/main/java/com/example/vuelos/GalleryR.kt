package com.example.vuelos

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import com.example.vuelos.databinding.ActivityGalleryRBinding

class GalleryR : AppCompatActivity() {
    private lateinit var binding: ActivityGalleryRBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGalleryRBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Creación arrays de las listas de la galería
        val lista1 = listOf(R.mipmap.madrid1r, R.mipmap.londres1r, R.mipmap.roma2r, R.mipmap.japon2r)
        val lista2 = listOf(R.mipmap.barcelona2r, R.mipmap.venecia2r, R.mipmap.paris2r, R.mipmap.suiza2r)

        val lista1T = listOf("Madrid", "London", "Roma", "Tokio")
        val lista2T = listOf("Barcelona", "Venice", "Paris", "Switzerland")

        //Asignación de los arrays a las listas
        val adapter = GalleryAdapterR(this, lista1, lista1T)
        val adapter2 = GalleryAdapterR(this, lista2, lista2T)
        binding.cityGal.adapter = adapter
        binding.cityGal2.adapter = adapter2

        //Intents que me llevan a una pantalla con información en concreto de la ciudad elegida.
        //Clicks sobre la primera lista
        var cityG = Intent(this, Gallery::class.java)
        binding.cityGal.setOnItemClickListener{adapterView: AdapterView<*>, view1: View, i: Int, l: Long ->
            when (adapterView.getItemAtPosition(i)) {
                0 -> {
                    cityG.putExtra("city", "Madrid")
                }
                1 -> {
                    cityG.putExtra("city", "London")
                }
                2 -> {
                    cityG.putExtra("city", "Roma")
                }
                3 -> {
                    cityG.putExtra("city", "Tokio")
                }
            }
            startActivity(cityG)
        }

        //Clicks sobre la segunda lista
        binding.cityGal2.setOnItemClickListener{adapterView: AdapterView<*>, view1: View, i: Int, l: Long ->
            when (adapterView.getItemAtPosition(i)) {
                0 -> {
                    cityG.putExtra("city", "Barcelona")
                }
                1 -> {
                    cityG.putExtra("city", "Venice")
                }
                2 -> {
                    cityG.putExtra("city", "Paris")
                }
                3 -> {
                    cityG.putExtra("city", "Switzerland")
                }
            }
            startActivity(cityG)
        }
    }
}
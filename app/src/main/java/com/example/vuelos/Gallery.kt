package com.example.vuelos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import clases.Itinerarios.Companion.itinerates
import com.example.vuelos.databinding.ActivityGalleryBinding

class Gallery : AppCompatActivity() {
    private lateinit var binding: ActivityGalleryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Creación del array que se asignara al adaptador de la lista en función de los datos pasados desde el anterior activity.
        val images : List<Int> = when {
            intent.getStringExtra("city") == "Madrid" -> {
                binding.photo.setImageResource(R.drawable.madrid)
                binding.ciudad.setText(R.string.m)
                binding.text.setText(R.string.madrid)
                listOf(R.mipmap.madrid1r, R.mipmap.madrid2r, R.mipmap.madrid3r, R.mipmap.madrid5r)
            }
            intent.getStringExtra("city") == "Barcelona" -> {
                binding.photo.setImageResource(R.drawable.barcelona)
                binding.ciudad.setText(R.string.b)
                binding.text.setText(R.string.barcelona)
                listOf(R.mipmap.barcelona1r, R.mipmap.barcelona2r, R.mipmap.barcelona3r, R.mipmap.barcelona4r)
            }
            intent.getStringExtra("city") == "London" -> {
                binding.photo.setImageResource(R.drawable.london)
                binding.ciudad.setText(R.string.l)
                binding.text.setText(R.string.londres)
                listOf(R.mipmap.londres1r, R.mipmap.londres2r, R.mipmap.londres3r, R.mipmap.londres4r)
            }
            intent.getStringExtra("city") == "Roma" -> {
                binding.photo.setImageResource(R.drawable.roma)
                binding.ciudad.setText(R.string.r)
                binding.text.setText(R.string.roma)
                listOf(R.mipmap.roma2r, R.mipmap.roma3r, R.mipmap.roma4r, R.mipmap.roma5r)
            }
            intent.getStringExtra("city") == "Venice" -> {
                binding.photo.setImageResource(R.drawable.venice)
                binding.ciudad.setText(R.string.v)
                binding.text.setText(R.string.venecia)
                listOf(R.mipmap.venecia2r, R.mipmap.venecia3r, R.mipmap.venecia4r)
            }
            intent.getStringExtra("city") == "Tokio" -> {
                binding.photo.setImageResource(R.drawable.tokio)
                binding.ciudad.setText(R.string.t)
                binding.text.setText(R.string.japon)
                listOf(R.mipmap.japon2r, R.mipmap.japon3r, R.mipmap.japon4r)
            }
            intent.getStringExtra("city") == "Paris" -> {
                binding.photo.setImageResource(R.drawable.paris)
                binding.ciudad.setText(R.string.p)
                binding.text.setText(R.string.paris)
                listOf(R.mipmap.paris2r, R.mipmap.paris3r, R.mipmap.paris4r)
            }
            else -> {
                binding.photo.setImageResource(R.drawable.suiza1)
                binding.ciudad.setText(R.string.s)
                binding.text.setText(R.string.suiza)
                listOf(R.mipmap.suiza2r, R.mipmap.suiza4r, R.mipmap.suiza5r)
            }
        }

        //Se establecen los valores del array al RecyclerView
        val adapter = GalleryAdapter(images)
        binding.city.setHasFixedSize(true)
        binding.city.isNestedScrollingEnabled = false
        binding.city.adapter = adapter


        //int ancho = 120;
        //int alto = 120;
        //LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ancho, alto);
        //myImageView.setLayoutParams(params)
    }
}

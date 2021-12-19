package com.example.vuelos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapCityO : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map_city)
        createMapFragment()
    }

    //Mapa Fragment Ciudades
    private fun createMapFragment() {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.fragmentMap) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    //ZOOM al lugar marcado
    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        val cityO = LatLng(intent.getDoubleExtra("coords1", 0.0), intent.getDoubleExtra("coords2", 0.0))
        map.addMarker(MarkerOptions().position(cityO).title("Origin city!"))

        map.animateCamera(
            CameraUpdateFactory.newLatLngZoom(cityO, 18f),4000,null)
    }
}



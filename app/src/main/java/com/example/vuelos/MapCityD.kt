package com.example.vuelos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapCityD : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var map: GoogleMap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map_city_d)
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
        val cityO = LatLng(intent.getDoubleExtra("coords1D", 0.0), intent.getDoubleExtra("coords2D", 0.0))
        map.addMarker(MarkerOptions().position(cityO).title("Destination city!"))

        map.animateCamera(
            CameraUpdateFactory.newLatLngZoom(cityO, 18f),4000,null)
    }
}
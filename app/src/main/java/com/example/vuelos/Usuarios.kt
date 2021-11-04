package com.example.vuelos

class Usuarios (val name : String, val lastname : String, val password : String){
    companion object{val users = mutableListOf<Usuarios>()}
}
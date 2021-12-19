package clases

class Usuarios(val idN : Int, val name : String, val lastname : String, val password : String, val email : String){
    companion object{val users = mutableListOf<Usuarios>()}
}
package clases

class Reservas (val idR : Int, val idF : Int, val name: String, val ret : Boolean, val numP : Int, val retDate: String){
    companion object{val reservations = mutableListOf<Reservas>()}
}
package clases

class Itinerarios (val idI : Int, val origin : String, val destination : String, val bPrice: Int, val photoO : String, val photoD : String, val coords1O : Double, val coords20 : Double, val coords1D : Double, val coords2D : Double){
    companion object{val itinerates = mutableListOf<Itinerarios>()}
}
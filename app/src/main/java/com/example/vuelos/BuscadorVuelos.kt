package com.example.vuelos

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import com.example.vuelos.databinding.ActivityBuscadorVuelosBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
import android.text.SpannableStringBuilder
import androidx.appcompat.app.AlertDialog
import clases.Itinerarios.Companion.itinerates
import java.text.SimpleDateFormat
import java.time.ZoneId


class BuscadorVuelos : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    private lateinit var binding : ActivityBuscadorVuelosBinding
    private var dateC = false

    //MENU
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_options, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.gal -> {
                val intent = Intent(this, GalleryR::class.java)
                startActivity(intent)
                true
            }
            R.id.logout -> {
                val login = Intent(this, MainActivity::class.java)
                startActivity(login)
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    //CONTADOR PASAJEROS
    private var count = 0
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBuscadorVuelosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //ESTABLECER COLORES INICIALES Y TEXTO DE USUARIO
        binding.usuario.text = "Hello ${intent.getStringExtra("name")} ${intent.getStringExtra("lastname")}"
        binding.nonstop.setBackgroundResource(R.drawable.circular_two)
        binding.Roundtrip.setBackgroundResource(R.drawable.circular_two)
        binding.vuelos.setBackgroundColor(Color.rgb(97,107,192))
        //EVENTO DEL BOTÓN BUSCAR VUELOS
        binding.vuelos.setOnClickListener {
            val comprobaciones = comprobaciones()
            val comCity = comArraysCities()
            if(comprobaciones && comCity){
                val name = intent.getStringExtra("name")
                val int = Intent(this, CargaVuelos::class.java).apply{
                    putExtra("from", binding.from.text.toString().replace(" ", ""))
                    putExtra("to", binding.to.text.toString().replace(" ", ""))
                    putExtra("depart", binding.depart.text.toString())
                    putExtra("name", name)
                    putExtra("passengers", binding.passenger.text.toString().toInt())
                    putExtra("retDate", binding.ret.text.toString())
                    if(binding.Roundtrip.isChecked) {
                        putExtra("roundtrip", true)
                        putExtra("r", true)
                    }else{
                        putExtra("r", false)
                    }

                    when {
                        binding.nonstop.isChecked -> {
                            putExtra("stops", 0)
                        }
                        binding.onestop.isChecked -> {
                            putExtra("stops", 1)
                        }
                        else -> {
                            putExtra("stops", 2)
                        }
                    }
                }
                //Corrutina simular carga
                lifecycleScope.launch{
                    delay(500L)
                    startActivity(int)
                }
            }else if(comprobaciones && !comCity){
                val alertDialog: AlertDialog = this.let {
                    val builder = AlertDialog.Builder(it)
                    builder.setMessage(R.string.cityI)
                    builder.apply {
                        setPositiveButton(R.string.ok) { dialog, id -> dialog.dismiss() }
                    }
                    builder.create()
                    builder.show()
                }
            }else if(!comprobaciones && comCity){
                val alertDialog: AlertDialog = this.let {
                    val builder = AlertDialog.Builder(it)
                    builder.setMessage(R.string.comp)
                    builder.apply {
                        setPositiveButton(R.string.ok) { dialog, id -> dialog.dismiss() }
                    }
                    builder.create()
                    builder.show()
                }
            }else{
                val alertDialog: AlertDialog = this.let {
                    val builder = AlertDialog.Builder(it)
                    builder.setMessage(R.string.comp)
                    builder.apply {
                        setPositiveButton(R.string.ok) { dialog, id -> dialog.dismiss() }
                    }
                    builder.create()
                    builder.show()
                }
            }
        }

        //IMPRIMIR LISTA DE RESERVAS
        binding.history.setOnClickListener{
            binding.history.setBackgroundResource(R.drawable.circular_two)
            binding.Roundtrip.setBackgroundResource(R.drawable.circular)
            binding.Oneway.setBackgroundResource(R.drawable.circular)

            val name = intent.getStringExtra("name")

            val lista = Intent(this, ListaReservas::class.java).apply{
                putExtra("retDate", binding.ret.text.toString())
                putExtra("name", name)
            }
            startActivity(lista)
        }

        binding.depart.setOnClickListener {
            dateC = false
            val datePicker: DialogFragment = DatePicker()
            datePicker.show(supportFragmentManager, "selector fecha")
        }

        binding.ret.setOnClickListener{
            dateC = true
            val datePicker: DialogFragment = DatePicker()
            datePicker.show(supportFragmentManager, "selector fecha")
        }
    }

    //DESACTIVAR CAMPOS AL HACER CLICK
    fun desactivarCampos(v: View){
        when(v.id){
            R.id.Roundtrip -> {binding.ret.isVisible = true; binding.DEPART2.isVisible = true; binding.Roundtrip.setBackgroundResource(R.drawable.circular_two)
                binding.Oneway.setBackgroundResource(R.drawable.circular); binding.history.setBackgroundResource(R.drawable.circular)}
            R.id.Oneway -> {binding.ret.isVisible = false; binding.DEPART2.isVisible = false; binding.Oneway.setBackgroundResource(R.drawable.circular_two)
                binding.history.setBackgroundResource(R.drawable.circular); binding.Roundtrip.setBackgroundResource(R.drawable.circular)}
        }
    }

    //SUMAR Y RESTAR PASAJEROS
    fun sumar(v : View){
        if(count < 20) {
            count++
            binding.passenger.text = Integer.valueOf(count).toString()
        }
    }

    fun restar(v: View){
        if(count > 0){
            count--
            binding.passenger.text = Integer.valueOf(count).toString()
        }
    }

    //ESTABLECER EL COLOR AL CAMBIAR DE OPCIONES
    fun color(v: View){
        when(v.id){
            R.id.nonstop -> {binding.nonstop.setBackgroundResource(R.drawable.circular_two)
                binding.onestop.setBackgroundResource(R.drawable.circular); binding.more.setBackgroundResource(R.drawable.circular)}
            R.id.onestop -> {binding.nonstop.setBackgroundResource(R.drawable.circular)
                binding.onestop.setBackgroundResource(R.drawable.circular_two); binding.more.setBackgroundResource(R.drawable.circular)}
            R.id.more -> {binding.nonstop.setBackgroundResource(R.drawable.circular)
                binding.onestop.setBackgroundResource(R.drawable.circular); binding.more.setBackgroundResource(R.drawable.circular_two)}
        }
    }
    //Comprobaciones de campos
    @SuppressLint("SimpleDateFormat")
    private fun comprobaciones() : Boolean{
        var date : Date? = null
        var date2: Date? = null
        if(binding.depart.text.isNotEmpty()){
            date = SimpleDateFormat("dd/mm/yyyy").parse(binding.depart.text.toString())
        }
        if(binding.ret.text.isNotEmpty()){
            date2 = SimpleDateFormat("dd/mm/yyyy").parse(binding.ret.text.toString())
        }
        val dateL = date?.toInstant()?.atZone(ZoneId.systemDefault())?.toLocalDate()
        val date2L = date2?.toInstant()?.atZone(ZoneId.systemDefault())?.toLocalDate()

        if(binding.Roundtrip.isChecked){
            if(binding.from.text.isEmpty() || binding.to.text.isEmpty()|| binding.depart.text.isEmpty()|| binding.ret.text.isEmpty() || binding.passenger.text.toString().toInt() == 0 || binding.history.isChecked || (dateL!!.isAfter(date2L))){
                return false
            }
        }else{
            if(binding.from.text.isEmpty() || binding.to.text.isEmpty()|| binding.depart.text.isEmpty() || binding.passenger.text.toString().toInt() == 0 || binding.history.isChecked){
                return false
            }
        }
        return true
    }

    private fun comArraysCities() : Boolean{
        itinerates.forEach {
            if(it.origin == binding.from.text.toString().replace(" ","") && it.destination == binding.to.text.toString().replace(" ","")){
                return true
            }
        }
        return false
    }

    //Date picker
    override fun onDateSet(view: android.widget.DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val date= "${dayOfMonth}/${month+1}/${year}"
        val editable: Editable = SpannableStringBuilder(date)
        if(!dateC){
            binding.depart.text = editable
        }else{
            binding.ret.text = editable
        }
    }
}
package com.example.vuelos

import clases.Usuarios
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import clases.Usuarios.Companion.users
import com.example.vuelos.databinding.ActivityRegisterBinding

class Register : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Comprobación de si los datos del registro son correctos o no
        binding.signup.setOnClickListener {
            var repeat = true
            var validate = validations()
            if(!isEmail(binding.email)){
                val alertDialog: AlertDialog = this.let {
                    val builder = AlertDialog.Builder(it)
                    builder.setMessage(R.string.eIncorrect)
                    builder.apply {
                        setPositiveButton(
                            R.string.ok
                        ) { dialog, id -> dialog.dismiss() }
                    }
                    builder.create()
                    builder.show()
                }
                repeat = false
            }
            if (validate && isEmail(binding.email)) {
                val num = (3..10000).random()
                val newUser = Usuarios(
                    num,
                    binding.name.text.toString().replace(" ", ""),
                    binding.lastname.text.toString().replace(" ", ""),
                    binding.password.text.toString(),
                    binding.email.toString().replace(" ", "")
                )
                users.add(newUser)
                Toast.makeText(this, "Registered user", Toast.LENGTH_SHORT).show()
                finish()
            }else if(!validate || repeat){
                val alertDialog: AlertDialog = this.let {
                    val builder = AlertDialog.Builder(it)
                    builder.setMessage(R.string.cr)
                    builder.apply {
                        setPositiveButton(
                            R.string.ok
                        ) { dialog, id -> dialog.dismiss() }
                    }
                    builder.create()
                    builder.show()
                }
            }
        }
    }

    //Funciones de comprobacion/validación
    private fun validations(): Boolean {
        if (binding.name.text.isEmpty() || binding.lastname.text.isEmpty() || binding.password.text.isEmpty()) {
            return false
        }
        return true
    }

    private fun isEmail(text: EditText) : Boolean{
        val email = text.text.toString() as CharSequence
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches())
    }
}
package com.example.vuelos

import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.vuelos.databinding.ActivityCargaVuelosBinding
import java.util.concurrent.TimeUnit


class CargaVuelos : AppCompatActivity() {
    private lateinit var binding : ActivityCargaVuelosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCargaVuelosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("name")
        val int = Intent(this, SeleccionVuelos::class.java).apply {
            putExtra("from", intent.getStringExtra("from"))
            putExtra("to", intent.getStringExtra("to"))
            putExtra("depart", intent.getStringExtra("depart"))
            putExtra("name", name)
            putExtra("retDate", intent.getStringExtra("retDate"))
            putExtra("stops", intent.getIntExtra("stops", 0))
            putExtra("passengers", intent.getIntExtra("passengers", 1))
            val b = intent.getBooleanExtra("r", false)
            if (b) {
                putExtra("roundtrip", true)
            } else {
                putExtra("roundtrip", false)
            }
        }

        var search = "Searching"
        binding.search.text = search

        //Animacion avion girando
        animateFlight(binding.flight, TimeUnit.SECONDS.toMillis(2))?.start()

        val timer = object : CountDownTimer(4000, 600) {
            override fun onTick(millisUntilFinished: Long) {
                if (search == "Searching...") {
                    search = "Searching"
                }
                search += "."
                binding.search.text = search
                binding.search.postInvalidate()
            }

            override fun onFinish() {
                startActivity(int)
                finish()
            }
        }
        timer.start()

    }

    private fun animateFlight(flight: ImageView, orbitDuration: Long): ValueAnimator? {
        val anim = ValueAnimator.ofInt(0, 359)
        anim.addUpdateListener { valueAnimator: ValueAnimator ->
            val variable = valueAnimator.animatedValue as Int
            val layoutParams = flight.layoutParams as ConstraintLayout.LayoutParams
            layoutParams.circleAngle = variable.toFloat()
            flight.layoutParams = layoutParams
        }
        anim.duration = orbitDuration
        anim.interpolator = LinearInterpolator()
        anim.repeatMode = ValueAnimator.RESTART
        anim.repeatCount = ValueAnimator.INFINITE
        return anim
    }
}
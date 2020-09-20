package com.example.tortoiseandhare

import android.os.Bundle
import android.view.View.OnTouchListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private var rabProgress = 0
    private var turtleProgress = 0

    private var rabPre = false
    private var turPre = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        hare_bar.setOnTouchListener(OnTouchListener { v, event -> true })
        tortoise_bar.setOnTouchListener(OnTouchListener{v, event -> true})

        rabProgress = 0
        turtleProgress = 0

        tortoise_button.setOnClickListener {
            if (preBoth() &&
                proBoth() ) {
                turtleProgress += 4
                tortoise_bar.progress = turtleProgress

                if (turtleProgress == 100) {
                    Toast.makeText(this, "Turtle win!", Toast.LENGTH_SHORT).show()
                }
            } else {
                notifyPre()
            }
        }

        hare_button.setOnClickListener {
            if (preBoth() &&
                proBoth()) {
                rabProgress += 4
                hare_bar.progress = rabProgress

                if (rabProgress == 100) {
                    Toast.makeText(this, "Rabbit win!", Toast.LENGTH_SHORT).show()
                }
            } else {
                notifyPre()
            }
        }

        tortoise_start.setOnClickListener {
            turPre = true

            if (turtleProgress == 100 ||
                    rabProgress == 100) {
                reset()
            }
        }

        hare_start.setOnClickListener {
            rabPre = true

            if (turtleProgress == 100 ||
                rabProgress == 100) {
                reset()
            }
        }
    }

    private fun proBoth(): Boolean = turtleProgress < 100 && rabProgress < 100

    private fun preBoth(): Boolean = rabPre && turPre

    private fun reset() {
        turPre = false
        rabPre = false
        rabProgress = 0
        turtleProgress = 0
        hare_bar.progress = 0
        tortoise_bar.progress = 0
    }

    private fun notifyPre() {
        if (turtleProgress == 100 ||
                rabProgress == 100) {
            return
        }
        else if (!turPre && !rabPre) {
            Toast.makeText(
                this,
                "Neither turtle nor rabbit press start key!",
                Toast.LENGTH_SHORT
            ).show()
        } else if (!turPre) {
            Toast.makeText(
                this,
                "You have better press start key, turtle boy!",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            Toast.makeText(
                this,
                "Dare you challenge me, rabbit!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
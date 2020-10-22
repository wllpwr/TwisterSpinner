package com.wllpwr.twisterspinner

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {

    private lateinit var spinButton: Button
    private lateinit var answer: TextView
    private lateinit var sideBank: Array<String>
    private lateinit var partBank: Array<String>
    private lateinit var colorBank: Array<String>
    private lateinit var rl: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sideBank = resources.getStringArray(R.array.sides)
        partBank = resources.getStringArray(R.array.parts)
        colorBank = resources.getStringArray(R.array.colors)
        rl = findViewById(R.id.layout)

        spinButton = findViewById(R.id.spinButton)
        answer = findViewById(R.id.answer)

        spinButton.setOnClickListener{
            spinning()
        }
    }

    private fun spinning(){
        //for (i in 0..30){
            //side.text = sideBank.random()
            //part.text = partBank.random()
            //color.text = colorBank.random()
        //}
        spin()
    }

    private fun spin(){
        val color = colorBank.random()
        //val side = sideBank.random()
        //val part = partBank.random()
        answer.text = sideBank.random() + " " + partBank.random() + " " + color

        answer.setTextColor(resources.getColor(R.color.white))

        when (color) {
            "red" -> rl.setBackgroundColor(resources.getColor(R.color.red))
            "blue" -> rl.setBackgroundColor(resources.getColor(R.color.blue))
            "green" -> rl.setBackgroundColor(resources.getColor(R.color.green))
            "yellow" -> {
                rl.setBackgroundColor(resources.getColor(R.color.yellow))
                answer.setTextColor(resources.getColor(R.color.black))
            }
        }
    }
}
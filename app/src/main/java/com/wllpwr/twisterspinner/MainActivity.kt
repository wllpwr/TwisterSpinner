package com.wllpwr.twisterspinner

import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var spinButton: Button
    private lateinit var answer: TextView
    private lateinit var autoBox: CheckBox
    private lateinit var sideBank: Array<String>
    private lateinit var partBank: Array<String>
    private lateinit var colorBank: Array<String>
    private lateinit var rl: ConstraintLayout
    private lateinit var mTTS: TextToSpeech
    private lateinit var timerCountdown: TextView

    private var time = 5000


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sideBank = resources.getStringArray(R.array.sides)
        partBank = resources.getStringArray(R.array.parts)
        colorBank = resources.getStringArray(R.array.colors)
        autoBox = findViewById(R.id.autoBox)
        rl = findViewById(R.id.layout)
        spinButton = findViewById(R.id.spinButton)
        answer = findViewById(R.id.answer)
        timerCountdown = findViewById(R.id.timerCountdown)

        mTTS = TextToSpeech(applicationContext) { status ->
            if (status != TextToSpeech.ERROR) {
                //if there is no error then set language
                mTTS.language = Locale.US
            }
        }


        spinButton.setOnClickListener{
            spin()
        }
        autoBox.setOnClickListener{
            spinWithDelay()
        }
    }

    private fun spinning(){
        var counter = 5
        object : CountDownTimer(5000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timerCountdown.text = java.lang.String.valueOf(counter)
                counter--
            }
            override fun onFinish() {
                timerCountdown.text = "5"
            }
        }.start()
    }

    private fun spin(){
        val color = colorBank.random()
        //val side = sideBank.random()
        //val part = partBank.random()
        answer.text = sideBank.random() + " " + partBank.random() + " " + color

        answer.setTextColor(resources.getColor(R.color.white))
        autoBox.setTextColor(resources.getColor(R.color.white))

        when (color) {
            "red" -> rl.setBackgroundColor(resources.getColor(R.color.red))
            "blue" -> rl.setBackgroundColor(resources.getColor(R.color.blue))
            "green" -> rl.setBackgroundColor(resources.getColor(R.color.green))
            "yellow" -> {
                rl.setBackgroundColor(resources.getColor(R.color.yellow))
                answer.setTextColor(resources.getColor(R.color.black))
                autoBox.setTextColor(resources.getColor(R.color.black))

            }
        }
        mTTS.speak(answer.text.toString(), TextToSpeech.QUEUE_FLUSH, null)
    }
    private fun spinWithDelay(){
        if (autoBox.isChecked){
            spinButton.isEnabled = false
            Handler().postDelayed({
                spin()
                spinWithDelay()
                timerCountdown.isVisible = true
            }, time.toLong())
            spinning()
        }
        else {
            spinButton.isEnabled = true
        }
    }
}
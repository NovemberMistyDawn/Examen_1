package com.example.examen_1_maria_calvo

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Locale

class MainActivity : AppCompatActivity() {

    lateinit var calculateButton: Button

    lateinit var resultTextView: TextView
    lateinit var gastosTextView: TextView
    lateinit var ingresosTextView: TextView


    lateinit var weightAddButton: Button

    var ingresos = 170.0f
    var gastos = 75.0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        calculateButton.setOnClickListener {
            calcularBalance()
        }

    }

    private fun calcularBalance(){
        var result = ingresos - gastos

        resultTextView.text = String.format(result.toString())
    }

    fun initViews(){

        calculateButton = findViewById(R.id.calculateButton)
        resultTextView = findViewById(R.id.resultTextView)
        gastosTextView = findViewById(R.id.resultTextView)
    }

}
package com.example.examen_1_maria_calvo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.examen_1_maria_calvo.MovimientoDAO
import com.example.examen_1_maria_calvo.adapters.DatabaseManager
import com.example.examen_1_maria_calvo.adapters.MovimientosAdapter
import com.example.examen_1_maria_calvo.databinding.ActivityAddMovimientoBinding
import com.example.examen_1_maria_calvo.databinding.ActivityMainBinding
import java.util.Locale

class MainActivity : AppCompatActivity() {

    lateinit var calculateButton: Button

    lateinit var movimientoList: List<Movimiento>

    lateinit var resultTextView: TextView
    lateinit var binding: ActivityMainBinding
    lateinit var movimientosTextView: TextView
    lateinit var balanceTextView: TextView

    lateinit var movimientoDAO: MovimientoDAO

    lateinit var adapter: MovimientosAdapter


    var ingresos = 170.0f
    var gastos = 75.0f

    //FALTA
    //QUE ME DETECTE SI ES POSITOV O NEGATIVO/
    //que me guarde las cosas y me las muestre en adapter
    //que me haga el calculo de ingresos y gastos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = MovimientosAdapter(items = listOf())


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.recyclerViewMovimientos.adapter = adapter
        binding.recyclerViewMovimientos.layoutManager = LinearLayoutManager(this)



        binding.addMovimientoButton.setOnClickListener {
            // Abre la actividad de añadir planta
            val intent = Intent(this, AddMovimiento::class.java)
            startActivity(intent)
        }

       initViews()




        movimientoDAO = MovimientoDAO(DatabaseManager(this))


        calculateButton.setOnClickListener {
            calcularBalance()
        }



        refreshData()
       /* calcularBalance()*/

    }

    override fun onResume() {
        super.onResume()

        refreshData()
        //poner aqui la funcion calcular
        calcularBalance()
    }

    fun refreshData() {
        movimientoList = movimientoDAO.findAll()
        adapter.updateItems(movimientoList)
    }

    fun initViews(){

        movimientosTextView =  findViewById(R.id.MovimientoTtilte)
        balanceTextView =  findViewById(R.id.balanceTextView)
        calculateButton = findViewById(R.id.calculateButton)
        resultTextView = findViewById(R.id.resultTextView)
    }



    private fun calcularBalance() {
        val ingresosTotales = movimientoList.filter { it.cantidad >= 0 }
            .sumOf { it.cantidad.toDouble() }

        val gastosTotales = movimientoList.filter { it.cantidad < 0 }
            .sumOf { it.cantidad.toDouble() }

        val balance = ingresosTotales + gastosTotales

        resultTextView.text = String.format("%.2f", balance)
    }
   /* fun detectarSiEsPositivo(){
    if (ES POSITIVO) {
        holder.binding.itemLayout.setBackgroundColor(ContextCompat.getColor(holder.itemView.context,R.color.LightRed)) // Fondo rojo
    } else (SI ES NEGATIVO) {
        holder.binding.itemLayout.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.LightGreen)) // Fondo verde
    }
    }*/



}
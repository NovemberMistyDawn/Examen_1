package com.example.examen_1_maria_calvo

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.examen_1_maria_calvo.adapters.DatabaseManager
import com.example.examen_1_maria_calvo.databinding.ActivityAddMovimientoBinding
import java.util.Calendar


class AddMovimiento : AppCompatActivity() {

    lateinit var binding: ActivityAddMovimientoBinding
    lateinit var  movimientoDAO: MovimientoDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        binding = ActivityAddMovimientoBinding.inflate(layoutInflater)
        setContentView(binding.root)



        movimientoDAO = MovimientoDAO(DatabaseManager(this))

        // Configurar el botón de guardar
        binding.btnGuardarMovimiento.setOnClickListener {
            val movimientoCantidadStr = binding.AddCantidad.text.toString()

            if (movimientoCantidadStr.isNotEmpty()) {

                try {
                    val cantidadFloat = movimientoCantidadStr.toFloat()
                    val newMovimiento = Movimiento(
                        id = 0,
                        cantidad = cantidadFloat,
                        fechaIngreso = System.currentTimeMillis()
                    )
                    movimientoDAO.insert(newMovimiento)
                    Toast.makeText(this, "Movimiento añadido", Toast.LENGTH_SHORT).show()
                    finish()
                }catch (e: NumberFormatException) {
                    Toast.makeText(this, "Cantidad no válida", Toast.LENGTH_SHORT).show()
                }
            }
            else {
                Toast.makeText(this, "Por favor, ingresa un número", Toast.LENGTH_SHORT).show()
            }
        }

        binding.fechaTextView.setOnClickListener {
            val calendario = Calendar.getInstance()
            val year = calendario.get(Calendar.YEAR)
            val month = calendario.get(Calendar.MONTH)
            val day = calendario.get(Calendar.DAY_OF_MONTH)

            val datePicker = DatePickerDialog(this, { _, y, m, d ->
                val fecha = String.format("%02d/%02d/%04d", d, m + 1, y)
                binding.fechaTextView.text = fecha
            }, year, month, day)

            datePicker.show()
        }



    }
}
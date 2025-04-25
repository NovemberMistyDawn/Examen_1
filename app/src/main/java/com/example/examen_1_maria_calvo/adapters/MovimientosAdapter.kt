package com.example.examen_1_maria_calvo.adapters

import android.app.DatePickerDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.examen_1_maria_calvo.Movimiento
import com.example.examen_1_maria_calvo.R
import com.example.examen_1_maria_calvo.databinding.MovimientoItemBinding
import com.google.android.material.datepicker.MaterialDatePicker.Builder.datePicker
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


//OJO, HAY QUE MOSTRAR LA FECHA TAMBIEN

class MovimientosAdapter(
    var items: List<Movimiento>,
) : RecyclerView.Adapter<IngresoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngresoViewHolder {
        val binding = MovimientoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return IngresoViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: IngresoViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)

    }

    fun updateItems(items: List<Movimiento>) {
        this.items = items
        notifyDataSetChanged()
    }
}

class IngresoViewHolder(val binding: MovimientoItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movimiento: Movimiento) {
        binding.movimientoIndividual.text = movimiento.cantidad.toString()

        // Convertir la fecha (long) a formato legible
        val formato = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val fechaFormateada = formato.format(Date(movimiento.fechaIngreso))
        binding.fechaDelIngreso.text = fechaFormateada


        if (movimiento.cantidad < 0) {
            binding.cardNumero.setBackgroundColor(
                ContextCompat.getColor(binding.root.context, R.color.LightRed)
            )
        } else {
            binding.cardNumero.setBackgroundColor(
                ContextCompat.getColor(binding.root.context, R.color.LightGreen)
            )
        }
    }
}

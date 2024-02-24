package com.example.webservicemedicamentos.ui.recyclerMedicamentos

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.webservicemedicamentos.R
import com.example.webservicemedicamentos.modelo.entidades.Medicamento
import com.example.webservicemedicamentos.modelo.viewModel.ViewModelMedicamentos


class MedicamentosAdapter(
    private var listaMedicamentos: List<Medicamento>,
    var modelViewMedicamento: ViewModelMedicamentos,
    var onItemClick: ((Medicamento) -> Unit)? = null
) : RecyclerView.Adapter<MedicamentosAdapter.MedicamentoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicamentoViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_medicamento, parent, false)
        return MedicamentoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MedicamentoViewHolder, position: Int) {
        val medicamentoActual = listaMedicamentos[position]

        holder.nombreMedicamento.text =
            String.format("-Medicamento: %s", medicamentoActual.nombreMedicamento)
        holder.formaFarmaceutica.text =
            String.format(
                "-Forma farmaceútica: %s",
                medicamentoActual.formaFarmaceutica
            )


        holder.itemView.setOnClickListener{
            onItemClick?.invoke((listaMedicamentos[position]))
        }
    }

    override fun getItemCount(): Int = listaMedicamentos.size

    class MedicamentoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombreMedicamento: TextView = itemView.findViewById(R.id.caja_nombre_medicamento_rec)
        val formaFarmaceutica: TextView = itemView.findViewById(R.id.caja_forma_farmaceutica_rec)
    }

}
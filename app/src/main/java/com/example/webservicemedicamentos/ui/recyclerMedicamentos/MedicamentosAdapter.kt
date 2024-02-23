package com.example.webservicemedicamentos.ui.recyclerMedicamentos

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.webservicemedicamentos.R
import com.example.webservicemedicamentos.modelo.entidades.Medicamento
import com.example.webservicemedicamentos.modelo.viewModel.ViewModelMedicamentos


class MedicamentosAdapter(
    private var listaMedicamentos: List<Medicamento>,
    var modelViewMedicamento: ViewModelMedicamentos

) : RecyclerView.Adapter<MedicamentosAdapter.MedicamentoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicamentoViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_medicamento, parent, false)
        return MedicamentoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MedicamentoViewHolder, position: Int) {
        val medicamentoActual = listaMedicamentos[position]
        holder.nombreMedicamento.text =
            String.format("Medicamento: %s", medicamentoActual.nombreMedicamento)
        holder.formaFarmaceutica.text =
            String.format("- Forma farmaceútica: %s", medicamentoActual.formaFarmaceutica)
        if (medicamentoActual.esGenerico)
            holder.esGenerico.text = String.format("Especialidad farmaceútica genérica")
        else
            holder.esGenerico.text = String.format("Marca")

        holder.itemView.setOnClickListener {
            mostrarDetallesAlimento(holder.itemView.context, medicamentoActual)
        }
    }

    override fun getItemCount(): Int = listaMedicamentos.size

    class MedicamentoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombreMedicamento: TextView = itemView.findViewById(R.id.caja_nombre_medicamento_rec)
        val laboratorio: TextView = itemView.findViewById(R.id.caja_laboratorio)
        val requiereReceta: TextView = itemView.findViewById(R.id.caja_requiere_receta_rec)
        val viaAdministracion: TextView = itemView.findViewById(R.id.caja_via_administracion_rec)
        val esGenerico: TextView = itemView.findViewById(R.id.caja_es_generico_rec)
        val formaFarmaceutica: TextView = itemView.findViewById(R.id.caja_forma_farmaceutica_rec)
    }

    private fun mostrarDetallesAlimento(context: Context, medicamento: Medicamento) {
        val alertDialogBuilder = AlertDialog.Builder(context)

        alertDialogBuilder.setTitle("Detalles de  ${medicamento.nombreMedicamento}")
        var mensaje = String.format(
            "- Vía administración: %s\n- Laboratorio: %s",
            medicamento.viaAdministracion,
            medicamento.laboratorio
        )
        if (medicamento.requiereReceta)
            alertDialogBuilder.setMessage("- Sujeto a prescripción médica\n $mensaje")
        else
            alertDialogBuilder.setMessage(
                "- No sujeto a prescripción médica\n" +
                        "$mensaje"
            )


        alertDialogBuilder.setPositiveButton("Cerrar") { dialog, _ ->
            dialog.dismiss()
        }
        alertDialogBuilder.show()
    }
}
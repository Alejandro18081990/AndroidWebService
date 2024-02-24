package com.example.webservicemedicamentos.ui.recyclerMedicamentos

import android.os.Bundle
import android.text.util.Linkify
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.webservicemedicamentos.R
import com.example.webservicemedicamentos.modelo.entidades.Medicamento
import com.example.webservicemedicamentos.modelo.viewModel.ViewModelMedicamentos


class FragmentDetalle : Fragment() {
    var medicamentoRecibido: Medicamento? = null
    private val modelViewMedicamentos: ViewModelMedicamentos by activityViewModels()
    lateinit var numRegistro: TextView
    lateinit var cajaMedicamento: TextView
    lateinit var cajaFormaFarmaceutica: TextView
    lateinit var viaAdministracion: TextView
    lateinit var urlInformacion: TextView
    lateinit var esGenerico: TextView
    lateinit var requiereReceta: TextView
    lateinit var laboratorio: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_detalle, container, false)
        cajaMedicamento = view.findViewById(R.id.caja_nombre_medicamento)
        numRegistro = view.findViewById(R.id.caja_numero_registro)
        cajaFormaFarmaceutica = view.findViewById(R.id.caja_forma_farmaceutica)
        viaAdministracion = view.findViewById(R.id.caja_via_administracion)
        urlInformacion = view.findViewById(R.id.caja_enlace_prospecto)
        esGenerico = view.findViewById(R.id.caja_es_generico)
        requiereReceta = view.findViewById(R.id.caja_receta)
        laboratorio = view.findViewById(R.id.caja_laboratorio_titular)
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Observa la posible selección del alimento (item del recyclerView) y lo asigna
        //a la propiedad  alimentoPadre de este fragment
        modelViewMedicamentos.selectedItem.observe(this, Observer { medicamento ->
            medicamentoRecibido = medicamento
            numRegistro.text =
                String.format("-NºRegistro: %s", medicamentoRecibido?.numeroRegistro.toString())
            cajaMedicamento.text =
                String.format("-Nombre: %s", medicamentoRecibido?.nombreMedicamento.toString()).lowercase()
            laboratorio.text =
                String.format("-Laboratorio: %s", medicamentoRecibido?.laboratorio.toString())
            cajaFormaFarmaceutica.text = String.format(
                "-Forma farmaceútica: %s",
                medicamentoRecibido?.formaFarmaceutica.toString().lowercase()
            )
            viaAdministracion.text =
                String.format("- Vía: %s", medicamentoRecibido?.viaAdministracion.toString()).lowercase()
            urlInformacion.text = String.format(
                "- Información adicional: %s",
                medicamentoRecibido?.urlInformacion.toString()
            )
            if (medicamentoRecibido!!.esGenerico)
                esGenerico.text = String.format("Especialidad farmaceútica genérica")
            else
                esGenerico.text = String.format("Marca comercial")

            if (medicamentoRecibido!!.requiereReceta)
                requiereReceta.text = String.format("Sujeto a prescripción médica")
            else
                requiereReceta.text = String.format("No sujeto a prescripción médica")
            Linkify.addLinks(urlInformacion, Linkify.WEB_URLS)
        })
    }
    /*if (medicamentoActual.esGenerico)
            holder.esGenerico.text = String.format("Especialidad farmaceútica genérica")
        else
            holder.esGenerico.text = String.format("Marca comercial")
*/
}
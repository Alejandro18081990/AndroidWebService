package com.example.webservicemedicamentos.ui.recyclerMedicamentos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.webservicemedicamentos.R
import com.example.webservicemedicamentos.modelo.entidades.Medicamento
import com.example.webservicemedicamentos.modelo.viewModel.ViewModelMedicamentos


class FragmentRecyclerMedicamento : Fragment() {
    lateinit var modelViewMedicamentos: ViewModelMedicamentos
    lateinit var medicamentoAdapter: MedicamentosAdapter
    lateinit var recyclerView: RecyclerView
    var listadoMedicamentos: MutableList<Medicamento> = mutableListOf()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recycler, container, false)
        modelViewMedicamentos =
            ViewModelProvider(requireActivity())[ViewModelMedicamentos::class.java]


        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        listadoMedicamentos = modelViewMedicamentos.getMedicamentos()
        medicamentoAdapter = MedicamentosAdapter(listadoMedicamentos, modelViewMedicamentos)
        recyclerView.adapter = medicamentoAdapter
        recyclerView

        modelViewMedicamentos.getAllMedicamentos({
            recyclerView.adapter!!.notifyDataSetChanged()
        }, {

        })
        medicamentoAdapter.onItemClick = {medicamento ->
            modelViewMedicamentos.selectAlimento(medicamento)
            findNavController().navigate(R.id.nav_fragment_detalle)
        }
        return view
    }
}

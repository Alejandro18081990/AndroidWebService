package com.example.webservicemedicamentos.modelo.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.webservicemedicamentos.modelo.entidades.Medicamento
import org.json.JSONException
import com.android.volley.Request
import com.android.volley.Response


class ViewModelMedicamentos(application: Application) : AndroidViewModel(application) {

    var lista: MutableList<Medicamento> = mutableListOf()
    private val mutableSelectedItem = MutableLiveData<Medicamento>()
    val selectedItem: LiveData<Medicamento> get() = mutableSelectedItem

    var url = "https://cima.aemps.es/cima/rest/medicamentos?nombre"
    var cola = Volley.newRequestQueue(getApplication())
    var listaMedicamentos: MutableList<Medicamento> = mutableListOf()

    fun getAllMedicamentos(onSuccess: () -> Unit, onError: () -> Unit) {
        val peticion = JsonObjectRequest(Request.Method.GET, url, null,
             Response.Listener { response ->
                try {
                    val resultadosPeticion = response.getJSONArray("resultados")
                    Log.d("Longitud",resultadosPeticion.length().toString())
                    for (i in 0 until resultadosPeticion.length()) {
                        val medicamentoJson = resultadosPeticion.getJSONObject(i)
                        val nombremedicamento = medicamentoJson.getString("nombre")
                        val laboratorio = medicamentoJson.getString("labtitular")
                        val requiereReceta = medicamentoJson.getString("receta").toBoolean()
                        val esGenerico = medicamentoJson.getString("generico").toBoolean()
                        val viaAdministracion = medicamentoJson.getJSONArray("viasAdministracion")
                        val nombreViaAdministracion =
                            viaAdministracion.getJSONObject(0).getString("nombre")
                        val formaFarmaceutica = medicamentoJson.getJSONObject("formaFarmaceutica")
                        val nombreFormaFarmaceutica = formaFarmaceutica.getString("nombre")

                        val medicamento = Medicamento(
                            nombremedicamento,
                            laboratorio,
                            requiereReceta,
                            esGenerico,
                            nombreViaAdministracion,
                            nombreFormaFarmaceutica
                        )
                        listaMedicamentos.add(medicamento)
                    }
                    getMedicamentos().addAll(listaMedicamentos)
                    onSuccess()
                } catch (e: JSONException) {
                    e.printStackTrace()
                    onError()
                }
            }
        ) { error ->
            error.printStackTrace()
            onError()
        }
        cola.add(peticion)
    }


    fun getMedicamentos(): MutableList<Medicamento> {
        return listaMedicamentos
    }
}
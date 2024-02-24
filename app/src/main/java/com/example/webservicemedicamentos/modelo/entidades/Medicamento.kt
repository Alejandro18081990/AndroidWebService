package com.example.webservicemedicamentos.modelo.entidades

import android.os.Parcelable

class Medicamento constructor(
    var numeroRegistro: Int,
    var nombreMedicamento: String,
    var laboratorio: String,
    var requiereReceta: Boolean,
    var esGenerico: Boolean,
    var viaAdministracion: String,
    var formaFarmaceutica: String,
    var urlInformacion : String
) : java.io.Serializable {
}
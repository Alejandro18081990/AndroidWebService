package com.example.webservicemedicamentos.modelo.entidades

class Medicamento constructor(
    var nombreMedicamento: String,
    var laboratorio: String,
    var requiereReceta: Boolean,
    var esGenerico: Boolean,
    var viaAdministracion: String,
    var formaFarmaceutica: String
) {
}
package com.example.siamo.model

import kotlinx.serialization.Serializable

@Serializable
data class OrderServicioTecnico(
    val id_ost: Int? = null,
    val fecha_registro: String? = null,
    val fecha_aprox_ingreso: String? = null,
    val estado: Int? = null,
    val consulta: Consulta? = null,
    val informe_tecnico: InformeTecnico? = null,
    val ficha_ingreso: FichaIngreso? = null,
    val ficha_salida: FichaSalida? = null
)

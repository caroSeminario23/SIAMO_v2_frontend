package com.example.siamo.data.otros

data class consulta_cliente(
    val nombres: String,
    val apellidos: String,
    val tipoDocumento: Int,
    val numDocumento: String
)
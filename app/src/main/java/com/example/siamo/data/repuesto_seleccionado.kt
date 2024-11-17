package com.example.siamo.data

data class repuesto_seleccionado(
    val repuesto: consulta_repuesto,
    val cantidad: Int,
    val marcado: Boolean = true
) {
    val subtotal: Double
        get() = repuesto.precio * cantidad
}

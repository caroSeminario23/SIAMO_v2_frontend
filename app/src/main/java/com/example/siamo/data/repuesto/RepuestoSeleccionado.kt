package com.example.siamo.data.repuesto

data class RepuestoSeleccionado(
    val repuesto: Repuesto,
    val cantidad: Int,
    val marcado: Boolean = true
) {
    val subtotal: Double
        get() = repuesto.precio!! * cantidad
}

package com.example.siamo.data.problema

import kotlinx.serialization.Serializable

@Serializable
data class ProblemaSeleccionado(
    val problema: ProblemaLectura,
    val seleccionado: Boolean = true
)

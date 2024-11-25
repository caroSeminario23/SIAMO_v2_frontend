package com.example.siamo.model

import com.example.siamo.data.problema.ProblemaLectura
import kotlinx.serialization.Serializable

@Serializable
data class ListaProblemas(
    val id_consulta: Int,
    val problemas: List<ProblemaLectura>

)
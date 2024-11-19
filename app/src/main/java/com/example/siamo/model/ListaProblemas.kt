package com.example.siamo.model

import kotlinx.serialization.Serializable

@Serializable
data class ListaProblemas(
    val id_Consulta: Int,
    val id_Problema:Int

)
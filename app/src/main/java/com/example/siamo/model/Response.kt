package com.example.siamo.model

import kotlinx.serialization.Serializable

@Serializable
data class Response(
    val id: Int? = null,
    val mensaje: String? = null,
    val message: String? = null
)

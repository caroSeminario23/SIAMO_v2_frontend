package com.example.siamo.data

import com.example.siamo.model.Automovil
import com.example.siamo.model.Response
import com.example.siamo.network_consulta.AutomovilApiService

interface AutomovilRepository {
    suspend fun postAutomovil(automovil: Automovil): Response
    suspend fun getAutomovil(placa: String): Automovil
}

class DefaultAutomovilRepository(private val automovilApiService: AutomovilApiService) :
    AutomovilRepository {
    override suspend fun postAutomovil(automovil: Automovil): Response {
        return automovilApiService.postAutomovil(automovil)
    }

    override suspend fun getAutomovil(placa: String): Automovil {
        return automovilApiService.getAutomovil(placa)
    }
}
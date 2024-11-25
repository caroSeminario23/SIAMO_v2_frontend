package com.example.siamo.data.otros_ara

import com.example.siamo.model.Response
import com.example.siamo.model.Solucion
import com.example.siamo.network.SolucionApiService

interface SolucionRepository {
    suspend fun getSolucionPorId(id: Int): Solucion
    suspend fun insertarSolucion(solucion: Solucion): Response
}

class DefaultSolucionRepository(private val solucionApiService: SolucionApiService) :
    SolucionRepository {

    override suspend fun getSolucionPorId(id: Int): Solucion {
        return solucionApiService.getSolucionPorId(id)
    }

    override suspend fun insertarSolucion(solucion: Solucion): Response {
        return solucionApiService.insertarSolucion(solucion)
    }
}

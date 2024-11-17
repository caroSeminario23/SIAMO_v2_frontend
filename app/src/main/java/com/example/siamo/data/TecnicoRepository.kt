package com.example.siamo.data

import com.example.siamo.model.Tecnico
import com.example.siamo.network_consulta.TecnicoApiService

interface TecnicoRepository {
    suspend fun getTecnico(id_tecnico: Int): Tecnico
    suspend fun getAllTecnicos(): List<Tecnico>
}

class DefaultTecnicoRepository(private val tecnicoService: TecnicoApiService) :
    TecnicoRepository {
    override suspend fun getTecnico(id_tecnico: Int): Tecnico {
        return tecnicoService.getTecnico(id_tecnico)
    }

    override suspend fun getAllTecnicos(): List<Tecnico> {
        return tecnicoService.getAllTecnicos()
    }
}
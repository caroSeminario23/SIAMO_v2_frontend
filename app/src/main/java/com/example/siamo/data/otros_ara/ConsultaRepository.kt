package com.example.siamo.data.otros_ara

import com.example.siamo.model.Consulta
import com.example.siamo.network.ConsultaApiService

interface ConsultaRepository {
    suspend fun getConsulta(id_tecnico: Int): List<Consulta>
}

class DefaultConsultaRepository(private val consultaApiService: ConsultaApiService) :
    ConsultaRepository {

    override suspend fun getConsulta(id_tecnico: Int): List<Consulta> {
        return consultaApiService.getConsultasPorTecnico(id_tecnico)
    }
}


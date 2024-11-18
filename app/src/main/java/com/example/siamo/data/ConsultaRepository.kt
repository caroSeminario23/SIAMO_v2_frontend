package com.example.siamo.data

import com.example.siamo.model.Consulta
import com.example.siamo.network_inspeccion.ConsultaApiService

interface ConsultaRepository {
    suspend fun getConsulta(id_tecnico: Int): List<Consulta>
}

class DefaultConsultaRepository(private val consultaApiService: ConsultaApiService) :
    ConsultaRepository {

    override suspend fun getConsulta(id_tecnico: Int): List<Consulta> {
        return consultaApiService.getConsultasPorTecnico(id_tecnico)
    }
}


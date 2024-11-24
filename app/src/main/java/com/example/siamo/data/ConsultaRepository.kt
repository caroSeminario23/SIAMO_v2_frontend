package com.example.siamo.data

import com.example.siamo.model.Consulta
import com.example.siamo.model.ConsultaRequest
import com.example.siamo.model.Response
import com.example.siamo.network_consulta.ConsultaApiService

interface ConsultaRepository {
    suspend fun postConsulta(consulta: Consulta): Response
    suspend fun getConsulta(id_tecnico: Int): List<Consulta>
}

class DefaultConsultaRepository(private val consultaApiService: ConsultaApiService) :
    ConsultaRepository {
    override suspend fun postConsulta(consulta: Consulta): Response {
        return consultaApiService.postConsulta(consulta)
    }

    override suspend fun getConsulta(id_tecnico: Int): List<Consulta> {
        return consultaApiService.getConsulta(id_tecnico)
    }
}
package com.example.siamo.data.otros_ara

import com.example.siamo.model.Consulta
import com.example.siamo.model.Response
import com.example.siamo.network.ConsultaApiService

interface ConsultaRepository {
    suspend fun getConsulta(id_tecnico: Int): List<Consulta>
    suspend fun aceptarConsulta(id_consulta: Int): Response
    suspend fun rechazarConsulta(id_consulta: Int): Response
}

class DefaultConsultaRepository(private val consultaApiService: ConsultaApiService) :
    ConsultaRepository {

    override suspend fun getConsulta(id_tecnico: Int): List<Consulta> {
        return consultaApiService.getConsultasPorTecnico(id_tecnico)
    }

    override suspend fun aceptarConsulta(id_consulta: Int): Response {
        return consultaApiService.aceptarConsulta(id_consulta)
    }

    override suspend fun rechazarConsulta(id_consulta: Int): Response {
        return consultaApiService.rechazarConsulta(id_consulta)
    }
}


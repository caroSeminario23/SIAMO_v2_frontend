package com.example.siamo.network_consulta

import com.example.siamo.model.Consulta
import com.example.siamo.model.ConsultaRequest
import com.example.siamo.model.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ConsultaApiService {
    @GET("api/consulta/get/all/{id_tecnico}")
    suspend fun getConsulta(id_tecnico: Int): List<Consulta>

    @POST("api/consulta/insert")
    suspend fun postConsulta(@Body request: Consulta): Response
}
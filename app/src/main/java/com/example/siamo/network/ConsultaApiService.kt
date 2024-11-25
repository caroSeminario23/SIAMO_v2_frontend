package com.example.siamo.network

import com.example.siamo.model.Consulta
import com.example.siamo.model.Response
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface ConsultaApiService {

    @GET("api/consulta/get/all/{id_tecnico}")
    suspend fun getConsultasPorTecnico(@Path("id_tecnico") id_tecnico: Int): List<Consulta>

    @PUT("api/consulta/update/accept/{id_consulta}")
    suspend fun aceptarConsulta(@Path("id_consulta") id_consulta: Int): Response

    @PUT("api/consulta/update/reject/{id_consulta}")
    suspend fun rechazarConsulta(@Path("id_consulta") id_consulta: Int): Response
}

package com.example.siamo.network_inspeccion

import com.example.siamo.model.Consulta
import retrofit2.http.GET
import retrofit2.http.Path

interface ConsultaApiService {

    @GET("api/consulta/get/all/{id_tecnico}")
    suspend fun getConsultasPorTecnico(@Path("id_tecnico") id_tecnico: Int): List<Consulta>
}

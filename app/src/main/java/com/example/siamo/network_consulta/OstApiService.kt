package com.example.siamo.network_consulta

import com.example.siamo.model.OrderServicioTecnico
import retrofit2.http.GET
import retrofit2.http.Path

interface OstApiService {

    @GET("api/ost/get/{id}")
    suspend fun getOstById(@Path("id") id: Int): OrderServicioTecnico
}
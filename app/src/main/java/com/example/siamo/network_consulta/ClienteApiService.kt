package com.example.siamo.network_consulta

import com.example.siamo.model.Cliente
import com.example.siamo.model.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ClienteApiService {
    // El doc se para como parametro de la url
    @GET("api/cliente/get/{doc}")
    suspend fun getCliente(@Path("doc") doc: String): Cliente

    @GET("api/cliente/get/doc/{doc}")
    suspend fun getClienteResumed(@Path("doc") doc: Int): Cliente

    @POST("api/cliente/insert")
    suspend fun postCliente(@Body request: Cliente): Response
}
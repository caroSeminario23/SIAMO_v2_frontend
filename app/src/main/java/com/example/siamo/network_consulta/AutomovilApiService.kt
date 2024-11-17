package com.example.siamo.network_consulta

import com.example.siamo.model.Automovil
import com.example.siamo.model.AutomovilRequest
import com.example.siamo.model.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AutomovilApiService {
    //    El doc se para como parametro de la url
    @GET("api/automovil/get/{placa}")
    suspend fun getAutomovil(@Path("placa") placa: String): Automovil

    @POST("api/automovil/insert")
    suspend fun postAutomovil(@Body  request: Automovil): Response
}
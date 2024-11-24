package com.example.siamo.network_consulta

import com.example.siamo.model.Tecnico
import retrofit2.http.GET

interface TecnicoApiService {

    @GET("api/tecnico/get/{id_tecnico}")
    suspend fun getTecnico(id_tecnico: Int): Tecnico

    @GET("api/tecnico/get/all")
    suspend fun getAllTecnicos(): List<Tecnico>
}
package com.example.siamo.network_consulta

import com.example.siamo.model.Persona
import com.example.siamo.model.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface PersonaApiService {
    @POST("api/persona/insert")
    suspend fun postPersona(@Body request: Persona): Response

    @PUT("api/persona/update/{id_persona}")
    suspend fun putPersona(@Path("id_persona") idPersona: Int, @Body request: Persona): Response
}
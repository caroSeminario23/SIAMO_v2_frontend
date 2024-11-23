package com.example.siamo.network_inspeccion

import com.example.siamo.model.Problema
import com.example.siamo.model.ProblemaActualizar
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ProblemaApiService {

    // Obtener todos los problemas
    @GET("api/problema/get/all")
    suspend fun getTodosLosProblemas(): List<Problema>

    // Obtener un problema por su ID
    @GET("api/problema/get/{id_problema}")
    suspend fun getProblemaPorId(@Path("id_problema") idProblema: Int): Problema

    // Crear un nuevo problema
    @POST("api/problema/insert")
    suspend fun insertarProblema(@Body problema: Problema): Problema

    // Actualizar el detalle del problema y la solución asociada
    @PUT("api/problema/update/{id_problema}")
    suspend fun actualizarProblema(
        @Path("id_problema") idProblema: Int,
        @Body problema: ProblemaActualizar
    ): Problema
}

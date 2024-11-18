package com.example.siamo.network_inspeccion

import com.example.siamo.model.Solucion
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface SolucionApiService {

    // Obtener una solución por su ID
    @GET("api/solucion/get/{id}")
    suspend fun getSolucionPorId(@Path("id") idSolucion: Int): Solucion

    // Crear una nueva solución
    @POST("api/solucion/insert")
    suspend fun insertarSolucion(@Body solucion: Solucion): Solucion
}

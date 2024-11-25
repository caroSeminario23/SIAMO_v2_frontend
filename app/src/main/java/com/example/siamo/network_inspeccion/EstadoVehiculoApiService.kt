package com.example.siamo.network_inspeccion

import com.example.siamo.model.EstadoVehiculo
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface EstadoVehiculoApiService {


    @GET("api/estado_vehiculo/get/{id_ficha_ingreso}")
    suspend fun getEstadoVehiculoPorFichaIngreso(@Path("id_ficha_ingreso") idFichaIngreso: Int): EstadoVehiculo


    @POST("api/estado_vehiculo/insert")
    suspend fun insertarEstadoVehiculo(@Body estadoVehiculo: EstadoVehiculo): EstadoVehiculo
}

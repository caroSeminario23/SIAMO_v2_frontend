package com.example.siamo.network

import com.example.siamo.model.PresupuestoRepuesto
import com.example.siamo.data.problema.ProblemaLectura
import com.example.siamo.data.problema.ProblemaRegistro
import com.example.siamo.data.solucion.SolucionLectura
import com.example.siamo.model.Problema
import com.example.siamo.model.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ProblemaApiService {

    // Obtener todos los problemas
    @GET("api/problema/get/all")
    suspend fun getTodosLosProblemas(): List<ProblemaLectura>

    // Obtener un problema por su ID
    @GET("api/problema/get/details/{id_problema}")
    suspend fun getProblemaPorId(@Path("id_problema") idProblema: Int): SolucionLectura

    // Crear un nuevo problema
    @POST("api/problema/insert")
    suspend fun insertarProblema(@Body problema: ProblemaRegistro): Response

    @POST("api/presupuesto_respuesto/insert")
    suspend fun insertarPresupuestoRepuesto(@Body presupuestoRepuesto: PresupuestoRepuesto): Response
}

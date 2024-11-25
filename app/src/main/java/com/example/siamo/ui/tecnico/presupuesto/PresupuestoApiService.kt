package com.example.siamo.ui.tecnico.presupuesto

import com.example.siamo.data.presupuesto.PresupuestoRegistro
import com.example.siamo.data.repuesto.Repuesto
import com.example.siamo.model.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PresupuestoApiService {
    //mostrar repuestos registrados
    @GET("api/repuesto/get/all")
    suspend fun getRepuestos(): List<Repuesto>

    @POST("api/presupuesto/insert")
    suspend fun insertPresupuesto(@Body presupuesto: PresupuestoRegistro): Response
}
package com.example.siamo.ui.tecnico.presupuesto

import com.example.siamo.data.consulta_repuesto
import retrofit2.http.GET

interface PresupuestoApiService {
    //mostrar repuestos registrados
    @GET("api/repuestos/getall")
    suspend fun getRepuestos(): List<consulta_repuesto>
}
package com.example.siamo.ui.tecnico.resumen_ost

import com.example.siamo.data.ost.OstRegistro
import com.example.siamo.data.presupuesto.PresupuestoRepuesto
import com.example.siamo.model.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ResumenOstApiService {
    // enviar ost a la base de datos
    @POST("api/ost/insert")
    suspend fun insertOst(@Body request: OstRegistro): Response

    // enviar ficha ingreso a la base de datos
    //@POST("api/fichaingreso/insert")
    //suspend fun insertFichaIngreso(@Body request: FichaIngreso): Int

    // enviar presupuesto a la base de datos
    //@POST("api/presupuesto/insert")
    //suspend fun insertPresupuesto(@Body request: Presupuesto): Int

    // enviar todos los presupuesto_repuesto a la base de datos
    @POST("api/presupuesto_repuesto/insert")
    suspend fun insertPresupuestoRepuesto(@Body request: List<PresupuestoRepuesto>): Response
}
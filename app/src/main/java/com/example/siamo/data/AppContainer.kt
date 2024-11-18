package com.example.siamo.data

import com.example.siamo.ui.tecnico.presupuesto.DefaultPresupuestoRepository
import com.example.siamo.ui.tecnico.presupuesto.PresupuestoApiService
import com.example.siamo.ui.tecnico.presupuesto.PresupuestoRepository
import com.example.siamo.ui.tecnico.resumen_ost.DefaultResumenOstRepository
import com.example.siamo.ui.tecnico.resumen_ost.ResumenOstApiService
import com.example.siamo.ui.tecnico.resumen_ost.ResumenOstRepository
import retrofit2.Retrofit
import kotlinx.serialization.json.Json
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import okhttp3.MediaType.Companion.toMediaType

interface AppContainer {
    val presupuestoRepository: PresupuestoRepository
    val resumenOstRepository: ResumenOstRepository
}

class DefaultAppContainer : AppContainer {
    private val BASE_URL = "http://192.168.18.37:5000/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json { ignoreUnknownKeys = true }.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    private val presupuestoApiService: PresupuestoApiService by lazy {
        retrofit.create(PresupuestoApiService::class.java)
    }

    private val resumenOstApiService: ResumenOstApiService by lazy {
        retrofit.create(ResumenOstApiService::class.java)
    }

    override val presupuestoRepository: PresupuestoRepository by lazy {
        DefaultPresupuestoRepository(presupuestoApiService)
    }

    override val resumenOstRepository: ResumenOstRepository by lazy {
        DefaultResumenOstRepository(resumenOstApiService)
    }
}
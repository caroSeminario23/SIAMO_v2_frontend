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
import com.example.siamo.data.otros_ara.ConsultaRepository
import com.example.siamo.data.otros_ara.DefaultConsultaRepository
import com.example.siamo.data.otros_ara.DefaultListaProblemasRepository
import com.example.siamo.data.otros_ara.DefaultProblemaRepository
import com.example.siamo.data.otros_ara.DefaultSolucionRepository
import com.example.siamo.data.otros_ara.ListaProblemasRepository
import com.example.siamo.data.otros_ara.ProblemaRepository
import com.example.siamo.data.otros_ara.SolucionRepository
import com.example.siamo.network.ConsultaApiService
import com.example.siamo.network.ListaProblemasApiService
import com.example.siamo.network.ProblemaApiService
import com.example.siamo.network.SolucionApiService
import okhttp3.MediaType.Companion.toMediaType
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import retrofit2.Retrofit

interface AppContainer {
    val presupuestoRepository: PresupuestoRepository
    val resumenOstRepository: ResumenOstRepository
    val consultaRepository: ConsultaRepository
    val problemaRepository: ProblemaRepository
    val listaProblemasRepository: ListaProblemasRepository
    val  solucionRepository: SolucionRepository

}

class DefaultAppContainer : AppContainer {
    private val BASE_URL = "http://192.168.18.37:5000/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json { ignoreUnknownKeys = true }.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    private val presupuestoApiService: PresupuestoApiService by lazy {
        retrofit.create(PresupuestoApiService::class.java)
    // API Services
    private val consultaApiService: ConsultaApiService by lazy {
        retrofit.create(ConsultaApiService::class.java)
    }

    private val problemaApiService: ProblemaApiService by lazy {
        retrofit.create(ProblemaApiService::class.java)
    }

    private val listaProblemasApiService: ListaProblemasApiService by lazy {
        retrofit.create(ListaProblemasApiService::class.java)
    }
    private val solucionApiService: SolucionApiService by lazy {
        retrofit.create(SolucionApiService::class.java)
    }

    private val resumenOstApiService: ResumenOstApiService by lazy {
        retrofit.create(ResumenOstApiService::class.java)
    // Repositories
    override val consultaRepository: ConsultaRepository by lazy {
        DefaultConsultaRepository(consultaApiService)
    }

    override val presupuestoRepository: PresupuestoRepository by lazy {
        DefaultPresupuestoRepository(presupuestoApiService)
    override val problemaRepository: ProblemaRepository by lazy {
        DefaultProblemaRepository(problemaApiService)
    }

    override val resumenOstRepository: ResumenOstRepository by lazy {
        DefaultResumenOstRepository(resumenOstApiService)
    override val listaProblemasRepository: ListaProblemasRepository by lazy {
        DefaultListaProblemasRepository(listaProblemasApiService)
    }
    override val solucionRepository: SolucionRepository by lazy {
        DefaultSolucionRepository(solucionApiService)
    }
}
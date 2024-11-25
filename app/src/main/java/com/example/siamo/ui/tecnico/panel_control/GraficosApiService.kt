package com.example.siamo.ui.tecnico.panel_control

import com.example.siamo.data.graficos.Datos
import retrofit2.http.GET
import retrofit2.http.Path

interface GraficosApiService {
    // TECNICO
    // mostrar datos de los graficos N° OSTS ASIGNADAS VS DE APOYO
    @GET("api/osts/getDatosGraficoTecnico1/{mes}/{id_tecnico}")
    suspend fun getDatosGraficoTecnico1(
        @Path("mes") mes: Int,
        @Path("id_tecnico") id_tecnico: Int
    ): List<Datos>

    // mostrar datos de los graficos ESTADO DE OSTS ASIGNADAS
    @GET("api/osts/getDatosGraficoTecnico2/{mes}/{id_tecnico}")
    suspend fun getDatosGraficoTecnico2(
        @Path("mes") mes: Int,
        @Path("id_tecnico") id_tecnico: Int
    ): List<Datos>

    // mostrar datos de los graficos ESTADO DE OSTS DE APOYO
    @GET("api/osts/getDatosGraficoTecnico3/{mes}/{id_tecnico}")
    suspend fun getDatosGraficoTecnico3(
        @Path("mes") mes: Int,
        @Path("id_tecnico") id_tecnico: Int
    ): List<Datos>


    // RECEPCIONISTA
    // mostrar datos del grafico Estado OSTs
    @GET("api/osts/getDatosGraficoRecepcionista1/{mes}")
    suspend fun getDatosGraficoRecepcionista1(
        @Path("mes") mes: Int,
    ): List<Datos>

    // mostrar datos del grafico N° OSTs por técnico
    @GET("api/osts/getDatosGraficoRecepcionista2/{mes}")
    suspend fun getDatosGraficoRecepcionista2(
        @Path("mes") mes: Int,
    ): List<Datos>

    // mostrar datos del grafico Consultas vs OSTs
    @GET("api/osts/getDatosGraficoRecepcionista3/{mes}")
    suspend fun getDatosGraficoRecepcionista3(
        @Path("mes") mes: Int,
    ): List<Datos>
}
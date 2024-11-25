package com.example.siamo.ui.tecnico.panel_control

import com.example.siamo.data.graficos.Datos
import retrofit2.http.GET

interface PanelControlTecnicoApiService {
    // mostrar datos de los graficos N° OSTS ASIGNADAS VS DE APOYO
    @GET("api/osts/getDatosGraficoTecnico1/{mes}")
    suspend fun getDatosGraficoTecnico1(mes : String): List<Datos>

    // mostrar datos de los graficos ESTADO DE OSTS ASIGNADAS
    @GET("api/osts/getDatosGraficoTecnico2/{mes}")
    suspend fun getDatosGraficoTecnico2(mes: String): List<Datos>

    // mostrar datos de los graficos ESTADO DE OSTS DE APOYO
    @GET("api/osts/getDatosGraficoTecnico3/{mes}")
    suspend fun getDatosGraficoTecnico3(mes: String): List<Datos>
}
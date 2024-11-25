package com.example.siamo.ui.recepcionista.panel_control

import com.example.siamo.data.graficos.Datos
import retrofit2.http.GET

interface PanelControlRecepcionistaApiService {
    // mostrar datos del grafico Estado OSTs
    @GET("api/osts/getDatosGraficoRecepcionista1/{mes}")
    suspend fun getDatosGraficoRecepcionista1(mes: String): List<Datos>

    // mostrar datos del grafico N° OSTs por técnico
    @GET("api/osts/getDatosGraficoRecepcionista2/{mes}")
    suspend fun getDatosGraficoRecepcionista2(mes: String): List<Datos>

    // mostrar datos del grafico Consultas vs OSTs
    @GET("api/osts/getDatosGraficoRecepcionista3/{mes}")
    suspend fun getDatosGraficoRecepcionista3(mes: String): List<Datos>
}
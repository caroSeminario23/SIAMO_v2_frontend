package com.example.siamo.ui.recepcionista.panel_control

import com.example.siamo.data.graficos.Datos

interface PanelControlRecepcionistaRepository {
    suspend fun getDatosGraficoRecepcionista1(mes: String): List<Datos>
    suspend fun getDatosGraficoRecepcionista2(mes: String): List<Datos>
    suspend fun getDatosGraficoRecepcionista3(mes: String): List<Datos>
}

class DefaultPanelControlRecepcionistaRepository(
    private val apiService: PanelControlRecepcionistaApiService
) : PanelControlRecepcionistaRepository {
    override suspend fun getDatosGraficoRecepcionista1(mes: String): List<Datos> {
        return apiService.getDatosGraficoRecepcionista1(mes)
    }

    override suspend fun getDatosGraficoRecepcionista2(mes: String): List<Datos> {
        return apiService.getDatosGraficoRecepcionista2(mes)
    }

    override suspend fun getDatosGraficoRecepcionista3(mes: String): List<Datos> {
        return apiService.getDatosGraficoRecepcionista3(mes)
    }
}
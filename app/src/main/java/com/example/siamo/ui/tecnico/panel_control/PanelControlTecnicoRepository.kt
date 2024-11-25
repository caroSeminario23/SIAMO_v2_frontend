package com.example.siamo.ui.tecnico.panel_control

import com.example.siamo.data.graficos.Datos

interface PanelControlTecnicoRepository {
    suspend fun getDatosGraficoTecnico1(mes: String): List<Datos>
    suspend fun getDatosGraficoTecnico2(mes: String): List<Datos>
    suspend fun getDatosGraficoTecnico3(mes: String): List<Datos>
}

class DefaultPanelControlTecnicoRepository(
    private val apiService: PanelControlTecnicoApiService
) : PanelControlTecnicoRepository {
    override suspend fun getDatosGraficoTecnico1(mes: String): List<Datos> {
        return apiService.getDatosGraficoTecnico1(mes)
    }

    override suspend fun getDatosGraficoTecnico2(mes: String): List<Datos> {
        return apiService.getDatosGraficoTecnico2(mes)
    }

    override suspend fun getDatosGraficoTecnico3(mes: String): List<Datos> {
        return apiService.getDatosGraficoTecnico3(mes)
    }
}
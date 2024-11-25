package com.example.siamo.ui.tecnico.panel_control

import com.example.siamo.data.graficos.Datos

interface GraficosRepository {
    suspend fun getDatosGraficoTecnico1(mes: Int, id_tecnico: Int): List<Datos>
    suspend fun getDatosGraficoTecnico2(mes: Int, id_tecnico: Int): List<Datos>
    suspend fun getDatosGraficoTecnico3(mes: Int, id_tecnico: Int): List<Datos>
    suspend fun getDatosGraficoRecepcionista1(mes: Int): List<Datos>
    suspend fun getDatosGraficoRecepcionista2(mes: Int): List<Datos>
    suspend fun getDatosGraficoRecepcionista3(mes: Int): List<Datos>
}

class DefaultGraficosRepository(
    private val graficosApiService: GraficosApiService
) : GraficosRepository {
    override suspend fun getDatosGraficoTecnico1(mes: Int, id_tecnico: Int): List<Datos> {
        return graficosApiService.getDatosGraficoTecnico1(mes, id_tecnico)
    }

    override suspend fun getDatosGraficoTecnico2(mes: Int, id_tecnico: Int): List<Datos> {
        return graficosApiService.getDatosGraficoTecnico2(mes, id_tecnico)
    }

    override suspend fun getDatosGraficoTecnico3(mes: Int, id_tecnico:Int): List<Datos> {
        return graficosApiService.getDatosGraficoTecnico3(mes, id_tecnico)
    }

    override suspend fun getDatosGraficoRecepcionista1(mes: Int): List<Datos> {
        return graficosApiService.getDatosGraficoRecepcionista1(mes)
    }

    override suspend fun getDatosGraficoRecepcionista2(mes: Int): List<Datos> {
        return graficosApiService.getDatosGraficoRecepcionista2(mes)
    }

    override suspend fun getDatosGraficoRecepcionista3(mes: Int): List<Datos> {
        return graficosApiService.getDatosGraficoRecepcionista3(mes)
    }
}
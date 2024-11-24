package com.example.siamo.data

import com.example.siamo.model.OrderServicioTecnico
import com.example.siamo.network_consulta.OstApiService

interface OstRepository {
    suspend fun getOstById(id: Int): OrderServicioTecnico
}

class DefaultOstRepository(private val ostApiService: OstApiService) : OstRepository {
    override suspend fun getOstById(id: Int): OrderServicioTecnico {
        return ostApiService.getOstById(id)
    }
}
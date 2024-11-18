package com.example.siamo.ui.tecnico.presupuesto

import com.example.siamo.data.consulta_repuesto

interface PresupuestoRepository {
    suspend fun getRepuestos(): List<consulta_repuesto>
}

class DefaultPresupuestoRepository(
    private val apiService: PresupuestoApiService) : PresupuestoRepository {
    override suspend fun getRepuestos(): List<consulta_repuesto> {
        return apiService.getRepuestos()
    }
}
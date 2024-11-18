package com.example.siamo.ui.tecnico.presupuesto

import com.example.siamo.data.repuesto.Repuesto

interface PresupuestoRepository {
    suspend fun getRepuestos(): List<Repuesto>
}

class DefaultPresupuestoRepository(
    private val apiService: PresupuestoApiService) : PresupuestoRepository {
    override suspend fun getRepuestos(): List<Repuesto> {
        return apiService.getRepuestos()
    }
}
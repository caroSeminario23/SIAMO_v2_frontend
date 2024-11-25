package com.example.siamo.ui.tecnico.presupuesto

import com.example.siamo.data.presupuesto.PresupuestoRegistro
import com.example.siamo.data.repuesto.Repuesto
import com.example.siamo.model.Response

interface PresupuestoRepository {
    suspend fun getRepuestos(): List<Repuesto>
    suspend fun insertPresupuesto(presupuesto: PresupuestoRegistro): Response
}

class DefaultPresupuestoRepository(
    private val apiService: PresupuestoApiService) : PresupuestoRepository {
    override suspend fun getRepuestos(): List<Repuesto> {
        return apiService.getRepuestos()
    }
    override suspend fun insertPresupuesto(presupuesto: PresupuestoRegistro): Response {
        return apiService.insertPresupuesto(presupuesto)
    }
}
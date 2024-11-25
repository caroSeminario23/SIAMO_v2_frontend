package com.example.siamo.ui.tecnico.resumen_ost

import com.example.siamo.data.ost.OstRegistro
import com.example.siamo.model.Response

interface ResumenOstRepository {
    suspend fun insertOst(request: OstRegistro): Response
    //suspend fun insertFichaIngreso(request: FichaIngreso): Int
    //suspend fun insertPresupuesto(request: Presupuesto): Int
    //suspend fun insertPresupuestoRepuesto(request: List<PresupuestoRepuesto>): Int
}

class DefaultResumenOstRepository(
    private val apiService: ResumenOstApiService) : ResumenOstRepository {
    override suspend fun insertOst(request: OstRegistro): Response {
        return apiService.insertOst(request)
    }
    //override suspend fun insertFichaIngreso(request: FichaIngreso): Int {
    //    return apiService.insertFichaIngreso(request)
    //}
    //override suspend fun insertPresupuesto(request: Presupuesto): Int {
    //    return apiService.insertPresupuesto(request)
    //}
    //override suspend fun insertPresupuestoRepuesto(request: List<PresupuestoRepuesto>): Int {
    //    return apiService.insertPresupuestoRepuesto(request)
    //}
}
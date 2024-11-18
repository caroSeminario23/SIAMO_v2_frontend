package com.example.siamo.ui.tecnico.resumenost

interface ResumenOstRepository {
    //suspend fun insertOst(request: Ost): Int
    //suspend fun insertFichaIngreso(request: FichaIngreso): Int
    //suspend fun insertPresupuesto(request: Presupuesto): Int
    //suspend fun insertPresupuestoRepuesto(request: List<PresupuestoRepuesto>): Int
}

class DefaultResumenOstRepository(
    private val apiService: ResumenOstApiService) : ResumenOstRepository {
    //override suspend fun insertOst(request: Ost): Int {
    //    return apiService.insertOst(request)
    //}
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
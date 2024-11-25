package com.example.siamo.data.otros_ara

import com.example.siamo.data.problema.ProblemaLectura
import com.example.siamo.data.problema.ProblemaRegistro
import com.example.siamo.data.solucion.SolucionLectura
import com.example.siamo.model.PresupuestoRepuesto
import com.example.siamo.model.Problema
import com.example.siamo.model.Response
import com.example.siamo.network.ProblemaApiService

//import com.example.siamo.network_problema.ProblemaApiService

interface ProblemaRepository {
    suspend fun getProblemas(): List<ProblemaLectura>
    suspend fun getProblemaPorId(id: Int): SolucionLectura
    suspend fun insertarProblema(problema: ProblemaRegistro): Response
    suspend fun insertarPresupuestoRepuesto(presupuestoRepuesto: PresupuestoRepuesto): Response
}

class DefaultProblemaRepository(
    private val problemaApiService: ProblemaApiService) :
    ProblemaRepository {

    override suspend fun getProblemas(): List<ProblemaLectura> {
        return problemaApiService.getTodosLosProblemas()
    }

    override suspend fun getProblemaPorId(id: Int): SolucionLectura {
        return problemaApiService.getProblemaPorId(id)
    }

    override suspend fun insertarProblema(problema: ProblemaRegistro): Response {
        return problemaApiService.insertarProblema(problema)
    }

    override suspend fun insertarPresupuestoRepuesto(presupuestoRepuesto: PresupuestoRepuesto): Response {
        return problemaApiService.insertarPresupuestoRepuesto(presupuestoRepuesto)
    }
}

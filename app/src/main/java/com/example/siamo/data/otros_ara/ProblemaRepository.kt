package com.example.siamo.data.otros_ara

import com.example.siamo.model.Problema
import com.example.siamo.network_problema.ProblemaApiService

interface ProblemaRepository {
    suspend fun getProblemas(): List<Problema>
    suspend fun getProblemaPorId(id: Int): Problema
    suspend fun insertarProblema(problema: Problema): Problema
}

class DefaultProblemaRepository(private val problemaApiService: ProblemaApiService) :
    ProblemaRepository {

    override suspend fun getProblemas(): List<Problema> {
        return problemaApiService.getTodosLosProblemas()
    }

    override suspend fun getProblemaPorId(id: Int): Problema {
        return problemaApiService.getProblemaPorId(id)
    }

    override suspend fun insertarProblema(problema: Problema): Problema {
        return problemaApiService.insertarProblema(problema)
    }
}

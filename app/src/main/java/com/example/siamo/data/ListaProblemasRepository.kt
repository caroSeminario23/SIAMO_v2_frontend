package com.example.siamo.data


import com.example.siamo.model.ListaProblemas
import com.example.siamo.network_lista_problemas.ListaProblemasApiService

interface ListaProblemasRepository {
    suspend fun getListaDeProblemasPorConsulta(idConsulta: Int): List<ListaProblemas>
    suspend fun insertarListaDeProblemas(listaProblemas: ListaProblemas)
}

class DefaultListaProblemasRepository(private val listaProblemasApiService: ListaProblemasApiService) :
    ListaProblemasRepository {

    override suspend fun getListaDeProblemasPorConsulta(idConsulta: Int): List<ListaProblemas> {
        return listaProblemasApiService.getListaDeProblemasPorConsulta(idConsulta)
    }

    override suspend fun insertarListaDeProblemas(listaProblemas: ListaProblemas) {
        listaProblemasApiService.insertarListaDeProblemas(listaProblemas)
    }
}

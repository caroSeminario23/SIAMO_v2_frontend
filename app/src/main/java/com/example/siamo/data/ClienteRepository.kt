package com.example.siamo.data

import com.example.siamo.model.Cliente
import com.example.siamo.model.Response
import com.example.siamo.network_consulta.ClienteApiService

interface ClienteRepository {
    suspend fun postCliente(cliente: Cliente): Response
    suspend fun getCliente(doc: String): Cliente
    suspend fun getClienteResumed(doc: Int): Cliente
}

class DefaultClienteRepository(private val clienteApiService: ClienteApiService) :
    ClienteRepository {
    override suspend fun postCliente(cliente: Cliente): Response {
        return clienteApiService.postCliente(cliente)
    }

    override suspend fun getCliente(doc: String): Cliente {
        return clienteApiService.getCliente(doc)
    }

    override suspend fun getClienteResumed(doc: Int): Cliente {
        return clienteApiService.getClienteResumed(doc)
    }
}
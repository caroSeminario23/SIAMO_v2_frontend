package com.example.siamo.data

import com.example.siamo.model.EstadoVehiculo
import com.example.siamo.network_inspeccion.EstadoVehiculoApiService

interface EstadoVehiculoRepository {
    suspend fun getEstadoVehiculoPorFichaIngreso(idFichaIngreso: Int): EstadoVehiculo
    suspend fun insertarEstadoVehiculo(estadoVehiculo: EstadoVehiculo): EstadoVehiculo
}

class DefaultEstadoVehiculoRepository(private val estadoVehiculoApiService: EstadoVehiculoApiService) :
    EstadoVehiculoRepository {

    override suspend fun getEstadoVehiculoPorFichaIngreso(idFichaIngreso: Int): EstadoVehiculo {
        return estadoVehiculoApiService.getEstadoVehiculoPorFichaIngreso(idFichaIngreso)
    }

    override suspend fun insertarEstadoVehiculo(estadoVehiculo: EstadoVehiculo): EstadoVehiculo {
        return estadoVehiculoApiService.insertarEstadoVehiculo(estadoVehiculo)
    }
}

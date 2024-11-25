package com.example.siamo.data

import com.example.siamo.model.FichaIngreso
import com.example.siamo.model.FichaIngresoCompleta
import com.example.siamo.model.FichaIngresoRequest
import com.example.siamo.network_inspeccion.FichaIngresoApiService

interface FichaIngresoRepository {
    suspend fun getFichaIngresoPorOst(idOst: Int): FichaIngreso
    suspend fun insertarFichaIngreso(fichaIngresoRequest: FichaIngresoRequest): FichaIngreso
    suspend fun getFullFichasIngresoPorTecnico(idTecnico: Int): List<FichaIngresoCompleta>

}

class DefaultFichaIngresoRepository(private val fichaIngresoApiService: FichaIngresoApiService) :
    FichaIngresoRepository {

    override suspend fun getFichaIngresoPorOst(idOst: Int): FichaIngreso {
        return fichaIngresoApiService.getFichaIngresoPorOst(idOst)
    }

    override suspend fun insertarFichaIngreso(fichaIngresoRequest: FichaIngresoRequest): FichaIngreso{
        return fichaIngresoApiService.insertarFichaIngreso(fichaIngresoRequest)
    }
    override suspend fun getFullFichasIngresoPorTecnico(idTecnico: Int): List<FichaIngresoCompleta> {
        return fichaIngresoApiService.getFullFichasIngresoPorTecnico(idTecnico)
    }
}

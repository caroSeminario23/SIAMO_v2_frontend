package com.example.siamo.data

import com.example.siamo.model.Empleado
import com.example.siamo.network_consulta.EmpleadoApiService

interface EmpleadoRepository {
    suspend fun loginEmpleado(codEmpleado: Int, contrasenia: String): Empleado
}

class DefaultEmpleadoRepository(private val empleadoApiService: EmpleadoApiService) :
    EmpleadoRepository {
    override suspend fun loginEmpleado(codEmpleado: Int, contrasenia: String): Empleado {
        return empleadoApiService.loginEmpleado(codEmpleado.toString(), contrasenia)
    }
}

package com.example.siamo.network_consulta

import com.example.siamo.model.Empleado
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path

interface EmpleadoApiService {
    @GET("api/empleado/login/{codEmpleado}/{contrasenia}")
    suspend fun loginEmpleado(
        @Path("codEmpleado") codEmpleado: String,
        @Path("contrasenia") contrasenia: String
    ): Empleado
}
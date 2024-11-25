package com.example.siamo.network_inspeccion

import com.example.siamo.model.FichaIngreso
import com.example.siamo.model.FichaIngresoCompleta
import com.example.siamo.model.FichaIngresoRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface FichaIngresoApiService {


    @GET("api/ficha_ingreso/get/{id_ost}")
    suspend fun getFichaIngresoPorOst(@Path("id_ost") idOst: Int): FichaIngreso


    @POST("api/ficha_ingreso/insert")
    suspend fun insertarFichaIngreso(@Body fichaIngresoRequest: FichaIngresoRequest): FichaIngreso

    @GET("api/ficha_ingreso/get_full_data_by_tecnico/{id_tecnico}")
    suspend fun getFullFichasIngresoPorTecnico(@Path("id_tecnico") idTecnico: Int): List<FichaIngresoCompleta>


}

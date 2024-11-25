package com.example.siamo.network

import com.example.siamo.model.ListaProblemas
import com.example.siamo.model.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ListaProblemasApiService {

    // Obtener la lista de problemas por consulta
    @GET("api/lista_problemas/get/{id_consulta}")
    suspend fun getListaDeProblemasPorConsulta(@Path("id_consulta") idConsulta: Int): ListaProblemas

    // Insertar una lista de problemas
    @POST("api/lista_problemas/insert")
    suspend fun insertarListaDeProblemas(@Body listaProblemas: ListaProblemas): Response
}

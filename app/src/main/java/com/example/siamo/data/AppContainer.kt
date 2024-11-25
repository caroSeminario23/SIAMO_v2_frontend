package com.example.siamo.data

import com.example.siamo.network_consulta.AutomovilApiService
import com.example.siamo.network_consulta.ClienteApiService
import com.example.siamo.network_consulta.ConsultaApiService
import com.example.siamo.network_consulta.EmpleadoApiService
import com.example.siamo.network_consulta.OstApiService
import com.example.siamo.network_consulta.PersonaApiService
import com.example.siamo.network_consulta.TecnicoApiService
import com.example.siamo.ui.recepcionista.panel_control.DefaultPanelControlRecepcionistaRepository
import com.example.siamo.ui.recepcionista.panel_control.PanelControlRecepcionistaRepository
import com.example.siamo.ui.tecnico.panel_control.DefaultGraficosRepository
import com.example.siamo.ui.tecnico.panel_control.GraficosApiService
import com.example.siamo.ui.tecnico.panel_control.GraficosRepository
import okhttp3.MediaType.Companion.toMediaType
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import retrofit2.Retrofit

interface AppContainer {
    val automovilRepository: AutomovilRepository
    val clienteRepository: ClienteRepository
    val consultaRepository: ConsultaRepository
    val personaRepository: PersonaRepository
    val tecnicoRepository: TecnicoRepository
    val ostRepository: OstRepository
    val empleadoRepository: EmpleadoRepository
    val graficosRepository: GraficosRepository
    //val panelControlRecepcionistaRepository: PanelControlRecepcionistaRepository
}

class DefaultAppContainer: AppContainer {

    private val BASE_URL = "http://192.168.18.37:5000"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json { ignoreUnknownKeys = true }.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    private val automovilApiService: AutomovilApiService by lazy {
        retrofit.create(AutomovilApiService::class.java)
    }

    private val clienteApiService: ClienteApiService by lazy {
        retrofit.create(ClienteApiService::class.java)
    }

    private val consultaApiService: ConsultaApiService by lazy {
        retrofit.create(ConsultaApiService::class.java)
    }

    private val personaApiService: PersonaApiService by lazy {
        retrofit.create(PersonaApiService::class.java)
    }

    private val tecnicoApiService: TecnicoApiService by lazy {
        retrofit.create(TecnicoApiService::class.java)
    }

    private val ostApiService: OstApiService by lazy {
        retrofit.create(OstApiService::class.java)
    }

    private val empleadoApiService: EmpleadoApiService by lazy {
        retrofit.create(EmpleadoApiService::class.java)
    }

    private val graficosApiService: GraficosApiService by lazy {
        retrofit.create(GraficosApiService::class.java)
    }

//    private val panelControlRecepcionistaApiService: PanelControlRecepcionistaApiService by lazy {
//        retrofit.create(PanelControlRecepcionistaApiService::class.java)
//    }



    override val automovilRepository: AutomovilRepository by lazy {
        DefaultAutomovilRepository(automovilApiService)
    }

    override val clienteRepository: ClienteRepository by lazy {
        DefaultClienteRepository(clienteApiService)
    }

    override val consultaRepository: ConsultaRepository by lazy {
        DefaultConsultaRepository(consultaApiService)
    }

    override val personaRepository: PersonaRepository by lazy {
        DefaultPersonaRepository(personaApiService)
    }

    override val tecnicoRepository: TecnicoRepository by lazy {
        DefaultTecnicoRepository(tecnicoApiService)
    }

    override val ostRepository: OstRepository by lazy {
        DefaultOstRepository(ostApiService)
    }

    override val empleadoRepository: EmpleadoRepository by lazy {
        DefaultEmpleadoRepository(empleadoApiService)
    }

    override val graficosRepository: GraficosRepository by lazy {
        DefaultGraficosRepository(graficosApiService)
    }

//    override val panelControlRecepcionistaRepository: PanelControlRecepcionistaRepository by lazy {
//        DefaultPanelControlRecepcionistaRepository(panelControlRecepcionistaApiService)
//    }
}
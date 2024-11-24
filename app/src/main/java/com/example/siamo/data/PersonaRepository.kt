package com.example.siamo.data

import com.example.siamo.model.Persona
import com.example.siamo.model.Response
import com.example.siamo.network_consulta.PersonaApiService

interface PersonaRepository {
    suspend fun postPersona(persona: Persona): Response
    suspend fun putPersona(id_persona: Int, persona: Persona): Response
}

class DefaultPersonaRepository(private val personaApiService: PersonaApiService) :
    PersonaRepository {
    override suspend fun postPersona(persona: Persona): Response {
        return personaApiService.postPersona(persona)
    }

    override suspend fun putPersona(id_persona: Int, persona: Persona): Response {
        return personaApiService.putPersona(id_persona, persona)
    }
}
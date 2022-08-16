package com.cw.catapoke.data.remote

import com.cw.catapoke.data.remote.dto.SpeciesDetailsDto
import com.cw.catapoke.data.remote.dto.SpeciesEvolutionDto
import com.cw.catapoke.data.remote.dto.SpeciesResponseDto

/**
 * Api service interface which contains the pokeman remote api functions and end points
 */

interface ApiService {

    companion object {
        const val BASE_URL = "https://pokeapi.co/api/v2/"
        const val END_POINT_SPECIES = "pokemon-species"
        const val END_POINT_SPECIES_DETAILS = "pokemon-species/"
        const val END_POINT_EVOLUTION = "evolution-chain/"

        const val PARAMETER_LIMIT = "limit"
        const val PARAMETER_OFFSET = "offset"
    }

    sealed class EndPoints(val url: String) {
        object GetSpecies : EndPoints(BASE_URL+END_POINT_SPECIES)
        object GetSpeciesDetails : EndPoints(BASE_URL+ END_POINT_SPECIES_DETAILS)
        object GetSpeciesEvolution : EndPoints(BASE_URL+ END_POINT_EVOLUTION)
    }

    suspend fun getSpecies(pageCount: Int, limit: Int): SpeciesResponseDto
    suspend fun getSpeciesDetails(id: Int): SpeciesDetailsDto
    suspend fun getSpeciesEvolution(id: Int): SpeciesEvolutionDto
}
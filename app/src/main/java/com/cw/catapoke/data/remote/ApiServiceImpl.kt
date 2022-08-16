package com.cw.catapoke.data.remote

import com.cw.catapoke.data.remote.ApiService.Companion.PARAMETER_LIMIT
import com.cw.catapoke.data.remote.ApiService.Companion.PARAMETER_OFFSET
import com.cw.catapoke.data.remote.dto.SpeciesDetailsDto
import com.cw.catapoke.data.remote.dto.SpeciesEvolutionDto
import com.cw.catapoke.data.remote.dto.SpeciesResponseDto
import io.ktor.client.*
import io.ktor.client.request.*

/**
 * Api service implementation for remote api calls with request parameters via provided http client
 *
 * @property client
 */

class ApiServiceImpl(private val client: HttpClient) : ApiService {

    override suspend fun getSpecies(pageCount: Int, limit: Int) : SpeciesResponseDto {
        return try {
            client.get {
                url(ApiService.EndPoints.GetSpecies.url)
                parameter(PARAMETER_LIMIT, 20)
                parameter(PARAMETER_OFFSET, pageCount)
            }
        } catch (ex : Exception) {
             ex.printStackTrace()
             SpeciesResponseDto(0,null, null, listOf())
        }
    }

    override suspend fun getSpeciesDetails(id:Int): SpeciesDetailsDto {
        return try {
            client.get {
                url(ApiService.EndPoints.GetSpeciesDetails.url + id)
            }
        } catch (ex: Exception) {
            SpeciesDetailsDto(null, null, emptyList(), null)
        }
    }

    override suspend fun getSpeciesEvolution(id: Int): SpeciesEvolutionDto {
        return try {
            client.get {
                url(ApiService.EndPoints.GetSpeciesEvolution.url + id)
            }
        } catch (ex: Exception) {
            SpeciesEvolutionDto(id=null, chain = null)
        }
    }
}
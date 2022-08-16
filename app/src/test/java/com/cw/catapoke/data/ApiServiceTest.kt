package com.cw.catapoke.data

import com.cw.catapoke.data.remote.ApiClient
import com.cw.catapoke.data.remote.ApiServiceImpl
import com.cw.catapoke.mock.ApiFailureMockEngine
import com.cw.catapoke.mock.ApiSuccessMockEngine
import kotlinx.coroutines.runBlocking
import org.junit.Test

class ApiServiceTest {

    private val apiMockClient = ApiClient.getClient(ApiSuccessMockEngine().getEngine())
    private val apiFailureMockClient = ApiClient.getClient(ApiFailureMockEngine().getEngine())

    private val apiService = ApiServiceImpl(apiMockClient)
    private val apiServiceFailureCase = ApiServiceImpl(apiFailureMockClient)

    @Test
    fun `test species list api success response`() = runBlocking {
        val response = apiService.getSpecies(1, 20)
        assert(response.results.size == 20)
    }

    @Test
    fun `test species details api success response`() = runBlocking {
        val response = apiService.getSpeciesDetails(1)
        assert(response.captureRate == 45)
    }

    @Test
    fun `test species evolution api success response`() = runBlocking {
        val response = apiService.getSpeciesEvolution(1)
        assert(response.id == 1)
    }

    @Test
    fun `test species evolution api failure response`() = runBlocking {
        val response = apiServiceFailureCase.getSpeciesEvolution(1)
        assert(response.chain == null)
    }

    @Test
    fun `test species details api failure response`() = runBlocking {
        val response = apiServiceFailureCase.getSpeciesDetails(1)
        assert(response.name == null)
    }

}
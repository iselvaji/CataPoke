package com.cw.catapoke.mock

import com.cw.catapoke.data.remote.ApiService
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.engine.mock.*
import io.ktor.http.*

class ApiSuccessMockEngine : MockEngine {

    private val responseHeaders = headersOf("Content-Type" to
            listOf(ContentType.Application.Json.toString()))

    private val client = HttpClient(MockEngine) {
        engine {
            addHandler { request ->
                var mockResponse = ""
                val statusCode = HttpStatusCode.OK

                val requestUrl = request.url.toString()

                if (requestUrl.contains(ApiService.PARAMETER_OFFSET)) {
                    mockResponse = SpeicesMockResponse.getMockData()
                }
                else if (requestUrl.contains(ApiService.END_POINT_SPECIES)) {
                    mockResponse = SpeicesDetailsMockResponse.getMockData()
                }
                else if (requestUrl.contains(ApiService.END_POINT_EVOLUTION)) {
                    mockResponse = SpeciesEvolutionResponse.getMockData()
                }
                respond(mockResponse, statusCode, responseHeaders)
            }
        }
    }

    override fun getEngine(): HttpClientEngine {
        return client.engine
    }
}




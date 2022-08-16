package com.cw.catapoke.data.remote

import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*

/**
 * Api client provides the Ktor Http Client
 *
 */
object ApiClient {

    fun getClient(httpClientEngine: HttpClientEngine)
            = HttpClient(httpClientEngine) {

        install(Logging) {
            level = LogLevel.ALL
        }
        install(JsonFeature) {
            serializer = KotlinxSerializer(json = kotlinx.serialization.json.Json {
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }
}
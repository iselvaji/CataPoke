package com.cw.catapoke.di

import com.cw.catapoke.data.remote.ApiService
import com.cw.catapoke.data.remote.ApiServiceImpl
import com.cw.catapoke.data.repository.RepositoryImpl
import com.cw.catapoke.domain.repository.Repository
import com.cw.catapoke.domain.usecase.SpeciesDetailsUsecase
import com.cw.catapoke.domain.usecase.SpeciesEvolutionDetailsUsecase
import com.cw.catapoke.domain.usecase.SpeciesUsecase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Provides
    @Singleton
    fun provideHttpClient() : HttpClient {
        return HttpClient(Android) {
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

    @Provides
    @Singleton
    fun provideApiService(client: HttpClient) : ApiService {
        return ApiServiceImpl(client)
    }

    @Provides
    @Singleton
    fun provideRepository(apiService: ApiService) : Repository {
        return RepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideSpeciesUsecase(repository: Repository) : SpeciesUsecase {
        return SpeciesUsecase(repository)
    }

    @Provides
    @Singleton
    fun provideSpeciesDetailsUsecase(repository: Repository) : SpeciesDetailsUsecase {
        return SpeciesDetailsUsecase(repository)
    }

    @Provides
    @Singleton
    fun provideSSpeciesEvolutionDetailsUsecase(repository: Repository) : SpeciesEvolutionDetailsUsecase {
        return SpeciesEvolutionDetailsUsecase(repository)
    }
}
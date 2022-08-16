package com.cw.catapoke.di

import com.cw.catapoke.data.remote.ApiClient
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
import javax.inject.Singleton

/**
 * App module contains Hilt dependency injection object provides dependency details
 */

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHttpClient() : HttpClient {
        return ApiClient.getClient(HttpClient().engine)
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
    fun provideSpeciesEvolutionDetailsUsecase(repository: Repository) : SpeciesEvolutionDetailsUsecase {
        return SpeciesEvolutionDetailsUsecase(repository)
    }
}
package com.cw.catapoke.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.cw.catapoke.data.mapper.toSpeciesDetails
import com.cw.catapoke.data.mapper.toSpeciesEvolution
import com.cw.catapoke.data.paging.SpeciesPagingSource
import com.cw.catapoke.data.paging.SpeciesPagingSource.Companion.PAGE_SIZE
import com.cw.catapoke.data.paging.SpeciesPagingSource.Companion.PRE_FETCH_DISTANCE
import com.cw.catapoke.data.remote.ApiService
import com.cw.catapoke.domain.model.SpeciesDetails
import com.cw.catapoke.domain.model.SpeciesEvolution
import com.cw.catapoke.domain.model.SpeciesResult
import com.cw.catapoke.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


/**
 * Repository implementation which communicates to the remote data source [apiService] and fetch the details
 * @property apiService
 */
class RepositoryImpl @Inject constructor(private val apiService: ApiService) : Repository {

    override suspend fun getSpecies(): Flow<PagingData<SpeciesResult>> {
        return Pager(PagingConfig(pageSize = PAGE_SIZE, prefetchDistance = PRE_FETCH_DISTANCE)) {
            SpeciesPagingSource(apiService)
        }.flow
    }

    override suspend fun getSpeciesDetails(id: Int): SpeciesDetails {
        return apiService.getSpeciesDetails(id).toSpeciesDetails(id)
    }

    override suspend fun getSpeciesEvolution(id: Int): SpeciesEvolution {
        return apiService.getSpeciesEvolution(id).toSpeciesEvolution()
    }
}
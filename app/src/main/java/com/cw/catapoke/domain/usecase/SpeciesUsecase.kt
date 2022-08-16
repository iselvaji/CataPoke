package com.cw.catapoke.domain.usecase

import androidx.paging.PagingData
import com.cw.catapoke.domain.model.SpeciesResult
import com.cw.catapoke.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

/**
 * Species usecase to get all the pokemon species results as a paging data
 *
 * @property repository
 */
class SpeciesUsecase(private val repository: Repository) {
    suspend fun getSpeciesList():  Flow<PagingData<SpeciesResult>> {
        return repository.getSpecies()
    }
}
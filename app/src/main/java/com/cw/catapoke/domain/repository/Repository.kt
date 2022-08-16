package com.cw.catapoke.domain.repository

import androidx.paging.PagingData
import com.cw.catapoke.domain.model.SpeciesDetails
import com.cw.catapoke.domain.model.SpeciesEvolution
import com.cw.catapoke.domain.model.SpeciesResult
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getSpecies() : Flow<PagingData<SpeciesResult>>
    suspend fun getSpeciesDetails(id: Int) : SpeciesDetails
    suspend fun getSpeciesEvolution(id: Int) : SpeciesEvolution
}
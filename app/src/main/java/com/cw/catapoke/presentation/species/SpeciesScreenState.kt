package com.cw.catapoke.presentation.species

import androidx.paging.PagingData
import com.cw.catapoke.domain.model.SpeciesResult
import kotlinx.coroutines.flow.Flow

/**
 * Species screen state
 *
 * @property speciesPagingData
 * @property error
 */
data class SpeciesScreenState(
    val speciesPagingData: Flow<PagingData<SpeciesResult>>? = null,
    val error: Int? = null
)

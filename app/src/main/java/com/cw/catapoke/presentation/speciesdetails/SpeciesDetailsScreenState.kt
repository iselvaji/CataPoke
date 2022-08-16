package com.cw.catapoke.presentation.speciesdetails

import com.cw.catapoke.domain.model.SpeciesDetails

/**
 * Species details screen state
 *
 * @property currentSpeciesDetails
 * @property evolutionSpeciesDetails
 * @property isLoading
 * @property error
 * @property captureRateDiff
 */
data class SpeciesDetailsScreenState(
    val currentSpeciesDetails: SpeciesDetails? = null,
    val evolutionSpeciesDetails : SpeciesDetails? = null,
    val isLoading : Boolean = false,
    val error: Int? = null,
    val captureRateDiff: Pair<Int, Boolean>? = null
)

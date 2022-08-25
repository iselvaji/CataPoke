package com.cw.catapoke.presentation.speciesdetails

/**
 * Species details screen user action/events
 *
 */
sealed class SpeciesDetailsScreenEvent {
    object FetchSpeciesDetails: SpeciesDetailsScreenEvent()
}
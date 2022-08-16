package com.cw.catapoke.presentation.species

import com.ramcosta.composedestinations.navigation.DestinationsNavigator

/**
 * Species screen user action/events
 *
 */
sealed class SpeciesScreenEvent {
    data class OnSpeciesSelect(val id: Int,  val navigator: DestinationsNavigator): SpeciesScreenEvent()
}
package com.cw.catapoke.presentation.species

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cw.catapoke.domain.usecase.SpeciesUsecase
import com.cw.catapoke.presentation.destinations.SpeciesDetailsScreenDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SpeciesListViewModel @Inject constructor(
    private val useCase: SpeciesUsecase) : ViewModel() {
    var state by mutableStateOf(SpeciesScreenState())

    init {
       getSpeciesData()
    }

    fun onUiEvent(event: SpeciesScreenEvent) {
        when(event) {
            is SpeciesScreenEvent.OnSpeciesSelect -> {
                event.navigator.navigate(SpeciesDetailsScreenDestination(event.id))
            }
        }
    }

    private fun getSpeciesData() {
        viewModelScope.launch {
            state = state.copy(speciesPagingData = useCase.getSpeciesList())
        }
    }
}
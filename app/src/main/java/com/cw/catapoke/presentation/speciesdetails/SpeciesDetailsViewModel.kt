package com.cw.catapoke.presentation.speciesdetails

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cw.catapoke.R
import com.cw.catapoke.domain.model.SpeciesDetails
import com.cw.catapoke.domain.usecase.SpeciesDetailsUsecase
import com.cw.catapoke.domain.usecase.SpeciesEvolutionDetailsUsecase
import com.cw.catapoke.utils.AppUtil
import com.cw.catapoke.utils.AppUtil.isPositiveCaptureRateDiff
import com.cw.catapoke.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject

@HiltViewModel
class SpeciesDetailsViewModel
    @Inject constructor(
        private val savedStateHandle: SavedStateHandle,
        private val detailsUsecase : SpeciesDetailsUsecase,
        private val evolutionDetailsUsecase: SpeciesEvolutionDetailsUsecase
    ) : ViewModel() {

    var state by mutableStateOf(SpeciesDetailsScreenState())
    private val evolutionOrder = AppUtil.EvolutionOrder.First

    init {
        getSpeciesDetails()
    }

    fun onUiEvent(event: SpeciesDetailsScreenEvent) {
        when(event) {
            is SpeciesDetailsScreenEvent.FetchSpeciesDetails -> {
                getSpeciesDetails()
            }
        }
    }

    /**
     * Get species details for given species(pokemon) id
     * Get the species evolution details for given species(pokemon) id
     * Iterate the evolution chain details and find the first[AppUtil.EvolutionOrder.First] evolution species(pokemon) id
     * Get the evolution details for the evolution species(pokemon) id
     * Update the state with all the response details which is required for UI
     * Supervisor scope used so if any of above task fails other task will continue to execute
     */
    private fun getSpeciesDetails() {
        val currentSpeciesId = savedStateHandle.get<Int>("selectedSpeciesId")

        viewModelScope.launch {
            currentSpeciesId?.let {
                supervisorScope {

                    state = state.copy(isLoading = true, error = null)

                    val getSpeciesDetailsTask = async(start = CoroutineStart.LAZY) {
                        detailsUsecase.getSpeciesDetails(currentSpeciesId)
                    }
                    val currentSpecialDetailsResponse = getSpeciesDetailsTask.await()

                    currentSpecialDetailsResponse.apply {
                        when(this) {
                            is Resource.Success -> {

                               val evolutionUrlId =  AppUtil.getId(this.data?.evolutionChainUrl?.url)

                                val getSpeciesEvolutionDetailsTask = async(start = CoroutineStart.LAZY) {
                                    evolutionDetailsUsecase.getSpeciesEvolutionByOrder(evolutionUrlId, evolutionOrder, currentSpeciesId)
                                }

                                val evolutionDetailsResponse = getSpeciesEvolutionDetailsTask.await()

                                evolutionDetailsResponse.let {
                                    when(it) {
                                        is Resource.Success -> {
                                            it.data?.let { speciesId ->
                                                if(speciesId > 0) {
                                                    val getSpeciesByOderTask = async(start = CoroutineStart.LAZY) {
                                                        detailsUsecase.getSpeciesDetails(speciesId)
                                                    }
                                                    val specialDetailsByEvolutionOrderResponse = getSpeciesByOderTask.await()
                                                    updateUIState(
                                                        currentSpecialDetailsResponse,
                                                        specialDetailsByEvolutionOrderResponse)
                                                }
                                                else {
                                                    updateUIState(currentSpecialDetailsResponse)
                                                }
                                            }
                                        }
                                        else -> {
                                            updateUIState(currentSpecialDetailsResponse)
                                        }
                                    }
                                }
                            }
                            else -> {
                                updateUIState(currentSpecialDetailsResponse)
                            }
                        }
                    }
                }
            }
        }
    }

    // update the state based on response received
    private fun updateUIState(
        currentSpecialDetailsResponse: Resource<SpeciesDetails>,
        specialDetailsByEvolutionOrderResponse: Resource<SpeciesDetails>? = null,
    ) {

        var currentSpeciesCaptureRate = 0
        var evolutionSpeciesCaptureRate = 0

        currentSpecialDetailsResponse.let {
            when (it) {
                is Resource.Success -> {
                    currentSpeciesCaptureRate = it.data?.captureRate ?: -1
                    state = state.copy(currentSpeciesDetails = it.data, isLoading = false)
                }
                is Resource.Error -> {
                    state = state.copy(isLoading = false, error = R.string.err_unable_to_load)
                }
                is Resource.Loading -> {
                    state = state.copy(isLoading = true, error = null)
                }
            }
        }

        specialDetailsByEvolutionOrderResponse?.let {
            when (it) {
                is Resource.Success -> {
                    evolutionSpeciesCaptureRate = it.data?.captureRate ?: -1
                    state = state.copy(
                        evolutionSpeciesDetails = it.data,
                        captureRateDiff = isPositiveCaptureRateDiff(
                            currentSpeciesCaptureRate,
                            evolutionSpeciesCaptureRate
                        ),
                        isLoading = false
                    )
                }
                is Resource.Error -> {
                    state = state.copy(isLoading = false, error = R.string.err_unable_to_load_evolution)
                }
                is Resource.Loading -> {
                    state = state.copy(isLoading = true, error = null)
                }
            }
        }
    }
}
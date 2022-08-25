package com.cw.catapoke.presentation

import androidx.lifecycle.SavedStateHandle
import com.cw.catapoke.TestData
import com.cw.catapoke.domain.model.SpeciesDetails
import com.cw.catapoke.domain.usecase.SpeciesDetailsUsecase
import com.cw.catapoke.domain.usecase.SpeciesEvolutionDetailsUsecase
import com.cw.catapoke.presentation.speciesdetails.SpeciesDetailsViewModel
import com.cw.catapoke.utils.Resource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SpeciesDetailsViewModelTest {

    @get:Rule
    val dispatcherRule = ViewModelRule()

    @MockK
    private lateinit var speciesDetailsUsecase: SpeciesDetailsUsecase

    @MockK
    private lateinit var speciesEvolutionDetailsUsecase: SpeciesEvolutionDetailsUsecase

    private lateinit var speciesDetailsViewModel: SpeciesDetailsViewModel

    private lateinit var savedStateHandle: SavedStateHandle

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)

        speciesDetailsUsecase = mockk()
        speciesEvolutionDetailsUsecase = mockk()

        savedStateHandle = SavedStateHandle()
        savedStateHandle["selectedSpeciesId"] = 1
    }

    @Test
    fun `for success resource, data must be available for async functions`() = runTest {

        // given
        val speciesDetailsResponse = Resource.Success(TestData.getSpeciesDetails())

        coEvery {
            speciesDetailsUsecase.getSpeciesDetails(any())
        } returns speciesDetailsResponse

        val speciesEvolutionResponse = Resource.Success(1)
        coEvery {
            speciesEvolutionDetailsUsecase.getSpeciesEvolutionByOrder(any(), any(), any())
        } returns speciesEvolutionResponse

        // when
        speciesDetailsViewModel = SpeciesDetailsViewModel(
            savedStateHandle,
            speciesDetailsUsecase,
            speciesEvolutionDetailsUsecase
        )

        // then
        advanceUntilIdle()
        assert(!speciesDetailsViewModel.state.isLoading)
        assert(speciesDetailsViewModel.state.currentSpeciesDetails != null)
        assert(speciesDetailsViewModel.state.evolutionSpeciesDetails != null)

    }

    @Test
    fun `for loading resource, data should be null && isLoading should be true`() = runTest {

        // given
        val speciesDetailsLoading = Resource.Loading<SpeciesDetails>(isLoading = true)

        coEvery {
            speciesDetailsUsecase.getSpeciesDetails(any())
        } returns speciesDetailsLoading

        val speciesEvolutionLoading = Resource.Loading<Int>(isLoading = true)
        coEvery {
            speciesEvolutionDetailsUsecase.getSpeciesEvolutionByOrder(any(), any(), any())
        } returns speciesEvolutionLoading

        // when
        speciesDetailsViewModel = SpeciesDetailsViewModel(
            savedStateHandle,
            speciesDetailsUsecase,
            speciesEvolutionDetailsUsecase
        )

        // then
        assert(speciesDetailsViewModel.state.isLoading)
        assert(speciesDetailsViewModel.state.error == null)
    }

    @Test
    fun `for error resource, data should be null && errorMessage should be present for async functions`() = runTest {

        // given
        val errorSpeciesDetails = Resource.Error<SpeciesDetails>(message = "Test Error")
        coEvery {
            speciesDetailsUsecase.getSpeciesDetails(any())
        } returns errorSpeciesDetails

        val errorSpeciesEvolution = Resource.Error<Int>(message = "Test Evolution Error")
        coEvery {
            speciesEvolutionDetailsUsecase.getSpeciesEvolutionByOrder(any(), any(), any())
        } returns errorSpeciesEvolution

        // when
        speciesDetailsViewModel = SpeciesDetailsViewModel(
            savedStateHandle,
            speciesDetailsUsecase,
            speciesEvolutionDetailsUsecase
        )

        // then
        assert(!speciesDetailsViewModel.state.isLoading)
        assert(speciesDetailsViewModel.state.error != null)
        assert(speciesDetailsViewModel.state.evolutionSpeciesDetails == null)
        assert(speciesDetailsViewModel.state.currentSpeciesDetails == null)

    }

    @Test
    fun `success task details are avaliable even if other task failed when executing async taks parallelly`() = runTest {

        // given
        val speciesDetailsResponse = Resource.Success(TestData.getSpeciesDetails())

        coEvery {
            speciesDetailsUsecase.getSpeciesDetails(any())
        } returns speciesDetailsResponse

        val errorSpeciesEvolution = Resource.Error<Int>(message = "Test Evolution Error")
        coEvery {
            speciesEvolutionDetailsUsecase.getSpeciesEvolutionByOrder(any(), any(), any())
        } returns errorSpeciesEvolution

        // when
        speciesDetailsViewModel = SpeciesDetailsViewModel(
            savedStateHandle,
            speciesDetailsUsecase,
            speciesEvolutionDetailsUsecase
        )

        // then
        assert(!speciesDetailsViewModel.state.isLoading)
        assert(speciesDetailsViewModel.state.error == null)
        assert(speciesDetailsViewModel.state.evolutionSpeciesDetails == null)
        assert(speciesDetailsViewModel.state.currentSpeciesDetails != null)

    }
}
package com.cw.catapoke.presentation

import androidx.paging.PagingData
import com.cw.catapoke.TestData
import com.cw.catapoke.domain.usecase.SpeciesUsecase
import com.cw.catapoke.presentation.species.SpeciesListViewModel
import com.cw.catapoke.presentation.species.SpeciesScreenEvent
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SpeciesViewModelTest {

    @get:Rule
    val dispatcherRule = ViewModelRule()

    @MockK
    private lateinit var speciesUsecase: SpeciesUsecase

    private lateinit var speciesListViewModel: SpeciesListViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        speciesUsecase = mockk()
        speciesListViewModel = SpeciesListViewModel(speciesUsecase)
    }

    @Test
    fun `for success resource, data must be available`() = runTest {

        // given
        val data = flow {
            delay(100L)
            val pagingData = PagingData.from(TestData.getSpeciesList())
            emit(pagingData)
        }

        coEvery {
            speciesUsecase.getSpeciesList()
        } returns data

        // when
        speciesListViewModel.onUiEvent(SpeciesScreenEvent.FetchSpeciesData)

        // then
        assert(speciesListViewModel.state.error == null)

        assert(speciesListViewModel.state.speciesPagingData?.count()!! == data.count())
    }
}
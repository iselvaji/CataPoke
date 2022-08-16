package com.cw.catapoke.domain

import com.cw.catapoke.domain.usecase.SpeciesEvolutionDetailsUsecase
import com.cw.catapoke.utils.AppUtil
import com.cw.catapoke.utils.Resource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class SpeciesEvolutionDetailsUsecaseTest {

    @MockK
    lateinit var speciesEvolutionDetailsUsecase: SpeciesEvolutionDetailsUsecase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        speciesEvolutionDetailsUsecase = mockk()
    }


    @Test
    fun `get species evolution details by order and validate`()  = runBlocking {

        // given
        val data = Resource.Success(2)

        // when
        coEvery {
            speciesEvolutionDetailsUsecase.getSpeciesEvolutionByOrder(1, AppUtil.EvolutionOrder.First)
        } returns data

        // then
        val actual = speciesEvolutionDetailsUsecase.getSpeciesEvolutionByOrder(1, AppUtil.EvolutionOrder.First)

        assert(actual is Resource.Success)
        assert(actual.data!! == 2)
    }
}

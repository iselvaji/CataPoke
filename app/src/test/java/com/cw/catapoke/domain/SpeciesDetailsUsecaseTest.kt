package com.cw.catapoke.domain

import com.cw.catapoke.TestData.getSpeciesDetails
import com.cw.catapoke.domain.usecase.SpeciesDetailsUsecase
import com.cw.catapoke.utils.Resource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class SpeciesDetailsUsecaseTest {

    @MockK
    lateinit var speciesDetailsUsecase: SpeciesDetailsUsecase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        speciesDetailsUsecase = mockk()
    }

    @Test
    fun `get species details and validate`() = runBlocking {

        // given
        val data = Resource.Success(getSpeciesDetails())

        // when
        coEvery {
            speciesDetailsUsecase.getSpeciesDetails(1)
        } returns data

        // then
        val response = speciesDetailsUsecase.getSpeciesDetails(1).data
        assert(response != null)

        response!!.apply {
            assert(captureRate == 40)
            assert(flavorEntries.isNotEmpty())
            assert(name == "Test Poke")
        }
    }
}
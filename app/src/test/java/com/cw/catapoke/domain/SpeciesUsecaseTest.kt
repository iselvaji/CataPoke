package com.cw.catapoke.domain

import androidx.paging.PagingData
import com.cw.catapoke.TestData
import com.cw.catapoke.domain.usecase.SpeciesUsecase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class SpeciesUsecaseTest {

    @MockK
    lateinit var speciesUsecase: SpeciesUsecase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        speciesUsecase = mockk()
    }

    @Test
    fun `get species list and validate`()  = runBlocking {

        // given
        val data = flow {
            delay(100L)
            val pagingData = PagingData.from(TestData.getSpeciesList())
            emit(pagingData)
        }

        // when
        coEvery {
            speciesUsecase.getSpeciesList()
        } returns data

        val count = speciesUsecase.getSpeciesList().count()

        // then
        assertEquals(count, data.count())
        assertEquals(speciesUsecase.getSpeciesList(), data)
    }
}
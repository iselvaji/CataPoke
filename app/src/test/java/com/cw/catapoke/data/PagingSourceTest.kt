package com.cw.catapoke.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingSource
import com.cw.catapoke.TestData
import com.cw.catapoke.data.mapper.toSpeciesResponse
import com.cw.catapoke.data.paging.SpeciesPagingSource
import com.cw.catapoke.data.remote.ApiService
import com.cw.catapoke.data.remote.dto.SpeciesResultDto
import io.ktor.client.features.*
import io.ktor.client.request.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class PagingSourceTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    private val testDispatcher = StandardTestDispatcher()

    @Mock
    private lateinit var apiService: ApiService

    private lateinit var pagingSource: SpeciesPagingSource

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        pagingSource = SpeciesPagingSource(apiService)
    }

    @Test
    fun `test paging source load failure`() = runBlocking {

        val error = HttpRequestTimeoutException(HttpRequestBuilder())

        Mockito.doThrow(error)
            .`when`(apiService)
            .getSpecies(anyInt(), anyInt())

        val expectedResult = PagingSource.LoadResult.Error<Int, SpeciesResultDto>(error)

        assertEquals(
            expectedResult, pagingSource.load(
                PagingSource.LoadParams.Refresh(
                    key = null,
                    loadSize = 1,
                    placeholdersEnabled = false
                )
            )
        )
    }

    @Test
    fun `test paging source load refresh success`() = runBlocking {

        val data = TestData.getSpeciesDto()

        Mockito.doReturn(data)
            .`when`(apiService)
            .getSpecies(anyInt(),anyInt())

        val expectedResult = PagingSource.LoadResult.Page(
            data = data.toSpeciesResponse().results,
            prevKey = null,
            nextKey = 1
        )

        assertEquals(
            expectedResult, pagingSource.load(
                PagingSource.LoadParams.Refresh(
                    key = null,
                    loadSize = 30,
                    placeholdersEnabled = false
                )
            )
        )
    }

    @Test
    fun `test user paging source load prepend success`()= runBlocking {

        val data = TestData.getSpeciesDto()

        Mockito.doReturn(data)
            .`when`(apiService)
            .getSpecies(anyInt(),anyInt())

        val expectedResult = PagingSource.LoadResult.Page(
            data = data.toSpeciesResponse().results,
            prevKey = 0,
            nextKey = 2
        )

        assertEquals(
            expectedResult, pagingSource.load(
                PagingSource.LoadParams.Prepend(
                    key = 1,
                    loadSize = 20,
                    placeholdersEnabled = false
                )
            )
        )
    }
}
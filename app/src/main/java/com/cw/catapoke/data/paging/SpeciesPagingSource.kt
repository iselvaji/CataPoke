package com.cw.catapoke.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.cw.catapoke.data.mapper.toSpeciesResponse
import com.cw.catapoke.data.remote.ApiService
import com.cw.catapoke.domain.model.SpeciesResult

/**
 * Species paging source which loads list of pokemons from api by given [PAGE_SIZE] per page
 * When page size increase [when user scroll] then next lits of data from api will be fetched
 * @property apiService
 */

class SpeciesPagingSource(private val apiService: ApiService) :
    PagingSource<Int, SpeciesResult>() {

    override fun getRefreshKey(state: PagingState<Int, SpeciesResult>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SpeciesResult> {
        return try {
            val nextPage = params.key ?: 0
            val response = apiService.getSpecies(nextPage, PAGE_SIZE).toSpeciesResponse()
            LoadResult.Page(
                data = response.results,
                prevKey = if(nextPage > 0)  nextPage - 1 else null,
                nextKey = if(nextPage < response.count)  nextPage + 1 else null
            )
        } catch (ex: Exception){
            LoadResult.Error(ex)
        }
    }

    companion object {
        const val PAGE_SIZE = 20
        const val PRE_FETCH_DISTANCE = 2
    }
}
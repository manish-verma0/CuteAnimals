package com.example.cuteanimals.data.model

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.cuteanimals.data.repo.CatService
import retrofit2.HttpException
import java.io.IOException

class CatsPagingSource(private val service: CatService): PagingSource<Int, Cat>() {
    override fun getRefreshKey(state: PagingState<Int, Cat>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Cat> {
        val pageIndex = params.key ?: 0
        return try {
            val response = service.getCatList(
                page = pageIndex
            )
            val list : List<Cat> = response.body() ?: emptyList()
            val nextKey =
                if (list.isEmpty()) {
                    null
                } else {
                    // By default, initial load size = 3 * NETWORK PAGE SIZE
                    // ensure we're not requesting duplicating items at the 2nd request
                    pageIndex + (params.loadSize / 10)
                }
            LoadResult.Page(
                data = list,
                prevKey = if (pageIndex == 0) null else pageIndex,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}
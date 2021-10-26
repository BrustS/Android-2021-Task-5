package by.brust.task_5_cats.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import by.brust.task_5_cats.network.CatApi
import by.brust.task_5_cats.utils.Constants
import java.io.IOException
import retrofit2.HttpException

class CatPagingSource constructor(private val catApi: CatApi) : PagingSource<Int, Cat>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Cat> {
        val page = params.key ?: Constants.DEFAULT_PAGE_INDEX
        return try {
            val response = catApi.getCats(page, params.loadSize)
            LoadResult.Page(
                response,
                prevKey = if (page == Constants.DEFAULT_PAGE_INDEX) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Cat>): Int? {
        return null
    }
}

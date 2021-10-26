package by.brust.task_5_cats.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import by.brust.task_5_cats.network.CatApi
import javax.inject.Inject

class CatRepository @Inject constructor(private val catApi: CatApi) {
    fun getCatImagesFromNetwork() = Pager(
        config = PagingConfig(
            initialLoadSize = 10,
            pageSize = 6,
            maxSize = 100,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { CatPagingSource(catApi) }
    ).liveData
}

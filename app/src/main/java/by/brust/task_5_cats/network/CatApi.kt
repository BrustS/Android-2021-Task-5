package by.brust.task_5_cats.network

import by.brust.task_5_cats.data.Cat
import retrofit2.http.GET
import retrofit2.http.Query

interface CatApi {
    companion object {
        const val BASE_URL = "https://api.thecatapi.com"
    }

    @GET("v1/images/search")
    suspend fun getCats(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): List<Cat>
}

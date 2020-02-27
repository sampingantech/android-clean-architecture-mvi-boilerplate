package org.buffer.android.boilerplate.remote

import com.google.gson.annotations.SerializedName
import io.reactivex.Flowable
import org.buffer.android.boilerplate.remote.model.ArticleModel
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Defines the abstract methods used for interacting with the Article API
 */
interface ArticleService {

    @GET("top-headlines?country=id")
    fun getArticles(@Query("apiKey")key: String): Flowable<EverythingResponse>

    class EverythingResponse(
            val status: String? = null,
            val totalResults: Int? = null,
            val articles: List<ArticleModel> = listOf()
    )
}
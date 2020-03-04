package org.buffer.android.boilerplate.domain.repository

import kotlinx.coroutines.flow.Flow
import org.buffer.android.boilerplate.domain.model.Article

/**
 * Interface defining methods for how the data layer can pass data to and from the Domain layer.
 * This is to be implemented by the data layer, setting the requirements for the
 * operations that need to be implemented
 */
interface ArticleRepository {

    suspend fun clearArticle()

    suspend fun saveArticles(articles: List<Article>)

    fun getArticles(): Flow<List<Article>>

}
package org.buffer.android.boilerplate.data.repository

import kotlinx.coroutines.flow.Flow
import org.buffer.android.boilerplate.data.model.ArticleEntity

/**
 * Interface defining methods for the data operations related to Articles.
 * This is to be implemented by external data source layers, setting the requirements for the
 * operations that need to be implemented
 */
interface ArticleDataStore {

    suspend fun clearArticles()

    suspend fun saveArticles(articles: List<ArticleEntity>)

    fun getArticles(): Flow<List<ArticleEntity>>

    fun isCached(): Flow<Boolean>

}
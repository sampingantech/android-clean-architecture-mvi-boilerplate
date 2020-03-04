package org.buffer.android.boilerplate.data.repository

import kotlinx.coroutines.flow.Flow
import org.buffer.android.boilerplate.data.model.ArticleEntity

interface ArticleCache {

    /**
     * Clear all Articles from the cache.
     */
    suspend fun clearArticles()

    /**
     * Save a given list of Articles to the cache.
     */
    suspend fun saveArticles(articles: List<ArticleEntity>)

    /**
     * Retrieve a list of Articles, from the cache.
     */
    fun getArticles(): Flow<List<ArticleEntity>>

    /**
     * Check whether there is a list of Articles stored in the cache.
     *
     * @return true if the list is cached, otherwise false
     */
    fun isCached(): Flow<Boolean>

    /**
     * Set a point in time at when the cache was last updated.
     *
     * @param lastCache the point in time at when the cache was last updated
     */
    fun setLastCacheTime(lastCache: Long)

    /**
     * Check if the cache is expired.
     *
     * @return true if the cache is expired, otherwise false
     */
    fun isExpired(): Boolean

}
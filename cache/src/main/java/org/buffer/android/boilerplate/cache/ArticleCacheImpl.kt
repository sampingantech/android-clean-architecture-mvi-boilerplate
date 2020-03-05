package org.buffer.android.boilerplate.cache

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import org.buffer.android.boilerplate.cache.db.BufferoosDatabase
import org.buffer.android.boilerplate.cache.mapper.ArticleEntityMapper
import org.buffer.android.boilerplate.data.model.ArticleEntity
import org.buffer.android.boilerplate.data.repository.ArticleCache
import javax.inject.Inject

/**
 * Cached implementation for retrieving and saving Bufferoo instances. This class implements the
 * [ArticleCache] from the Data layer as it is that layers responsibility for defining the
 * operations in which data store implementation layers can carry out.
 */
class ArticleCacheImpl @Inject constructor(val bufferoosDatabase: BufferoosDatabase,
                                           private val entityMapper: ArticleEntityMapper,
                                           private val preferencesHelper: PreferencesHelper) :
        ArticleCache {

    private val EXPIRATION_TIME = (60 * 10 * 1000).toLong()

    /**
     * Retrieve an instance from the database, used for tests.
     */
    internal fun getDatabase(): BufferoosDatabase {
        return bufferoosDatabase
    }

    override suspend fun clearArticles() {
        bufferoosDatabase.cachedArticleDao().clearArticles()
    }

    override suspend fun saveArticles(articles: List<ArticleEntity>) {
        articles.forEach {
            bufferoosDatabase.cachedArticleDao().insertSArticles(
                    entityMapper.mapToCached(it))
        }
    }

    override fun getArticles(): Flow<List<ArticleEntity>> {
        return flowOf(bufferoosDatabase.cachedArticleDao().getArticles()).map {
            it.map { entityMapper.mapFromCached(it) }
        }
    }

    /**
     * Check whether there are instances of [CachedArticleDao] stored in the cache.
     */
    override fun isCached(): Flow<Boolean> {
        return flowOf(bufferoosDatabase.cachedArticleDao().getArticles().isNotEmpty())
    }

    /**
     * Set a point in time at when the cache was last updated.
     */
    override fun setLastCacheTime(lastCache: Long) {
        preferencesHelper.lastCacheTime = lastCache
    }

    /**
     * Check whether the current cached data exceeds the defined [EXPIRATION_TIME] time.
     */
    override fun isExpired(): Boolean {
        val currentTime = System.currentTimeMillis()
        val lastUpdateTime = this.getLastCacheUpdateTimeMillis()
        return currentTime - lastUpdateTime > EXPIRATION_TIME
    }

    /**
     * Get in millis, the last time the cache was accessed.
     */
    private fun getLastCacheUpdateTimeMillis(): Long {
        return preferencesHelper.lastCacheTime
    }

}
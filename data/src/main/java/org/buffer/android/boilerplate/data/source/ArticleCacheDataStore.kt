package org.buffer.android.boilerplate.data.source

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import org.buffer.android.boilerplate.data.model.ArticleEntity
import org.buffer.android.boilerplate.data.repository.ArticleCache
import org.buffer.android.boilerplate.data.repository.ArticleDataStore
import javax.inject.Inject

/**
 * Implementation of the [ArticleDataStore] interface to provide a means of communicating
 * with the local data source
 */
open class ArticleCacheDataStore @Inject constructor(private val articleCache: ArticleCache) :
        ArticleDataStore {

    override suspend fun clearArticles() {
        return articleCache.clearArticles()
    }

    override suspend fun saveArticles(articles: List<ArticleEntity>) {
        withContext(Dispatchers.IO) {
            async { articleCache.saveArticles(articles) }
                    .invokeOnCompletion {
                        articleCache.setLastCacheTime(System.currentTimeMillis())
                    }.dispose()
        }
    }

    override fun getArticles(): Flow<List<ArticleEntity>> {
        return articleCache.getArticles()
    }

    /**
     * Retrieve a list of [ArticleEntity] instance from the cache
     */
    override fun isCached(): Flow<Boolean> {
        return articleCache.isCached()
    }

}
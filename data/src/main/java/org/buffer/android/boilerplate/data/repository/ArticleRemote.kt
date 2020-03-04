package org.buffer.android.boilerplate.data.repository

import kotlinx.coroutines.flow.Flow
import org.buffer.android.boilerplate.data.model.ArticleEntity

/**
 * Interface defining methods for the caching of Articles. This is to be implemented by the
 * cache layer, using this interface as a way of communicating.
 */
interface ArticleRemote {

    /**
     * Retrieve a list of Articles, from the cache
     */
    fun getArticles(): Flow<List<ArticleEntity>>

}
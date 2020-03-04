package org.buffer.android.boilerplate.data.source

import kotlinx.coroutines.flow.Flow
import org.buffer.android.boilerplate.data.model.ArticleEntity
import org.buffer.android.boilerplate.data.repository.ArticleDataStore
import org.buffer.android.boilerplate.data.repository.ArticleRemote
import javax.inject.Inject

/**
 * Implementation of the [ArticleDataStore] interface to provide a means of communicating
 * with the remote data source
 */
open class ArticleRemoteDataStore @Inject constructor(private val articleRemote: ArticleRemote) :
        ArticleDataStore {

    override suspend fun clearArticles() {
        throw UnsupportedOperationException()
    }

    override suspend fun saveArticles(articles: List<ArticleEntity>) {
        throw UnsupportedOperationException()
    }

    override fun getArticles(): Flow<List<ArticleEntity>> {
        return articleRemote.getArticles()
    }

    override fun isCached(): Flow<Boolean> {
        throw UnsupportedOperationException()
    }

}
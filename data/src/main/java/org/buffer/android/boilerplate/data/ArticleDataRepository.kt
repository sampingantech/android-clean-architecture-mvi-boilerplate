package org.buffer.android.boilerplate.data

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import org.buffer.android.boilerplate.data.mapper.ArticleMapper
import org.buffer.android.boilerplate.data.model.ArticleEntity
import org.buffer.android.boilerplate.data.source.ArticleDataStoreFactory
import org.buffer.android.boilerplate.domain.model.Article
import org.buffer.android.boilerplate.domain.repository.ArticleRepository
import javax.inject.Inject

/**
 * Provides an implementation of the [ArticleRepository] interface for communicating to and from
 * data sources
 */
class ArticleDataRepository @Inject constructor(private val factory: ArticleDataStoreFactory,
                                                private val articleMapper: ArticleMapper) :
        ArticleRepository {

    override suspend fun clearArticle() {
        return factory.retrieveCacheDataStore().clearArticles()
    }

    override suspend fun saveArticles(articles: List<Article>) {
        val articlesEntities = mutableListOf<ArticleEntity>()
        articles.map { articlesEntities.add(articleMapper.mapToEntity(it)) }
        return factory.retrieveCacheDataStore().saveArticles(articlesEntities)
    }

    @ExperimentalCoroutinesApi
    override fun getArticles(): Flow<List<Article>> {
        return factory.retrieveCacheDataStore().isCached()
                .map { isCached ->
                    factory.retrieveDataStore(isCached).getArticles()
                }
                .map { flow ->
                    flow.flattenToList().map {
                        articleMapper.mapFromEntity(it)
                    }
                }
                .flatMapConcat { list ->
                    saveArticles(list)
                    flowOf(list)
                }
    }

}

suspend fun <T> Flow<List<T>>.flattenToList() =
        flatMapConcat { it.asFlow() }.toList()
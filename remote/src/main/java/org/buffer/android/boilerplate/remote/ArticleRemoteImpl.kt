package org.buffer.android.boilerplate.remote

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.buffer.android.boilerplate.data.model.ArticleEntity
import org.buffer.android.boilerplate.data.repository.ArticleRemote
import org.buffer.android.boilerplate.remote.mapper.ArticleEntityMapper
import java.lang.reflect.Field
import javax.inject.Inject

/**
 * Remote implementation for retrieving Article instances. This class implements the
 * [ArticleRemote] from the Data layer as it is that layers responsibility for defining the
 * operations in which data store implementation layers can carry out.
 */
class ArticleRemoteImpl @Inject constructor(private val articleService: ArticleService,
                                            private val entityMapper: ArticleEntityMapper) :
        ArticleRemote {
    val apikey: String

    init {
        val klass = Class.forName("org.buffer.android.boilerplate.ui.BuildConfig")
        val field: Field = klass.getDeclaredField("API_KEY")
        apikey = field[null].toString()
    }

    override fun getArticles(): Flow<List<ArticleEntity>> {
        val entities = mutableListOf<ArticleEntity>()
        articleService.getArticles(apikey).articles.forEach {
            entities.add(entityMapper.mapFromRemote(it))
        }
        return flowOf(entities)
    }

}
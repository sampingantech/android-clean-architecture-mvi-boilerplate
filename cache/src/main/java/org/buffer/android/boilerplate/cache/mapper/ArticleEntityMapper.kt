package org.buffer.android.boilerplate.cache.mapper

import org.buffer.android.boilerplate.cache.model.CachedArticle
import org.buffer.android.boilerplate.data.model.ArticleEntity
import javax.inject.Inject

/**
 * Map a [CachedArticle] instance to and from a [ArticleEntity] instance when data is moving between
 * this later and the Data layer
 */
open class ArticleEntityMapper @Inject constructor() :
        EntityMapper<CachedArticle, ArticleEntity> {

    override fun mapToCached(article: ArticleEntity): CachedArticle {
        return CachedArticle(article.source.name,
                article.source.id,
                article.author,
                article.title,
                article.description,
                article.url,
                article.urlToImage,
                article.publishedAt,
                article.content)
    }

    override fun mapFromCached(article: CachedArticle): ArticleEntity {
        return ArticleEntity(ArticleEntity.Source(article.sourceID, article.sourceName), article.author, article.title, article.description, article.url, article.urlToImage, article.publishedAt, article.content)
    }
}

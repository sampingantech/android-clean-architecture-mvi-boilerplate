package org.buffer.android.boilerplate.ui.mapper

import org.buffer.android.boilerplate.presentation.browse.model.ArticleView
import org.buffer.android.boilerplate.presentation.browse.model.BufferooView
import org.buffer.android.boilerplate.ui.model.ArticleViewModel
import org.buffer.android.boilerplate.ui.model.BufferooViewModel
import javax.inject.Inject

/**
 * Map a [ArticleView] to and from a [ArticleViewModel] instance when data is moving between
 * this layer and the Domain layer
 */
open class ArticleMapper @Inject constructor() : Mapper<ArticleViewModel, ArticleView> {

    override fun mapToViewModel(article: ArticleView): ArticleViewModel {
        return ArticleViewModel(
                article.author?:"",
                article.title?:"",
                article.description?:"",
                article.url?:"",
                article.urlToImage?:"",
                article.publishedAt?:"",
                article.content?:"")
    }
}
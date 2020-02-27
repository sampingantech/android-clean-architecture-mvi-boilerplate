package org.buffer.android.boilerplate.cache.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import org.buffer.android.boilerplate.cache.db.constants.ArticleConstants
import org.buffer.android.boilerplate.cache.model.CachedArticle

@Dao
abstract class CachedArticleDao {

    @Query(ArticleConstants.QUERY_ARTICLES)
    abstract fun getArticles(): List<CachedArticle>

    @Query(ArticleConstants.DELETE_ALL_ARTICLES)
    abstract fun clearArticles()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertSArticles(cachedArticle: CachedArticle)

}
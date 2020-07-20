package org.buffer.android.boilerplate.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import org.buffer.android.boilerplate.cache.model.CachedArticle

const val TABLE_NAME = "article"
const val QUERY_ARTICLES = "SELECT * FROM $TABLE_NAME"
const val DELETE_ALL_ARTICLES = "DELETE FROM $TABLE_NAME"

@Dao
abstract class CachedArticleDao {

    @Query(QUERY_ARTICLES)
    abstract fun getArticles(): List<CachedArticle>

    @Query(DELETE_ALL_ARTICLES)
    abstract fun clearArticles()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertSArticles(cachedArticle: CachedArticle)

}
package org.buffer.android.boilerplate.cache.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import org.buffer.android.boilerplate.cache.db.constants.ArticleConstants
import org.buffer.android.boilerplate.cache.db.constants.BufferooConstants

/**
 * Model used solely for the caching of a article
 */
@Entity(tableName = ArticleConstants.TABLE_NAME)
data class CachedArticle(

        val sourceName: String,
        val sourceID: String,
        val author: String,
        val title: String,
        val description: String,
        val url: String,
        val urlToImage: String,
        val publishedAt: String,
        val content: String,
        @PrimaryKey(autoGenerate = true)
        val id: Int? = null
)
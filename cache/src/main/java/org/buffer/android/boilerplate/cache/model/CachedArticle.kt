package org.buffer.android.boilerplate.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.buffer.android.boilerplate.cache.dao.TABLE_NAME

/**
 * Model used solely for the caching of a article
 */
@Entity(tableName = TABLE_NAME)
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
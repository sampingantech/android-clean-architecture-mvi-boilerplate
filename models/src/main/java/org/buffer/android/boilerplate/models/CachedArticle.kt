package org.buffer.android.boilerplate.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "article")
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
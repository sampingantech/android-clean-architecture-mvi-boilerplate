package org.buffer.android.boilerplate.cache.db.constants

/**
 * Defines constants for the Article Table
 */
object ArticleConstants {

    const val TABLE_NAME = "article"

    const val QUERY_ARTICLES = "SELECT * FROM" + " " + TABLE_NAME

    const val DELETE_ALL_ARTICLES = "DELETE FROM" + " " + TABLE_NAME

}
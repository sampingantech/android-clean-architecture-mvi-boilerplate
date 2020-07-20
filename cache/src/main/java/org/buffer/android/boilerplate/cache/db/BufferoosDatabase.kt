package org.buffer.android.boilerplate.cache.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import org.buffer.android.boilerplate.cache.dao.CachedArticleDao
import org.buffer.android.boilerplate.cache.dao.CachedBufferooDao
import org.buffer.android.boilerplate.models.CachedArticle
import org.buffer.android.boilerplate.cache.model.CachedBufferoo
import javax.inject.Inject

@Database(entities = arrayOf(CachedBufferoo::class, CachedArticle::class), version = 1)
abstract class BufferoosDatabase @Inject constructor() : RoomDatabase() {

    abstract fun cachedBufferooDao(): CachedBufferooDao
    abstract fun cachedArticleDao(): CachedArticleDao

    private var INSTANCE: BufferoosDatabase? = null

    private val sLock = Any()

    fun getInstance(context: Context): BufferoosDatabase {
        if (INSTANCE == null) {
            synchronized(sLock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            BufferoosDatabase::class.java, "bufferoos_new.db")
                            .build()
                }
                return INSTANCE!!
            }
        }
        return INSTANCE!!
    }

}
package com.harunkor.movieflick.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(BookmarkRecord::class), version = 1)
abstract class BookmarkDatabase: RoomDatabase() {
    abstract fun bookmarkDao(): BookmarkMovieDao
    companion object {
        private var INSTANCE: BookmarkDatabase? = null
        fun getDatabase(context: Context): BookmarkDatabase? {
            if (INSTANCE == null) {
                synchronized(BookmarkDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.getApplicationContext(),
                        BookmarkDatabase::class.java, "BookmarksDatabase.db"
                    ).allowMainThreadQueries().build()
                }
            }
            return INSTANCE
        }
    }
}
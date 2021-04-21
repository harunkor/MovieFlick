package com.harunkor.movieflick.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BookmarkMovieDao {

    @Insert
    fun insert(bookmarkRecord: BookmarkRecord)

    @Delete
    fun delete(bookmarkRecord: BookmarkRecord)

    @Query("SELECT * from BookmarkRecord")
    fun getAllRows(): List<BookmarkRecord>


}
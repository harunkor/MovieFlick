package com.harunkor.movieflick.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "BookmarkRecord")
class BookmarkRecord (

    @PrimaryKey(autoGenerate = true)
    val key: Int? = null,
    @ColumnInfo(name = "poster_path") val poster_path: String,
    @ColumnInfo(name = "adult") val adult: String,
    @ColumnInfo(name = "overview") val overview: String,
    @ColumnInfo(name = "release_date") val release_date: String,
    @ColumnInfo(name = "genre_ids") val genre_ids: String,
    @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "original_title") val original_title: String,
    @ColumnInfo(name = "original_language") val original_language: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "backdrop_path") val backdrop_path: String,
    @ColumnInfo(name = "popularity") val popularity: String,
    @ColumnInfo(name = "vote_count") val vote_count: String,
    @ColumnInfo(name = "video") val video: String,
    @ColumnInfo(name = "vote_average") val vote_average: String,


        )




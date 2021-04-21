package com.harunkor.movieflick.model

import android.R
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.squareup.picasso.Picasso



class  MoviesModel {

    // Movies default model created.

    @SerializedName("poster_path")
    @Expose
    var poster_path: String? =null
        get() = field
        set(value) {
            field = value
        }

    @SerializedName("adult")
    @Expose
    var adult: Boolean? = null
        get() = field
        set(value) {
            field = value
        }


    @SerializedName("overview")
    @Expose
    var overview: String? = null
        get() = field
        set(value) {
            field = value
        }


    @SerializedName("release_date")
    @Expose
    var release_date: String? = null
        get() = field
        set(value) {
            field = value
        }

    @SerializedName("genre_ids")
    @Expose
    var genre_ids: Array<Integer>?  = null
        get() = field
        set(value) {
            field = value
        }

    @SerializedName("id")
    @Expose
    var id: Int? = null
        get() = field
        set(value) {
            field = value
        }

    @SerializedName("original_title")
    @Expose
    var original_title: String? = null
        get() = field
        set(value) {
            field = value
        }


    @SerializedName("original_language")
    @Expose
    var original_language: String? = null
        get() = field
        set(value) {
            field = value
        }

    @SerializedName("title")
    @Expose
    var title: String? = null
        get() = field
        set(value) {
            field = value
        }

    @SerializedName("backdrop_path")
    @Expose
    var backdrop_path: String? = null
        get() = field
        set(value) {
            field = value
        }

    @SerializedName("popularity")
    @Expose
    var popularity: String? = null
        get() = field
        set(value) {
            field = value
        }

    @SerializedName("vote_count")
    @Expose
    var vote_count: String? = null
        get() = field
        set(value) {
            field = value
        }

    @SerializedName("video")
    @Expose
    var video: Boolean? = null
        get() = field
        set(value) {
            field = value
        }


    @SerializedName("vote_average")
    @Expose
    var vote_average: String? = null
        get() = field
        set(value) {
            field = value
        }


    companion object {
        @BindingAdapter("imgurl")
        @JvmStatic
        fun  loadImage(view: ImageView, imageUrl: String?) {
            val url = "https://image.tmdb.org/t/p/w185/"
            if(imageUrl!=null) Picasso.get().load(url + imageUrl!!).
            error(com.harunkor.movieflick.R.drawable.none_poster).into(view)

        }

    }

















}
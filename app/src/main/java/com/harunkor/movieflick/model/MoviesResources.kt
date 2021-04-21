package com.harunkor.movieflick.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class MoviesResources {

    // List Movies Model assigned.
    @SerializedName("results")
    @Expose
    private var moviesModelList: List<MoviesModel>? = null

    fun getMoviesList(): List<MoviesModel> {
        return moviesModelList!!
    }

    fun setMoviesList(moviesModelList: List<MoviesModel>) {
        this.moviesModelList = moviesModelList
    }



}
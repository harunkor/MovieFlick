package com.harunkor.movieflick.utils

import com.harunkor.movieflick.model.MoviesResources
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiRetroInterface   {

    @GET("/3/{dynamicType}/{dynamicUrl}")
    fun MoviesAll(
        @Path(value = "dynamicType") dynamicType : String,
        @Path(value = "dynamicUrl") dynamicUrl : String,
        @Query("api_key") api_key: String?,
        @Query("language") language: String?,
        @Query("page") page: String?
    ): Call<MoviesResources?>?




    @GET("/3/{dynamicType}/{dynamicUrl}")
    fun SearchMovies(
        @Path(value = "dynamicType") dynamicType : String,
        @Path(value = "dynamicUrl") dynamicUrl : String,
        @Query("api_key") api_key: String?,
        @Query("query") query: String?,
        @Query("language") language: String?,
        @Query("page") page: String?
    ): Call<MoviesResources?>?


    @GET("/3/{dynamicType}/{dynamicUrl}/recommendations")
    fun Recommendations(
            @Path(value = "dynamicType") dynamicType : String,
            @Path(value = "dynamicUrl") dynamicUrl : String,
            @Query("api_key") api_key: String?,
            @Query("language") language: String?,
            @Query("page") page: String?
    ): Call<MoviesResources?>?




}
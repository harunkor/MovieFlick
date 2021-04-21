package com.harunkor.movieflick.utils

import android.os.StrictMode
import android.os.StrictMode.VmPolicy
import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiLoginClient {

    private var retrofit: Retrofit? = null


    fun ClientLogin(): Retrofit? {
        val builder = VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        val logger = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
            Log.d("API", it)
        })
        logger.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(logger).build()
        retrofit = Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        return retrofit


    }


}
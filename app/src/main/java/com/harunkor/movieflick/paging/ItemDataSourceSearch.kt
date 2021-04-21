package com.harunkor.movieflick.paging

import androidx.paging.PageKeyedDataSource
import com.harunkor.movieflick.model.MoviesModel
import com.harunkor.movieflick.model.MoviesResources
import com.harunkor.movieflick.utils.ApiLoginClient
import com.harunkor.movieflick.utils.ApiRetroInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ItemDataSourceSearch(
     val dynamicType: String,
    val dynamicUrl: String,
    val apikey: String,
    val query: String,
    val language: String,
    val page: String
) : PageKeyedDataSource<Integer, MoviesModel>() {




    private val apiLoginClient:ApiLoginClient= ApiLoginClient();

    private val apiRetroInterface: ApiRetroInterface = apiLoginClient.ClientLogin()!!.create(
        ApiRetroInterface::class.java
    )





    override fun loadInitial(
        params: LoadInitialParams<Integer>,
        callback: LoadInitialCallback<Integer, MoviesModel>
    ) {


        var call = apiRetroInterface.SearchMovies(dynamicType,dynamicUrl, apikey,query, language, page)
        call!!.enqueue(object : Callback<MoviesResources?> {
            override fun onResponse(
                call: Call<MoviesResources?>,
                response: Response<MoviesResources?>
            ) {

                var nextpage: Integer = Integer(page.toInt() + 1);
                callback.onResult(response.body()!!.getMoviesList()!!, null, nextpage)

            }

            override fun onFailure(call: Call<MoviesResources?>, t: Throwable) {

            }

        })





    }



    override fun loadBefore(
        params: LoadParams<Integer>,
        callback: LoadCallback<Integer, MoviesModel>
    )
    {


        var call=apiRetroInterface.SearchMovies(dynamicType,dynamicUrl, apikey,query, language, page)
        call!!.enqueue(object : Callback<MoviesResources?> {

            override fun onResponse(
                call: Call<MoviesResources?>,
                response: Response<MoviesResources?>
            ) {


                var key: Integer? =
                    if (params.key.toInt() > 1) Integer(params.key.toInt() - 1) else null
                callback.onResult(response.body()!!.getMoviesList()!!, key);


            }


            override fun onFailure(call: Call<MoviesResources?>, t: Throwable) {
                TODO("Not yet implemented")
            }


        })





    }


    override fun loadAfter(
        params: LoadParams<Integer>,
        callback: LoadCallback<Integer, MoviesModel>
    )
    {


        var call=apiRetroInterface.SearchMovies(dynamicType,dynamicUrl, apikey,query, language, page)
        call!!.enqueue(object : Callback<MoviesResources?> {

            override fun onResponse(
                call: Call<MoviesResources?>,
                response: Response<MoviesResources?>
            ) {


                var key: Integer? = Integer(params.key.toInt() + 1);
                callback.onResult(response.body()!!.getMoviesList()!!, key);


            }


            override fun onFailure(call: Call<MoviesResources?>, t: Throwable) {
                //TODO("Not yet implemented")
            }


        })




    }




}
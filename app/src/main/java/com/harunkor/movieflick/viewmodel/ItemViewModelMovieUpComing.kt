package com.harunkor.movieflick.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.harunkor.movieflick.model.MoviesModel
import com.harunkor.movieflick.paging.ItemDataSourceFactory



class ItemViewModelMovieUpComing  constructor() : ViewModel() {


    var itemPagedList: LiveData<PagedList<MoviesModel>>
    var liveDataSource: LiveData<PageKeyedDataSource<Integer, MoviesModel>>

    var dynamicType:String="movie"
    var dynamicUrl:String="upcoming"
    var apikey: String="5c14977999b58573a8b099dd535033b7"
    var language: String = "en-US"
    var page:String="1"


    init {

        var itemDataSourceFactory = ItemDataSourceFactory(dynamicType,
            dynamicUrl,
            apikey,
            language,
            page
        )
        liveDataSource = itemDataSourceFactory.getItemLiveDataSource()
        var config = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(50)  // burada sayı
                .build()

        var livepaged= LivePagedListBuilder(itemDataSourceFactory, config)
        itemPagedList =  livepaged.build()

    }




}



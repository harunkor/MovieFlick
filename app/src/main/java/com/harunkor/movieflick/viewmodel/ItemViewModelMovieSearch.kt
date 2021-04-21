package com.harunkor.movieflick.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.harunkor.movieflick.model.MoviesModel
import com.harunkor.movieflick.paging.ItemDataSourceFactory
import com.harunkor.movieflick.paging.ItemDataSourceFactorySearch


class ItemViewModelMovieSearch(query: String) : ViewModel()
  {


    var itemPagedList: LiveData<PagedList<MoviesModel>>
    var liveDataSource: LiveData<PageKeyedDataSource<Integer, MoviesModel>>

    var dynamicType:String="search"
    var dynamicUrl:String="multi"
    var apikey: String="5c14977999b58573a8b099dd535033b7"
    var language: String = "en-US"
    var page:String="1"







    init {


        var itemDataSourceFactorySearch = ItemDataSourceFactorySearch(
            dynamicType,
            dynamicUrl,
            apikey,
            query,
            language,
            page
        )
        liveDataSource = itemDataSourceFactorySearch.getItemLiveDataSource()
        var config = PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setPageSize(50)  // burada sayı
                .build()

        var livepaged= LivePagedListBuilder(itemDataSourceFactorySearch, config)
        itemPagedList =  livepaged.build()



    }








  }



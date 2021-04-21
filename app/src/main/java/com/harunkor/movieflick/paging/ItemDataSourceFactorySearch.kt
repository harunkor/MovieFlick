package com.harunkor.movieflick.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.harunkor.movieflick.model.MoviesModel


class ItemDataSourceFactorySearch(val dynamicType:String, val dynamicUrl: String, val apikey: String,val query:String, val language: String, val page: String) : DataSource.Factory<Integer, MoviesModel>() {






    private var itemLiveDataSource:MutableLiveData<PageKeyedDataSource<Integer, MoviesModel>> = MutableLiveData()


    override fun create(): DataSource<Integer, MoviesModel> {
        val itemDataSourceSearch = ItemDataSourceSearch(dynamicType,dynamicUrl, apikey,query, language, page)
        itemLiveDataSource.postValue(itemDataSourceSearch);
        return itemDataSourceSearch;

    }



    open fun getItemLiveDataSource(): MutableLiveData<PageKeyedDataSource<Integer, MoviesModel>> {
        return itemLiveDataSource
    }


}
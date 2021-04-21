package com.harunkor.movieflick.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.harunkor.movieflick.model.MoviesModel


class ItemDataSourceFactoryRecommendations(val dynamicType:String, val dynamicUrl: String, val apikey: String, val language: String, val page: String) : DataSource.Factory<Integer, MoviesModel>() {






    private var itemLiveDataSource:MutableLiveData<PageKeyedDataSource<Integer, MoviesModel>> = MutableLiveData()


    override fun create(): DataSource<Integer, MoviesModel> {
        val itemDataSource = ItemDataSourceRecommendations(dynamicType,dynamicUrl, apikey, language, page)
        itemLiveDataSource.postValue(itemDataSource);
        return itemDataSource;

    }



    open fun getItemLiveDataSource(): MutableLiveData<PageKeyedDataSource<Integer, MoviesModel>> {
        return itemLiveDataSource
    }


}
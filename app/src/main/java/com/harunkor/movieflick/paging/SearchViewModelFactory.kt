package com.harunkor.movieflick.paging

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.harunkor.movieflick.viewmodel.ItemViewModelMovieSearch

class SearchViewModelFactory(query:String) : ViewModelProvider.NewInstanceFactory() {

     var query: String? =query



    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
         return   ItemViewModelMovieSearch(query.toString()) as T;
    }
}
package com.harunkor.movieflick.paging

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.harunkor.movieflick.viewmodel.ItemViewModelMovieRecommendations
import com.harunkor.movieflick.viewmodel.ItemViewModelMovieSearch

class RecommendationsModelFactory(movieID:String) : ViewModelProvider.NewInstanceFactory() {

     var movieID: String? =movieID



    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
         return   ItemViewModelMovieRecommendations(movieID.toString()) as T;
    }
}
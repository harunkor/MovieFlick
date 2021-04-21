package com.harunkor.movieflick.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.harunkor.movieflick.R
import com.harunkor.movieflick.adapter.RecyclerViewAdapter
import com.harunkor.movieflick.databinding.FragmentMovieDetailBinding
import com.harunkor.movieflick.model.MoviesModel
import com.harunkor.movieflick.paging.RecommendationsModelFactory
import com.harunkor.movieflick.paging.SearchViewModelFactory
import com.harunkor.movieflick.viewmodel.ItemViewModelMovieRecommendations
import com.harunkor.movieflick.viewmodel.ItemViewModelMovieSearch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "movie"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MovieDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MovieDetailFragment : Fragment(), RecyclerViewAdapter.ClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var MovieDetailBinding:FragmentMovieDetailBinding
    lateinit var moviesModel: MoviesModel
    var AdapterMoviesRecom: RecyclerViewAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        moviesModel=movie()

        MovieDetailBinding= DataBindingUtil.inflate(inflater, R.layout.fragment_movie_detail, container, false)

        MovieDetailBinding.setVariable(BR.dataMovies, moviesModel)

        var recommendationsModelFactory: RecommendationsModelFactory= RecommendationsModelFactory(moviesModel.id.toString())

        var itemViewModelMovieRecommendations: ItemViewModelMovieRecommendations

        AdapterMoviesRecom = RecyclerViewAdapter(this)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        MovieDetailBinding.idrecomlist.setLayoutManager(layoutManager)
        MovieDetailBinding.idrecomlist.setHasFixedSize(false)


        itemViewModelMovieRecommendations= ViewModelProvider(this, recommendationsModelFactory).get(
                ItemViewModelMovieRecommendations::class.java
        )

        itemViewModelMovieRecommendations.itemPagedList.observe(viewLifecycleOwner,
                Observer<PagedList<MoviesModel>> { items ->

                    AdapterMoviesRecom!!.submitList(items)



                })


        MovieDetailBinding.setMoviesRecomAdapter(AdapterMoviesRecom)


        return MovieDetailBinding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MovieDetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MovieDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


    fun movie():MoviesModel
    {
        var gson=Gson()
        var mov: MoviesModel? = gson.fromJson(param1,MoviesModel::class.java)


        return mov!!
    }

    override fun launchIntent(moviesModel: MoviesModel?) {
        val mov= Gson().toJson(moviesModel)
        val bundle = Bundle()
        bundle.putString("movie", mov.toString())
        val nav = NavHostFragment.findNavController(this);
        nav.navigate(R.id.action_movieDetailFragment_self, bundle)
    }
}
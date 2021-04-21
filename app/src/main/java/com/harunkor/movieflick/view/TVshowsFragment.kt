package com.harunkor.movieflick.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.harunkor.movieflick.R
import com.harunkor.movieflick.adapter.RecyclerViewAdapter
import com.harunkor.movieflick.databinding.FragmentTvshowsBinding
import com.harunkor.movieflick.model.MoviesModel
import com.harunkor.movieflick.viewmodel.*
import com.squareup.picasso.Picasso


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TVshowsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TVshowsFragment : Fragment(), RecyclerViewAdapter.ClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var TvShowsBinding:FragmentTvshowsBinding

    lateinit var AdapterTvShowsCurrently: RecyclerViewAdapter;
    lateinit var itemViewModelTvShowsCurrently: ItemViewModelTVShowsCurrently


    lateinit var AdapterTvShowsPopuler: RecyclerViewAdapter;
    lateinit var itemViewModelTvShowsPopuler: ItemViewModelTVShowsPopuler


    lateinit var AdapterTvShowsTopRated: RecyclerViewAdapter;
    lateinit var itemViewModelTvShowsTopRated: ItemViewModelTVShowsTopRated



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
        TvShowsBinding= DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_tvshows,
            container,
            false
        )

        itemViewModelTvShowsCurrently = ViewModelProvider.NewInstanceFactory().create(
            ItemViewModelTVShowsCurrently()::class.java
        )
        itemViewModelTvShowsPopuler= ViewModelProvider.NewInstanceFactory().create(
            ItemViewModelTVShowsPopuler()::class.java
        )
        itemViewModelTvShowsTopRated= ViewModelProvider.NewInstanceFactory().create(
            ItemViewModelTVShowsTopRated()::class.java
        )

        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        TvShowsBinding.idCurrentlyMoviesList.setLayoutManager(layoutManager);
        TvShowsBinding.idCurrentlyMoviesList.setHasFixedSize(false);
        TvShowsBinding.idPopulerMoviesList.setHasFixedSize(false); // buraya bak sonra
        TvShowsBinding.idTopratedMoviesList.setHasFixedSize(false)

        AdapterTvShowsCurrently = RecyclerViewAdapter(this)
        AdapterTvShowsPopuler=RecyclerViewAdapter(this)
        AdapterTvShowsTopRated=RecyclerViewAdapter(this)


        val url = "https://image.tmdb.org/t/p/w400/"+"/6kbAMLteGO8yyewYau6bJ683sw7.jpg"
        Picasso.get().load(url).error(com.harunkor.movieflick.R.drawable.none_poster).into(
            TvShowsBinding.idMovieImage
        )

        itemViewModelTvShowsCurrently.itemPagedList.observe(viewLifecycleOwner,
            Observer<PagedList<MoviesModel>> { items ->
                AdapterTvShowsCurrently.submitList(items)

            })


        itemViewModelTvShowsCurrently.itemPagedList.observe(
            viewLifecycleOwner,
            Observer<PagedList<MoviesModel>> { items ->
                AdapterTvShowsPopuler.submitList(
                    items
                )

            })


        itemViewModelTvShowsTopRated.itemPagedList.observe(
            viewLifecycleOwner,
            Observer<PagedList<MoviesModel>> { items ->
                AdapterTvShowsTopRated.submitList(
                    items
                )

            })

        TvShowsBinding.setTvShowsCurrentlyAdapter(AdapterTvShowsCurrently)

        TvShowsBinding.setTvShowsPopulerAdapter(AdapterTvShowsPopuler)

        TvShowsBinding.setTvShowsTopRatedAdapter(AdapterTvShowsTopRated)



        // Inflate the layout for this fragment
        return  TvShowsBinding.getRoot()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TVshowsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TVshowsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }




    override fun launchIntent(moviesModel: MoviesModel?) {
        val mov= Gson().toJson(moviesModel)
        val bundle = Bundle()
        bundle.putString("movie", mov.toString())
        val nav = NavHostFragment.findNavController(this);
        nav.navigate(R.id.action_TVshowsFragment_to_movieDetailFragment,bundle)

    }



}
package com.harunkor.movieflick.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.harunkor.movieflick.BR
import com.harunkor.movieflick.R
import com.harunkor.movieflick.adapter.RecyclerViewAdapter
import com.harunkor.movieflick.databinding.FragmentMoviesBinding
import com.harunkor.movieflick.model.MoviesModel
import com.harunkor.movieflick.viewmodel.ItemViewModelMovieCurrently
import com.harunkor.movieflick.viewmodel.ItemViewModelMoviePopuler
import com.harunkor.movieflick.viewmodel.ItemViewModelMovieTopRated
import com.harunkor.movieflick.viewmodel.ItemViewModelMovieUpComing
import com.squareup.picasso.Picasso


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MoviesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MoviesFragment : Fragment(), RecyclerViewAdapter.ClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var moviesBinding: FragmentMoviesBinding


    lateinit var AdapterMoviesCurrently:RecyclerViewAdapter;
    lateinit var itemViewModelMovieCurrently: ItemViewModelMovieCurrently


    lateinit var AdapterMoviesPopuler:RecyclerViewAdapter;
    lateinit var itemViewModelMoviePopuler: ItemViewModelMoviePopuler


    lateinit var AdapterMoviesTopRated:RecyclerViewAdapter;
    lateinit var itemViewModelMovieTopRated: ItemViewModelMovieTopRated

    lateinit var AdapterMoviesUpComing:RecyclerViewAdapter;
    lateinit var itemViewModelMovieUpComing: ItemViewModelMovieUpComing


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        moviesBinding=DataBindingUtil.inflate(inflater, R.layout.fragment_movies, container, false)


        itemViewModelMovieCurrently = ViewModelProvider.NewInstanceFactory().create(ItemViewModelMovieCurrently()::class.java)
        itemViewModelMoviePopuler= ViewModelProvider.NewInstanceFactory().create(ItemViewModelMoviePopuler()::class.java)
        itemViewModelMovieTopRated= ViewModelProvider.NewInstanceFactory().create(ItemViewModelMovieTopRated()::class.java)
        itemViewModelMovieUpComing= ViewModelProvider.NewInstanceFactory().create(ItemViewModelMovieUpComing()::class.java)




        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        


        moviesBinding.idCurrentlyMoviesList.setLayoutManager(layoutManager);
        moviesBinding.idCurrentlyMoviesList.setHasFixedSize(false);
        moviesBinding.idPopulerMoviesList.setHasFixedSize(false); // buraya bak sonra
        moviesBinding.idTopratedMoviesList.setHasFixedSize(false)
        moviesBinding.idUpcomingMoviesList.setHasFixedSize(false)



        AdapterMoviesCurrently = RecyclerViewAdapter(this)
        AdapterMoviesPopuler=RecyclerViewAdapter(this)
        AdapterMoviesTopRated=RecyclerViewAdapter(this)
        AdapterMoviesUpComing=RecyclerViewAdapter(this)


        val url = "https://image.tmdb.org/t/p/w400/"+"/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg"
        Picasso.get().load(url).error(com.harunkor.movieflick.R.drawable.none_poster).into(moviesBinding.idMovieImage)

        itemViewModelMovieCurrently.itemPagedList.observe(viewLifecycleOwner,
            Observer<PagedList<MoviesModel>> { items ->
                AdapterMoviesCurrently.submitList(items)


            })


        itemViewModelMoviePopuler.itemPagedList.observe(viewLifecycleOwner, Observer<PagedList<MoviesModel>> {
            items ->  AdapterMoviesPopuler.submitList(items)

        })


        itemViewModelMovieTopRated.itemPagedList.observe(viewLifecycleOwner, Observer<PagedList<MoviesModel>> {
            items -> AdapterMoviesTopRated.submitList(items)

        })


        itemViewModelMovieUpComing.itemPagedList.observe(viewLifecycleOwner, Observer<PagedList<MoviesModel>> {
                items -> AdapterMoviesUpComing.submitList(items)

        })



         moviesBinding.setMoviesCurrentlyAdapter(AdapterMoviesCurrently)

         moviesBinding.setMoviesPopulerAdapter(AdapterMoviesPopuler)

         moviesBinding.setMoviesTopRatedAdapter(AdapterMoviesTopRated)

         moviesBinding.setMoviesUpComingAdapter(AdapterMoviesUpComing)








        // Inflate the layout for this fragment
        return  moviesBinding.getRoot()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MoviesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MoviesFragment().apply {
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
        nav.navigate(R.id.action_moviesFragment_to_movieDetailFragment,bundle)
    }



}
package com.harunkor.movieflick.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.Gson
import com.harunkor.movieflick.R
import com.harunkor.movieflick.adapter.RecyclerViewAdapter
import com.harunkor.movieflick.databinding.FragmentSearchBinding
import com.harunkor.movieflick.model.MoviesModel
import com.harunkor.movieflick.paging.SearchViewModelFactory
import com.harunkor.movieflick.viewmodel.ItemViewModelMovieSearch


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment : Fragment(), SearchView.OnQueryTextListener,
    RecyclerViewAdapter.ClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var searchMovieBinding:FragmentSearchBinding
    var AdapterMoviesSearch: RecyclerViewAdapter?=null



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
        searchMovieBinding= DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_search,
            container,
            false
        )
        searchMovieBinding.idSearchview.setIconified(false)
        searchMovieBinding.idSearchview.setOnQueryTextListener(this)


        val layoutManager = GridLayoutManager(context, 3)

        searchMovieBinding.idSearchList.setLayoutManager(layoutManager);
        searchMovieBinding.idSearchList.setHasFixedSize(false);

        AdapterMoviesSearch = RecyclerViewAdapter(this)



        // Inflate the layout for this fragment
        return  searchMovieBinding.getRoot()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SearchFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {


        return false

    }

    override fun onQueryTextChange(p0: String?): Boolean {
        search(p0!!.trim().toString())
        return false
    }


    override fun launchIntent(moviesModel: MoviesModel?) {
        val mov= Gson().toJson(moviesModel)
        val bundle = Bundle()
        bundle.putString("movie", mov.toString())
        val nav = NavHostFragment.findNavController(this);
        nav.navigate(R.id.action_searchFragment_to_movieDetailFragment, bundle)
    }


    fun search(query: String)
    {

        if(AdapterMoviesSearch!!.itemCount>0)
        AdapterMoviesSearch!!.submitList(null)


        var searchViewModelFactory: SearchViewModelFactory = SearchViewModelFactory(query)

        var itemViewModelMovieSearchView: ItemViewModelMovieSearch

        itemViewModelMovieSearchView= ViewModelProvider(this, searchViewModelFactory).get(
            ItemViewModelMovieSearch::class.java
        )

        itemViewModelMovieSearchView.itemPagedList.observe(viewLifecycleOwner,
            Observer<PagedList<MoviesModel>> { items ->

                AdapterMoviesSearch!!.submitList(items)
                searchMovieBinding.setMoviesSearchAdapter(AdapterMoviesSearch)
                searchMovieBinding.idEmptySearch.setVisibility(View.INVISIBLE)

            })




    }

}
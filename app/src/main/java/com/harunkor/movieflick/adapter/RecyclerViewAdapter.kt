package com.harunkor.movieflick.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.harunkor.movieflick.model.MoviesModel


open class RecyclerViewAdapter(var clickListener: ClickListener) : PagedListAdapter<MoviesModel, RecyclerViewAdapter.ViewHolder>(DIFF_CALLBACK)

{


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        var binding = DataBindingUtil.inflate<ViewDataBinding>(
                LayoutInflater.from(parent.context),
                com.harunkor.movieflick.R.layout.recycler_movie_row, parent, false
        )

        return  ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position));

        holder.itemView.setOnClickListener(View.OnClickListener { clickListener.launchIntent(getItem(position)) })

    }



    class ViewHolder(var itemRowBinding: ViewDataBinding) : RecyclerView.ViewHolder(itemRowBinding.root) {
        fun bind(obj: Any?) {
            itemRowBinding.setVariable(BR.dataMovies, obj)
            itemRowBinding.executePendingBindings()
        }
    }



    object DIFF_CALLBACK : DiffUtil.ItemCallback<MoviesModel>() {
        override fun areItemsTheSame(oldItem: MoviesModel, newItem: MoviesModel): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: MoviesModel, newItem: MoviesModel): Boolean {
            return oldItem.equals(newItem)
        }
    }


    interface ClickListener {
        fun launchIntent(moviesModel: MoviesModel?)
    }






}
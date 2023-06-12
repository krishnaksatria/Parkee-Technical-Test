package com.id.parkee.features.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.id.parkee.commons.extensions.loadImage
import com.id.parkee.core.movie.data.MovieDetailModel
import com.id.parkee.features.databinding.ItemMovieBinding

class MovieAdapter :
    ListAdapter<MovieDetailModel, MovieAdapter.MovieViewHolder>(COMPARATOR) {

    private var movieClickListener: ((MovieAdapter, MovieDetailModel) -> Unit)? =
        null

    fun setOnMovieClickListener(listener: (MovieAdapter, MovieDetailModel) -> Unit) {
        movieClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return MovieViewHolder(binding) { _, position ->
            movieClickListener?.invoke(this, getItem(position))
        }
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindView(getItem(position))
    }

    inner class MovieViewHolder(
        private val itemBinding: ItemMovieBinding,
        listener: (MovieViewHolder, Int) -> Unit
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        init {
            itemView.setOnClickListener {
                listener.invoke(this, absoluteAdapterPosition)
            }
        }

        fun bindView(item: MovieDetailModel) {
            itemBinding.imageMovie.loadImage(item.posterPath)
            itemBinding.textMovieTitle.text = item.title
            itemBinding.textMovieDescription.text = item.overview
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<MovieDetailModel>() {
            override fun areItemsTheSame(
                oldItem: MovieDetailModel,
                newItem: MovieDetailModel
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: MovieDetailModel,
                newItem: MovieDetailModel
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}

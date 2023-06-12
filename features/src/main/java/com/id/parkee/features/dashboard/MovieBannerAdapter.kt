package com.id.parkee.features.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.id.parkee.commons.extensions.loadImage
import com.id.parkee.core.movie.data.MovieDetailModel
import com.id.parkee.features.databinding.ItemMovieBannerBinding

class MovieBannerAdapter :
    ListAdapter<MovieDetailModel, MovieBannerAdapter.MovieBannerViewHolder>(COMPARATOR) {

    private var movieClickListener: ((MovieBannerAdapter, MovieDetailModel) -> Unit)? =
        null

    fun setOnMovieClickListener(listener: (MovieBannerAdapter, MovieDetailModel) -> Unit) {
        movieClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieBannerViewHolder {
        val binding = ItemMovieBannerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return MovieBannerViewHolder(binding) { _, position ->
            movieClickListener?.invoke(this, getItem(position))
        }
    }

    override fun onBindViewHolder(holder: MovieBannerViewHolder, position: Int) {
        holder.bindView(getItem(position))
    }

    inner class MovieBannerViewHolder(
        private val itemBinding: ItemMovieBannerBinding,
        listener: (MovieBannerViewHolder, Int) -> Unit
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        init {
            itemView.setOnClickListener {
                listener.invoke(this, absoluteAdapterPosition)
            }
        }

        fun bindView(item: MovieDetailModel) {
            itemBinding.imageMovieBanner.loadImage(item.posterPath)
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

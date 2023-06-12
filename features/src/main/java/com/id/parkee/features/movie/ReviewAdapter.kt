package com.id.parkee.features.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.id.parkee.core.movie.data.reviews.MovieReviewModel
import com.id.parkee.features.databinding.ItemReviewBinding

class ReviewAdapter :
    ListAdapter<MovieReviewModel, ReviewAdapter.ReviewViewHolder>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val binding = ItemReviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ReviewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.bindView(getItem(position))
    }

    inner class ReviewViewHolder(
        private val itemBinding: ItemReviewBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bindView(item: MovieReviewModel) {
            itemBinding.textReview.text = item.content
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<MovieReviewModel>() {
            override fun areItemsTheSame(
                oldItem: MovieReviewModel,
                newItem: MovieReviewModel
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: MovieReviewModel,
                newItem: MovieReviewModel
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}

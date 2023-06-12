package com.id.parkee.features.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.id.parkee.features.databinding.ItemFavoriteBinding
import com.id.parkee.room.data.FavoriteMovieData

class FavoriteAdapter :
    ListAdapter<FavoriteMovieData, FavoriteAdapter.FavoriteViewHolder>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding = ItemFavoriteBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return FavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bindView(getItem(position))
    }

    inner class FavoriteViewHolder(
        private val itemBinding: ItemFavoriteBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bindView(item: FavoriteMovieData) {
            itemBinding.textMovieTitle.text = item.title
            itemBinding.textMovieDescription.text = item.description
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<FavoriteMovieData>() {
            override fun areItemsTheSame(
                oldItem: FavoriteMovieData,
                newItem: FavoriteMovieData
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: FavoriteMovieData,
                newItem: FavoriteMovieData
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}

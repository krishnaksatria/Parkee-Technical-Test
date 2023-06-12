package com.id.parkee.features.favorite

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.id.parkee.commons.adapter.itemdecoration.ItemDividerVertical
import com.id.parkee.commons.ui.dialog.BottomSheetDialog
import com.id.parkee.features.R
import com.id.parkee.features.databinding.ActivityFavoriteBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.scope.ScopeActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteActivity : ScopeActivity() {

    private lateinit var binding: ActivityFavoriteBinding

    private val favoriteViewModel: FavoriteViewModel by viewModel()

    private val favoriteAdapter: FavoriteAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initLayout()
        initObserver()

        favoriteViewModel.getFavoriteMovieList()
    }

    private fun initLayout() {
        binding.recyclerFavorite.apply {
            adapter = favoriteAdapter
            layoutManager = LinearLayoutManager(
                this@FavoriteActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
            addItemDecoration(ItemDividerVertical(resources.getDimensionPixelSize(R.dimen.dimens_10dp)))
        }
    }

    private fun initObserver() {
        favoriteViewModel.getFavoriteMovieListLiveData.observe(this) {
            favoriteAdapter.submitList(it)
        }
    }

    private fun showBottomSheet() {
        val bottomSheet = BottomSheetDialog(this)
        bottomSheet.show()
    }

    companion object {
        internal const val INSERT_DATA_FAILED = -1L
        internal const val CHANGE_DATA_SUCCESS = 1
        const val MOVIE_MODEL_ARGS = "movie_model_args"
    }
}

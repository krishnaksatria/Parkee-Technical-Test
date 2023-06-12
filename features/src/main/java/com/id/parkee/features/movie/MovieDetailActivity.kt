package com.id.parkee.features.movie

import android.os.Build
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.parkee.android.libraries.extension.setGone
import com.parkee.android.libraries.extension.setVisible
import com.id.parkee.commons.adapter.itemdecoration.ItemDividerVertical
import com.id.parkee.commons.extensions.loadImage
import com.id.parkee.commons.extensions.showError
import com.id.parkee.commons.extensions.showToast
import com.id.parkee.commons.ui.dialog.BottomSheetDialog
import com.id.parkee.commons.ui.viewmodel.ViewStateModel
import com.id.parkee.core.movie.data.MovieDetailModel
import com.id.parkee.features.R
import com.id.parkee.features.databinding.ActivityMovieDetailBinding
import com.id.parkee.room.data.FavoriteMovieData
import org.koin.android.ext.android.inject
import org.koin.androidx.scope.ScopeActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailActivity : ScopeActivity() {

    private lateinit var binding: ActivityMovieDetailBinding

    private val movieDetailViewModel: MovieDetailViewModel by viewModel()

    private val reviewAdapter: ReviewAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movie: MovieDetailModel? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(MOVIE_MODEL_ARGS, MovieDetailModel::class.java)
        } else {
            intent.getParcelableExtra(MOVIE_MODEL_ARGS)
        }

        if (movie != null) {
            movieDetailViewModel.movie = movie
        } else {
            finish()
            return
        }

        initLayout()
        initEvent()
        initObserver()

        movieDetailViewModel.getMovieReviewList()
    }

    private fun initLayout() {
        val movie = movieDetailViewModel.movie
        binding.textMovieTitle.text = movie.title
        binding.textMovieDescription.text = movie.overview
        binding.imageMovie.loadImage(movie.posterPath)

        binding.recyclerReview.apply {
            adapter = reviewAdapter
            layoutManager = LinearLayoutManager(
                this@MovieDetailActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
            addItemDecoration(ItemDividerVertical(resources.getDimensionPixelSize(R.dimen.dimens_8dp)))
        }
    }

    private fun initEvent() {
        binding.imageShare.setOnClickListener {
            showBottomSheet()
        }

        binding.imageFavorite.setOnClickListener {
            val movie = movieDetailViewModel.movie
            val favoriteMovie = FavoriteMovieData(
                movie.id,
                movie.posterPath,
                movie.title,
                movie.overview
            )
            if (movieDetailViewModel.isFavorite) {
                movieDetailViewModel.removeFavoriteMovie(favoriteMovie)
            } else {
                movieDetailViewModel.insertFavoriteMovie(favoriteMovie)
            }
        }
    }

    private fun initObserver() {
        movieDetailViewModel.movieReviewListLiveData.observe(this) {
            movieDetailViewModel.getFavoriteMovieList()
            reviewAdapter.submitList(it)
        }

        movieDetailViewModel.getFavoriteMovieListLiveData.observe(this) {
            val movie = movieDetailViewModel.movie
            val favoriteMovie = FavoriteMovieData(
                movie.id,
                movie.posterPath,
                movie.title,
                movie.overview
            )
            movieDetailViewModel.isFavorite = it.contains(favoriteMovie)
        }

        movieDetailViewModel.insertReminderLiveData.observe(this) {
            movieDetailViewModel.getMovieReviewList()
            showToast(getString(R.string.label_success_insert_data))
        }

        movieDetailViewModel.removeReminderLiveData.observe(this) {
            movieDetailViewModel.getMovieReviewList()
            showToast(getString(R.string.label_success_remove_data))
        }

        movieDetailViewModel.viewState.observe(this) {
            when (it) {
                ViewStateModel.LOADING -> binding.progressBarLoading.setVisible()
                else -> binding.progressBarLoading.setGone()
            }
        }

        movieDetailViewModel.showErrorLiveData.observe(this) { errorMessage ->
            showError(errorMessage)
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

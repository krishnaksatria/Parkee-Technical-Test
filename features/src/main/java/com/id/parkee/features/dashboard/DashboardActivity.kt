package com.id.parkee.features.dashboard

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.parkee.android.libraries.extension.setGone
import com.parkee.android.libraries.extension.setVisible
import com.id.parkee.commons.adapter.itemdecoration.ItemDividerHorizontal
import com.id.parkee.commons.extensions.showError
import com.id.parkee.commons.navigation.FavoriteNavigation
import com.id.parkee.commons.navigation.MovieDetailNavigation
import com.id.parkee.commons.ui.viewmodel.ViewStateModel
import com.id.parkee.features.R
import com.id.parkee.features.databinding.ActivityDashboardBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.scope.ScopeActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named

class DashboardActivity : ScopeActivity() {

    private lateinit var binding: ActivityDashboardBinding

    private val dashboardViewModel: DashboardViewModel by viewModel()

    private val popularAdapter: MovieBannerAdapter by inject()
    private val topRatedAdapter: MovieAdapter by inject(
        qualifier = named(
            TYPE_TOP_RATED
        )
    )
    private val nowPlayingAdapter: MovieAdapter by inject(
        qualifier = named(
            TYPE_NOW_PLAYING
        )
    )

    private val movieDetailNavigation: MovieDetailNavigation by inject()
    private val favoriteNavigation: FavoriteNavigation by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initLayout()
        initEvent()
        initObserver()
        initData()
    }

    private fun initData() {
        dashboardViewModel.getPopularMovieList()
        dashboardViewModel.getTopRatedMovieList()
        dashboardViewModel.getNowPlayingMovieList()
    }

    private fun initLayout() {
        binding.recyclerPopularMovie.apply {
            adapter = popularAdapter
            layoutManager = LinearLayoutManager(
                this@DashboardActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            addItemDecoration(ItemDividerHorizontal(resources.getDimensionPixelSize(R.dimen.dimens_10dp)))
        }

        binding.recyclerTopRated.apply {
            adapter = topRatedAdapter
            layoutManager = LinearLayoutManager(
                this@DashboardActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            addItemDecoration(ItemDividerHorizontal(resources.getDimensionPixelSize(R.dimen.dimens_8dp)))
        }

        binding.recyclerNowPlaying.apply {
            adapter = nowPlayingAdapter
            layoutManager = LinearLayoutManager(
                this@DashboardActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            addItemDecoration(ItemDividerHorizontal(resources.getDimensionPixelSize(R.dimen.dimens_8dp)))
        }
    }

    private fun initEvent() {
        binding.appBarDashboard.setOnEndButtonClickListener {
            startActivity(favoriteNavigation.createIntent(this))
        }

        popularAdapter.setOnMovieClickListener { _, movie ->
            startActivity(movieDetailNavigation.createIntent(this, movie))
        }

        topRatedAdapter.setOnMovieClickListener { _, movie ->
            startActivity(movieDetailNavigation.createIntent(this, movie))
        }

        nowPlayingAdapter.setOnMovieClickListener { _, movie ->
            startActivity(movieDetailNavigation.createIntent(this, movie))
        }
    }

    private fun initObserver() {
        dashboardViewModel.popularMovieListLiveData.observe(this) {
            popularAdapter.submitList(it)
        }

        dashboardViewModel.topRatedMovieListLiveData.observe(this) {
            topRatedAdapter.submitList(it)
        }

        dashboardViewModel.nowPlayingMovieListLiveData.observe(this) {
            nowPlayingAdapter.submitList(it)
        }

        dashboardViewModel.viewState.observe(this) {
            when (it) {
                ViewStateModel.LOADING -> binding.progressBarLoading.setVisible()
                else -> binding.progressBarLoading.setGone()
            }
        }

        dashboardViewModel.showErrorLiveData.observe(this) { errorMessage ->
            showError(errorMessage)
        }
    }

    companion object {
        const val TYPE_TOP_RATED = "top_rated"
        const val TYPE_NOW_PLAYING = "now_playing"
    }
}

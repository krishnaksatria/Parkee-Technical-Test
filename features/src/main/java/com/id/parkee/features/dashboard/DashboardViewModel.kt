package com.id.parkee.features.dashboard

import androidx.lifecycle.MutableLiveData
import com.id.parkee.commons.ui.viewmodel.BaseViewModel
import com.id.parkee.commons.ui.viewmodel.ViewStateModel
import com.id.parkee.commons.ui.viewmodel.getErrorMessage
import com.id.parkee.core.movie.data.MovieDetailModel
import com.id.parkee.core.movie.domain.MovieRepositoryContract
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class DashboardViewModel(
    private val movieRepositoryContract: MovieRepositoryContract
) : BaseViewModel() {
    val popularMovieListLiveData: MutableLiveData<List<MovieDetailModel>> = MutableLiveData()
    val topRatedMovieListLiveData: MutableLiveData<List<MovieDetailModel>> = MutableLiveData()
    val nowPlayingMovieListLiveData: MutableLiveData<List<MovieDetailModel>> = MutableLiveData()

    fun getPopularMovieList() {
        viewState.value = ViewStateModel.LOADING
        movieRepositoryContract.getPopularMovieList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    viewState.value = ViewStateModel.SUCCESS
                    popularMovieListLiveData.value = it
                },
                {
                    viewState.value = ViewStateModel.FAILED
                    showErrorLiveData.value = getErrorMessage(it)
                }
            ).addToDisposable()
    }

    fun getTopRatedMovieList() {
        viewState.value = ViewStateModel.LOADING
        movieRepositoryContract.getTopRatedMovieList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    viewState.value = ViewStateModel.SUCCESS
                    topRatedMovieListLiveData.value = it
                },
                {
                    viewState.value = ViewStateModel.FAILED
                    showErrorLiveData.value = getErrorMessage(it)
                }
            ).addToDisposable()
    }

    fun getNowPlayingMovieList() {
        viewState.value = ViewStateModel.LOADING
        movieRepositoryContract.getNowPlayingMovieList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    viewState.value = ViewStateModel.SUCCESS
                    nowPlayingMovieListLiveData.value = it
                },
                {
                    viewState.value = ViewStateModel.FAILED
                    showErrorLiveData.value = getErrorMessage(it)
                }
            ).addToDisposable()
    }
}

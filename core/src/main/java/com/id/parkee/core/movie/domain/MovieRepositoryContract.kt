package com.id.parkee.core.movie.domain

import com.id.parkee.core.movie.data.MovieDetailModel
import com.id.parkee.core.movie.data.reviews.MovieReviewModel
import io.reactivex.rxjava3.core.Single

interface MovieRepositoryContract {
    fun getPopularMovieList(): Single<List<MovieDetailModel>>
    fun getTopRatedMovieList(): Single<List<MovieDetailModel>>
    fun getNowPlayingMovieList(): Single<List<MovieDetailModel>>
    fun getMovieReviewList(id: Int): Single<List<MovieReviewModel>>
}

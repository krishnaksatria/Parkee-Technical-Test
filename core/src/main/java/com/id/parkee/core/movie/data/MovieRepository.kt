package com.id.parkee.core.movie.data

import com.id.parkee.core.movie.data.reviews.MovieReviewModel
import com.id.parkee.core.movie.data.reviews.toModel
import com.id.parkee.core.movie.domain.MovieRepositoryContract
import io.reactivex.rxjava3.core.Single

class MovieRepository(
    private val movieService: MovieService
) : MovieRepositoryContract {
    override fun getPopularMovieList(): Single<List<MovieDetailModel>> =
        movieService.getPopularMovieList().map { it.toModel().movieList }

    override fun getTopRatedMovieList(): Single<List<MovieDetailModel>> =
        movieService.getTopRatedMovieList().map { it.toModel().movieList }

    override fun getNowPlayingMovieList(): Single<List<MovieDetailModel>> =
        movieService.getNowPlayingMovieList().map { it.toModel().movieList }

    override fun getMovieReviewList(id: Int): Single<List<MovieReviewModel>> =
        movieService.getMovieReviewList(id).map { it.toModel().reviewList }
}

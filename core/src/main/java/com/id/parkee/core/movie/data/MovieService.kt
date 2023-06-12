package com.id.parkee.core.movie.data

import com.id.parkee.dto.movie.GetMovieListResponse
import com.id.parkee.dto.movie.reviews.GetMovieReviewListResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieService {
    @GET("movie/popular")
    fun getPopularMovieList(): Single<GetMovieListResponse>

    @GET("movie/top_rated")
    fun getTopRatedMovieList(): Single<GetMovieListResponse>

    @GET("movie/now_playing")
    fun getNowPlayingMovieList(): Single<GetMovieListResponse>

    @GET("movie/{id}/reviews")
    fun getMovieReviewList(
        @Path("id") movieId: Int
    ): Single<GetMovieReviewListResponse>
}

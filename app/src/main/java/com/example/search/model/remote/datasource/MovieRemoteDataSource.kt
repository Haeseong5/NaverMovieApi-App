package com.example.search.model.remote.datasource

import com.example.search.model.vo.dto.NaverMovieResponse
import io.reactivex.Single

interface MovieRemoteDataSource {
    fun getMovies(query: String): Single<NaverMovieResponse>
}
package com.example.search.model.remote.datasource

import com.example.search.model.vo.dto.NaverMovieResponse
import com.example.search.model.remote.api.NaverApi
import io.reactivex.Single

class MovieRemoteDataSourceImpl (private val naverApi: NaverApi) : MovieRemoteDataSource {
    override fun getMovies(query: String): Single<NaverMovieResponse> {
        return naverApi.getMovies(query)
    }
}
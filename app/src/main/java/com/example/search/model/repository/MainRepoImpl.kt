package com.example.search.model.repository

import com.example.search.model.local.datasource.RecentSearchDataSource
import com.example.search.model.vo.dto.NaverMovieResponse
import com.example.search.model.remote.datasource.MovieRemoteDataSource
import com.example.search.model.vo.entity.RecentSearch
import io.reactivex.Completable
import io.reactivex.Single

class MainRepoImpl (private val movieRemoteDataSource : MovieRemoteDataSource, private val local: RecentSearchDataSource) : MainRepo {

    override fun getMovies(query: String): Single<NaverMovieResponse> {
        return movieRemoteDataSource.getMovies(query)
    }

    override fun insert(recentSearch: RecentSearch) : Completable {
        return local.insert(recentSearch)
    }

    override fun delete(id: Int): Completable {
        return local.delete(id)
    }

    override fun deleteAllWords(): Completable {
        return local.deleteAllWords()
    }

    override fun getAllWords(): Single<List<RecentSearch>> {
        return local.getAllWords()
    }


}
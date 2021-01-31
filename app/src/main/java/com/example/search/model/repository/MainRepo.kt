package com.example.search.model.repository

import com.example.search.model.vo.dto.NaverMovieResponse
import com.example.search.model.vo.entity.RecentSearch
import io.reactivex.Completable
import io.reactivex.Single

interface MainRepo{
    //remote data source
    fun getMovies(query: String) : Single<NaverMovieResponse>

    //local data source
    fun insert(recentSearch: RecentSearch) : Completable
    fun delete(id: Int) : Completable
    fun deleteAllWords() : Completable

    fun getAllWords() : Single<List<RecentSearch>>
}

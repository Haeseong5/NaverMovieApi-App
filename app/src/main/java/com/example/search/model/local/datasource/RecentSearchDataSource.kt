package com.example.search.model.local.datasource

import com.example.search.model.vo.entity.RecentSearch
import io.reactivex.Completable
import io.reactivex.Single

interface RecentSearchDataSource{

    fun insert(recentSearch: RecentSearch) : Completable

    fun deleteAllWords() : Completable

    fun delete(id: Int) : Completable

    fun getAllWords() : Single<List<RecentSearch>>

}
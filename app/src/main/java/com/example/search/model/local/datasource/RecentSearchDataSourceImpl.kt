package com.example.search.model.local.datasource

import com.example.search.model.local.LocalDataSource
import com.example.search.model.vo.entity.RecentSearch
import io.reactivex.Completable
import io.reactivex.Single

class RecentSearchDataSourceImpl(private val localDataSource: LocalDataSource):
    RecentSearchDataSource {

    override fun insert(recentSearch: RecentSearch) : Completable {
        return localDataSource.insert(recentSearch)
    }

    override fun deleteAllWords() : Completable{
        return localDataSource.deleteAllWords()
    }

    override fun delete(id: Int): Completable {
        return localDataSource.delete(id)
    }

    override fun getAllWords(): Single<List<RecentSearch>> {
        return localDataSource.getAllWords()
    }

}
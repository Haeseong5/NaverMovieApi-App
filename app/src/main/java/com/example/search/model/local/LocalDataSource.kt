package com.example.search.model.local

import com.example.search.model.local.database.RecentSearchDao
import com.example.search.model.vo.entity.RecentSearch
import io.reactivex.Completable
import io.reactivex.Single

class LocalDataSource (private val recentSearchDao: RecentSearchDao) {

    fun insert(recentSearch: RecentSearch) : Completable {
        return recentSearchDao.insert(recentSearch = recentSearch)
    }

    fun delete(id: Int) : Completable {
        return recentSearchDao.delete(id)
    }

    fun deleteAllWords() : Completable {
        return recentSearchDao.deleteAllWords()
    }

    fun getAllWords() : Single<List<RecentSearch>> {
        return recentSearchDao.getAllWords()
    }

}
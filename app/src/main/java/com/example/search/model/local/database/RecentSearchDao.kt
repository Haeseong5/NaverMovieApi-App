package com.example.search.model.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.search.model.vo.entity.RecentSearch
import io.reactivex.Completable
import io.reactivex.Single


@Dao
interface RecentSearchDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(recentSearch: RecentSearch) : Completable

    @Query("DELETE FROM RecentSearch")
    fun deleteAllWords() : Completable

    @Query("DELETE FROM RecentSearch WHERE id = :id")
    fun delete(id: Int) : Completable

    @Query("SELECT * FROM RecentSearch order by id desc limit 10") //상위 10개만 출력
    fun getAllWords() : Single<List<RecentSearch>>

}
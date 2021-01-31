package com.example.search.model.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.search.model.vo.entity.RecentSearch

@Database(entities = [RecentSearch::class], version = 2, exportSchema = false)
abstract class LocalDatabase : RoomDatabase(){

    abstract fun recentSearchDao() : RecentSearchDao

}
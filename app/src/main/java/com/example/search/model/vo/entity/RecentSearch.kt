package com.example.search.model.vo.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "RecentSearch")
data class RecentSearch(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val word: String
)
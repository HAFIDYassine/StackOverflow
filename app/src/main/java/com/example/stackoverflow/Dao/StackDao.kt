package com.example.stackoverflow.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.stackoverflow.data.Question
import kotlinx.coroutines.flow.Flow

@Dao
interface StackDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<Question>)

    @Query("SELECT * FROM question_table")
    fun getQuestionListFlow(): Flow<List<Question>>
}
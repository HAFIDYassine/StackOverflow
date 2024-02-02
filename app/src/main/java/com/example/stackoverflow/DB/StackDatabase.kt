package com.example.stackoverflow.DB

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.stackoverflow.Dao.StackDao
import com.example.stackoverflow.data.Question

@Database(
    entities = [Question::class],
    version = 1,
    exportSchema = false

)

abstract class StackDatabase: RoomDatabase() {
    abstract fun stackDao(): StackDao
}
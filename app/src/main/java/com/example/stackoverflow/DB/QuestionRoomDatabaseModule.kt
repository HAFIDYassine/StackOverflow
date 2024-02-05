package com.example.stackoverflow.DB

import android.content.Context
import androidx.room.Room
import com.example.stackoverflow.Dao.StackDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object QuestionRoomDatabaseModule {

    @Provides
    fun provideQuestionDao(questionRoomDatabase: StackDatabase) : StackDao {
        return questionRoomDatabase.stackDao()
    }

    @Provides
    @Singleton
    fun provideQuestionRoomDatabase(@ApplicationContext appContext: Context):
            StackDatabase {
        return Room.databaseBuilder(
            appContext.applicationContext,
            StackDatabase::class.java,
            "question_database"
        ).build()
    }

}
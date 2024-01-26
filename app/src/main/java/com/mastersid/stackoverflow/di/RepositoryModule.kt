package com.mastersid.stackoverflow.di

import com.mastersid.stackoverflow.repository.QuestionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule  {

    @Provides
    fun provideQuestionRepository() : QuestionRepository = QuestionRepository()
}
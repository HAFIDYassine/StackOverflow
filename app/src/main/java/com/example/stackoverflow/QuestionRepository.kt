package com.example.stackoverflow

import kotlinx.coroutines.flow.Flow


interface QuestionRepository {
    val questionResponse: Flow<QuestionResponse>
    suspend fun updateQuestionsInfo()
}
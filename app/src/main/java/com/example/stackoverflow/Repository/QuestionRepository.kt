package com.example.stackoverflow.Repository

import com.example.stackoverflow.QuestionResponse
import kotlinx.coroutines.flow.Flow


interface QuestionRepository {
    val questionResponse: Flow<QuestionResponse>
    suspend fun updateQuestionsInfo()
}
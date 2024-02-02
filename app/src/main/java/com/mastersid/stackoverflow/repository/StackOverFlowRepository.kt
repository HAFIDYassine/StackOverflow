package com.mastersid.stackoverflow.repository

import com.mastersid.stackoverflow.webservice.StackOverflowApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class StackOverflowRepository @Inject constructor(private val api: StackOverflowApi) {
    private val _questionsFlow = MutableStateFlow<QuestionsResponse>(QuestionsResponse.Pending)
    val questionsFlow: StateFlow<QuestionsResponse> = _questionsFlow

    suspend fun updateQuestionsInfo() {
        _questionsFlow.value = QuestionsResponse.Pending
        val response = getActiveQuestions()
        if (response.isSuccessful) {
            _questionsFlow.value = QuestionsResponse.Success(response.body()?.questions ?: emptyList())
        } else {
        }

    }

    suspend fun getActiveQuestions() = api.getActiveQuestions()
}
package com.mastersid.stackoverflow.repository

import com.mastersid.stackoverflow.data.Question
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.random.Random

sealed interface QuestionsResponse {
    object Pending : QuestionsResponse
    data class Success(val questions: List<Question>) : QuestionsResponse
}

class QuestionRepository {
    private val _questionsFlow = MutableStateFlow<QuestionsResponse>(QuestionsResponse.Pending)
    val questionsFlow: Flow<QuestionsResponse> = _questionsFlow.asStateFlow()

    suspend fun updateQuestionsInfo() {
        _questionsFlow.value = QuestionsResponse.Pending
        delay(5000) // Attendre 5 secondes

        // Générer une liste aléatoire de questions
        val randomQuestions = List(Random.nextInt(1, 10)) {
            Question(
                "lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum",
                it,
                "Question $it",
                Random.nextInt(1, 5)
            )
        }

        _questionsFlow.value = QuestionsResponse.Success(randomQuestions)
    }
}
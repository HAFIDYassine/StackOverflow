package com.mastersid.stackoverflow.ui.question

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mastersid.stackoverflow.data.Question
import com.mastersid.stackoverflow.repository.QuestionRepository
import com.mastersid.stackoverflow.repository.QuestionsResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionViewModel @Inject constructor(
    private val repository: QuestionRepository
)
    : ViewModel() {

    val isUpdating = mutableStateOf(false)
    val questions = mutableStateOf(emptyList<Question>())

    init {
        viewModelScope.launch {
            repository.questionsFlow.collect { response ->
                when (response) {
                    is QuestionsResponse.Pending -> {
                        isUpdating.value = true
                    }
                    is QuestionsResponse.Success -> {
                        isUpdating.value = false
                        questions.value = response.questions
                    }
                }
            }
        }
    }

    fun updateQuestions() {
        viewModelScope.launch {
            Log.d("viewmodel","hello from updateQuestions()")
            isUpdating.value = true
            repository.updateQuestionsInfo()
            isUpdating.value = false
        }
    }
}
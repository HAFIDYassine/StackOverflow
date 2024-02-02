package com.mastersid.stackoverflow.ui.question

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mastersid.stackoverflow.data.Question
import com.mastersid.stackoverflow.repository.QuestionsResponse
import com.mastersid.stackoverflow.repository.StackOverflowRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionViewModel @Inject constructor(
    private val repository: StackOverflowRepository
)
    : ViewModel() {

    val isUpdating = MutableLiveData(false)
    val questions = mutableStateOf(emptyList<Question>())

    init {
        viewModelScope.launch {
            updateQuestions()
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
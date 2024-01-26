package com.mastersid.stackoverflow.ui.question

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mastersid.stackoverflow.data.Question
import com.mastersid.stackoverflow.repository.QuestionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionViewModel @Inject constructor(
    private val repository: QuestionRepository
)
    : ViewModel() {

    val isUpdating = mutableStateOf(false)

    val questions = mutableStateOf(
        listOf(
            Question(
                "lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum ",
                1,
                "Kotlin doesn't work",
                3
            ),
            Question(
                "lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum",
                2,
                "Short question",
                1
            ),
            Question(
                "lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum",
                3,
                "a very very very very very very very very very long question",
                4
            )
        )
    )

    fun updateQuestions() {
        viewModelScope.launch {
            Log.d("viewmodel","hello from updateQuestions()")
            isUpdating.value = true
            // repository.updateQuestionsInfo()
            isUpdating.value = false
        }
    }
}
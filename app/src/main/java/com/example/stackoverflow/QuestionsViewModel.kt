package com.example.stackoverflow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stackoverflow.data.Question
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class QuestionsViewModel @Inject constructor(
    private val questionRepository: QuestionRepository
): ViewModel() {
    private val _question: MutableLiveData<List<Question>> = MutableLiveData(emptyList())
    val question: LiveData<List<Question>> = _question

    private val _isUpdating = MutableLiveData(false)
    val isUpdating: LiveData<Boolean> = _isUpdating

    fun updateQuestions() {
        viewModelScope.launch(Dispatchers.IO){
            questionRepository.updateQuestionsInfo()

        }
    }

    init {
        viewModelScope.launch(Dispatchers.IO){
            questionRepository.questionResponse.collect { response ->
                when(response){
                    is QuestionResponse.Pending -> _isUpdating.postValue(true)
                    is QuestionResponse.Success -> {
                        _question.postValue(response.list)
                        _isUpdating.postValue(false)

                    }
                }

            }
        }
    }






}
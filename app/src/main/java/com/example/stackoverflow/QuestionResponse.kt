package com.example.stackoverflow

import com.example.stackoverflow.data.Question

sealed interface QuestionResponse {

    object Pending : QuestionResponse
    @JvmInline
    value class Success(val list: List<Question>) : QuestionResponse
    data class Error(val message: String) : QuestionResponse

    @JvmInline
    value class Error(val message: String) : QuestionResponse

}
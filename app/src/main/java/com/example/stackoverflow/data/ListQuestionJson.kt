package com.example.stackoverflow.data

data class ListQuestionJson(
    val items: List<QuestionJson>
)

data class QuestionJson(
    val title: String,
    val answer_count: Int

)

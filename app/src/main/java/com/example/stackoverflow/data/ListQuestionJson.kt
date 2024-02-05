package com.example.stackoverflow.data

data class ListQuestionJson(
    val items: List<QuestionJson>
)

data class QuestionJson(
    val id: Int?,
    val title: String,
    val answer_count: Int

)

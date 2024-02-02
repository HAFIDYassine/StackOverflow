package com.mastersid.stackoverflow.webservice

import com.mastersid.stackoverflow.data.Question
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class QuestionResponse(
    @Json(name = "items")
    val questions: List<Question>
)
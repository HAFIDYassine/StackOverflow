package com.mastersid.stackoverflow.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Question(
    val id: Int,
    val body: String,
    val title: String,
    @Json(name = "answer_count")
    val answerCount: Int
)

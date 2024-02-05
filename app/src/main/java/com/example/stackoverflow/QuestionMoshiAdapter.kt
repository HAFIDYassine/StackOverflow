package com.example.stackoverflow

import com.example.stackoverflow.data.ListQuestionJson
import com.example.stackoverflow.data.Question
import com.example.stackoverflow.data.QuestionJson
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

class QuestionMoshiAdapter {
    @FromJson
    fun fromJson(listQuestionJson: ListQuestionJson): List<Question> {
        return listQuestionJson.items.map { questionJson ->
            Question(questionJson.id,questionJson.title, questionJson.answer_count)
        }
    }

    @ToJson
    fun ToJson(listQuestion: List<Question>): ListQuestionJson {
        return ListQuestionJson(
            listQuestion.map {question ->
            QuestionJson(question.id,question.title, question.answerCount)
            }
        )
    }
}
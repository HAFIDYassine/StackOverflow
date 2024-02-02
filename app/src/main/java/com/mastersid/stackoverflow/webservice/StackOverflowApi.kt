package com.mastersid.stackoverflow.webservice

import retrofit2.Response
import retrofit2.http.GET

interface StackOverflowApi {
    @GET("questions?pagesize=20&order=desc&sort=activity&site=stackoverflow")
    suspend fun getActiveQuestions(): Response<QuestionResponse>
}
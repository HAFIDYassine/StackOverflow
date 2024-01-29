/*package com.example.stackoverflow.Repository

import com.example.stackoverflow.QuestionResponse
import com.example.stackoverflow.data.Question
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class QuestionRepositoryDummyImpl @Inject constructor() : QuestionRepository {
    override val questionResponse : MutableStateFlow<QuestionResponse> = MutableStateFlow(
        QuestionResponse.Success(emptyList())
    )
    override suspend fun updateQuestionsInfo(){
        questionResponse.emit(QuestionResponse.Pending)
        delay(5000)
        questionResponse.emit(
            QuestionResponse.Success(
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
        )
    }

}*/
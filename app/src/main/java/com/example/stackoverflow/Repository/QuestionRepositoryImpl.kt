package com.example.stackoverflow.Repository

import android.net.http.HttpException
import com.example.stackoverflow.Coroutine.CoroutineScopeIO
import com.example.stackoverflow.Dao.StackDao
import com.example.stackoverflow.QuestionResponse
import com.example.stackoverflow.WebService.QuestionWebservice
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject
import retrofit2.HttpException as HttpException1

class QuestionRepositoryImpl @Inject constructor(
    private val questionwebservice: QuestionWebservice,
    private val questionDao: StackDao,
    @CoroutineScopeIO private val coroutineScopeIO: CoroutineScope
) : QuestionRepository {
    override val questionResponse: MutableStateFlow<QuestionResponse> = MutableStateFlow(
        QuestionResponse.Success(emptyList())
    )

    init {
        coroutineScopeIO.launch {
            questionDao.getQuestionListFlow().collect { list ->
                questionResponse.emit(QuestionResponse.Success(list))
            }
        }
    }

    override suspend fun updateQuestionsInfo() {
            try {
                val list = questionwebservice
                    .getQuestionList(
                        pagesize = 20,
                        order = "desc",
                        sort = "activity",
                        site = "stackoverflow"
                    )
                questionDao.insertAll(list)
            } catch (e: IOException) {
                questionResponse.emit(QuestionResponse.Error("Network Error"))
            } catch (e: HttpException1) {
                questionResponse.emit(QuestionResponse.Error("Request Error"))
                /* questionResponse.emit(QuestionResponse.Pending)
           val list = questionwebservice
               .getQuestionList(
                   pagesize = 20,
                   order = "desc",
                   sort = "activity",
                   site = "stackoverflow"
               )
           questionDao.insertAll(list)*/

            }
        }
    }




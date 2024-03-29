package com.example.stackoverflow.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.stackoverflow.UpdateQuestionButton
import com.example.stackoverflow.ViewModel.QuestionsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestionScreen(questionsViewModel: QuestionsViewModel = viewModel()) {
    val questionList by questionsViewModel.question.observeAsState(initial = emptyList())
    val refreshing by questionsViewModel.isUpdating.observeAsState(initial = false)
    val error by questionsViewModel.error.observeAsState()
    val context = LocalContext.current
    val snackbarHostState = remember {
        SnackbarHostState()
    }

    Scaffold(
        content = { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(questionList)
                    { question ->


                        Row(
                            Modifier.fillMaxWidth()
                        ) {
                            Column(
                                modifier = Modifier.width(320.dp)
                            ) {

                                Text(
                                    text = question.title,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    style = MaterialTheme.typography.headlineSmall

                                )
                            }

                            Text(
                                text = question.answerCount.toString(),
                                style = MaterialTheme.typography.displaySmall,
                                textAlign = TextAlign.Start
                            )
                        }
                    }
                }
                if (refreshing) {
                    LinearProgressIndicator(modifier = Modifier.align(Alignment.TopStart))
                }
            }
        },
        floatingActionButton = {
            UpdateQuestionButton(
                updateQuestions = questionsViewModel::updateQuestions,

                modifier = Modifier
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    )
    error?.let {
        LaunchedEffect(it) {
            snackbarHostState.showSnackbar(
                message = it,
                actionLabel = "Dismiss"
            )
        }
    }
}





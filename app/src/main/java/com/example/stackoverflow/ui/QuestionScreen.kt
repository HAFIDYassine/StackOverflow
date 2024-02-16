package com.example.stackoverflow.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.stackoverflow.ViewModel.QuestionsViewModel
import com.example.stackoverflow.data.Question

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestionScreen() {

    val questionsViewModel : QuestionsViewModel = viewModel()

    val questionList by questionsViewModel.question.observeAsState(initial = emptyList())
    val refreshing by questionsViewModel.isUpdating.observeAsState(initial = false)

    val error by questionsViewModel.error.observeAsState()

    val snackbarHostState = remember {
        SnackbarHostState()
    }

    Scaffold(content = { innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding)
        ) {
            when (refreshing) {
                true -> {
                    LinearProgressIndicator(modifier = Modifier.align(Alignment.TopStart))
                }

                false -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(questionList) { question ->
                            QuestionItem(question)
                        }
                    }
                }
            }
        }
    }, floatingActionButton = {
        UpdateQuestionButton(
            updateQuestions = questionsViewModel::updateQuestions,

            modifier = Modifier
        )
    }, snackbarHost = {
        SnackbarHost(hostState = snackbarHostState)
    })
    error?.let {
        LaunchedEffect(it) {
            snackbarHostState.showSnackbar(
                message = it, actionLabel = "Dismiss"
            )
        }
    }
}

@Composable
fun QuestionItem(question: Question) {
    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = question.title,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .weight(3f),
        )


        Text(
            text = question.answerCount.toString(),
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Start,
        )

        Icon(
            Icons.Rounded.Send,
            contentDescription = "send icon",
        )

    }
}

@Composable
fun UpdateQuestionButton(updateQuestions: () -> Unit, modifier: Modifier, error : Boolean = false) {
    Button(onClick = updateQuestions,
        modifier = Modifier
            .padding(16.dp)

    ) {
        Text("Update Questions")
    }

}

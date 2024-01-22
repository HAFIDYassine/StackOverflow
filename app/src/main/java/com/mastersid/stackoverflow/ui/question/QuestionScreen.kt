package com.mastersid.stackoverflow.ui.question

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun QuestionScreen(questionViewModel: QuestionViewModel = viewModel()) {

    Box {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(questionViewModel.questionList.value)
            { question ->

                Row(
                    Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.width(320.dp)
                    ) {
                        LinearProgressIndicator()
                        Text(
                            text = question.title,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.headlineSmall

                        )
                        Text(
                            text = question.body,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            fontSize = 16.sp,
                        )
                    }

                    Text(
                        text = question.answerCount.toString(),
                        style = MaterialTheme.typography.displaySmall,
                        textAlign = TextAlign.Start
                    )
                }
                Button(
                    onClick = { questionViewModel.updateQuestions() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Update questions")
                }
            }
        }
    }
}
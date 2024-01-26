package com.mastersid.stackoverflow.ui.question

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mastersid.stackoverflow.data.Question


@Composable
fun QuestionScreen(questionViewModel: QuestionViewModel = viewModel()) {


    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            LinearProgressIndicator(
                modifier = Modifier.fillMaxWidth(),
                progress = 0.5f,
            )
        }
        items(questionViewModel.questions.value)
        { question ->

            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,

            ) {
                QuestionAndBody(question = question, modifier = Modifier
                    .weight(4f)
                    .fillMaxWidth()
                )
                Text(
                    text = question.answerCount.toString(),
                    style = MaterialTheme.typography.displaySmall,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                )
            }

        }
        item {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { questionViewModel.updateQuestions() }) {
                Text("Update questions")
            }
        }
    }
}

@Composable
fun QuestionAndBody(question: Question, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
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
}

@Preview
@Composable
fun QuestionScreenPreview() {
    QuestionScreen()
}

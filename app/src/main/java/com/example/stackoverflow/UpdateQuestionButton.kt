package com.example.stackoverflow

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun UpdateQuestionButton(updateQuestions: () -> Unit, modifier: Modifier, error : Boolean = false) {
    Button(onClick = updateQuestions,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)

    ) {
        Text("Update Questions")
    }

}
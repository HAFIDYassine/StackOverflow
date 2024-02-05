package com.example.stackoverflow.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "question_table")

@Parcelize
data class Question(@PrimaryKey val id: Int?, val title: String, val answerCount: Int): Parcelable


package com.example.jetpackcomposecrashcourse.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class School(
    @PrimaryKey(autoGenerate = false)
    val name: String
)

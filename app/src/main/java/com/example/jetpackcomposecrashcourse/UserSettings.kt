package com.example.jetpackcomposecrashcourse

import kotlinx.serialization.Serializable

@Serializable
data class UserSettings(
    val userName: String? = null,
    val password: String? = null
)

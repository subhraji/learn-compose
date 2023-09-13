package com.example.jetpackcomposecrashcourse.room.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.jetpackcomposecrashcourse.room.db.UserDatabase
import com.example.jetpackcomposecrashcourse.room.model.User
import com.example.jetpackcomposecrashcourse.room.ui.ui.theme.JetpackComposeCrashCourseTheme
import kotlinx.coroutines.launch

class RoomHomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = Room.databaseBuilder(
            applicationContext,
            UserDatabase::class.java,
            "user.db"
        ).build()

        lifecycleScope.launch {
            db.dao.getUsers().forEach(::println)
        }

        /*(1..10).forEach{
            lifecycleScope.launch {
                db.dao.insertUser(
                    User(
                        email = "testEmail@test$it.com",
                        userName = "test$it"
                    )
                )
            }
        }*/


    }
}
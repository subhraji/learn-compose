package com.example.jetpackcomposecrashcourse.room.db

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.jetpackcomposecrashcourse.room.model.User

@Database(
    entities = [User::class],
    version = 2,
    autoMigrations = [
        AutoMigration(from = 1, to = 2)
    ]
)
abstract class UserDatabase: RoomDatabase() {
    abstract val dao: UserDao
}
package com.example.jetpackcomposecrashcourse.room.db

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RenameColumn
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.jetpackcomposecrashcourse.room.model.School
import com.example.jetpackcomposecrashcourse.room.model.User

@Database(
    entities = [User::class, School::class],
    version = 4,
    autoMigrations = [
        AutoMigration(from = 1, to = 2),
        AutoMigration(from = 2, to = 3, spec = UserDatabase.Migration2to3::class)
    ]
)
abstract class UserDatabase: RoomDatabase() {
    abstract val dao: UserDao

    @RenameColumn(tableName = "User", fromColumnName = "created", toColumnName = "createdAt")
    class Migration2to3(): AutoMigrationSpec

    companion object{
        val migration3To4 = object : Migration(3, 4){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE IF NOT EXISTS school (name CHAR NOT NULL PRIMARY KEY)")
            }
        }
    }
}
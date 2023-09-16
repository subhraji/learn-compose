package com.example.jetpackcomposecrashcourse

import androidx.room.testing.MigrationTestHelper
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.jetpackcomposecrashcourse.room.db.UserDatabase
import com.google.common.truth.Truth.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

private const val DB_NAME = "test.db"
@RunWith(AndroidJUnit4::class)
class UserMigrationTest {
    @get:Rule
    val helper = MigrationTestHelper(
        InstrumentationRegistry.getInstrumentation(),
        UserDatabase::class.java,
        listOf(UserDatabase.Migration2to3()),
        FrameworkSQLiteOpenHelperFactory()
    )

    @Test
    fun migration1To2_containsCorrectData(){
        var db = helper.createDatabase(DB_NAME, 1).apply {
            execSQL("INSERT INTO user VALUES('subhrajit@fmail.com', 'Papai')")
            close()
        }

        db = helper.runMigrationsAndValidate(DB_NAME, 2, true)

        db.query("SELECT * FROM user").apply {
            assertThat(moveToFirst()).isTrue()
            assertThat(getLong(getColumnIndex("created"))).isEqualTo(0)
        }
    }
}
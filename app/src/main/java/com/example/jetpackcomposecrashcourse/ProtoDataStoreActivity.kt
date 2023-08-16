package com.example.jetpackcomposecrashcourse

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.datastore.dataStore
import com.example.jetpackcomposecrashcourse.proto_datastore.AppSettings
import com.example.jetpackcomposecrashcourse.proto_datastore.AppSettingsSerializer
import com.example.jetpackcomposecrashcourse.proto_datastore.Language
import com.example.jetpackcomposecrashcourse.ui.theme.JetpackComposeCrashCourseTheme
import kotlinx.coroutines.launch

class ProtoDataStoreActivity : ComponentActivity() {

    private val Context.dataStore by dataStore(
        fileName = "app-settings.json",
        serializer = AppSettingsSerializer()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeCrashCourseTheme {
                val appSetting = dataStore.data.collectAsState(
                    initial = AppSettings()
                ).value
                val scope = rememberCoroutineScope()
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    for (i in 0..2){
                        val language = Language.values()[i]
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            RadioButton(
                                selected =  language == appSetting.language,
                                onClick = {
                                    scope.launch {
                                        setLanguage(language)
                                    }
                                }
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = language.toString())
                        }
                    }

                }
            }
        }
    }

    private suspend fun setLanguage(language: Language){
        dataStore.updateData {
            it.copy(
                language = language
            )
        }
    }
}
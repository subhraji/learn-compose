@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.jetpackcomposecrashcourse

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposecrashcourse.ui.theme.JetpackComposeCrashCourseTheme
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class AndroidCryptoActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //process -1 key store
        val cryptoManager = CryptoManager()

        //process -2 local.properties
        val apiKey = BuildConfig.API_KEY
        Toast.makeText(this, apiKey, Toast.LENGTH_LONG).show()

        setContent {
            JetpackComposeCrashCourseTheme {
                var messageToEncrypt = remember{
                    mutableStateOf("")
                }

                var messageToDecrypt = remember{
                    mutableStateOf("")
                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp)
                ) {
                    OutlinedTextField(
                        value = messageToEncrypt.value,
                        onValueChange = { text ->
                            messageToEncrypt.value = text
                        },
                        modifier = Modifier.fillMaxWidth(),
                        textStyle = TextStyle(
                            color = Color.Black
                        ),
                        placeholder = {
                            Text(text = "Encrypt")
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row {
                        Button(onClick = {
                            val bytes = messageToEncrypt.value.encodeToByteArray()
                            val file = File(filesDir, "secret.txt")
                            if(!file.exists()){
                                file.createNewFile()
                            }
                            val fos = FileOutputStream(file)
                            messageToDecrypt.value = cryptoManager.encrypt(
                                bytes = bytes,
                                outputStream = fos
                            ).decodeToString()
                        }) {
                            Text(text = "Encrypt")
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Button(onClick = {
                            val file = File(filesDir, "secret.txt")
                            messageToEncrypt.value = cryptoManager.decrypt(
                                inputStream = FileInputStream(file)
                            ).decodeToString()

                        }) {
                            Text(text = "Decrypt")
                        }
                    }
                    Text(text = messageToDecrypt.value)
                }
            }
        }
    }
}

@Composable
fun Greeting2(file: File) {

}

/*
@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    JetpackComposeCrashCourseTheme {
        Greeting2()
    }
}*/

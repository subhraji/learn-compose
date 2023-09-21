package com.example.jetpackcomposecrashcourse.workmanager.api

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET

interface FileApi {

    //url to download the image
    @GET("/wp-content/uploads/2022/02/220849-scaled.jpg")
    suspend fun downloadImage(): Response<ResponseBody>

    /*As we are not using dependency injection, So we are creating the retrofit instance here.*/

    /*We don't need the GSON converter as we are not going to deal with any JSON response.*/
    companion object{
        val instance by lazy {
            Retrofit.Builder()
                .baseUrl("https://pl-coding.com")
                .build()
                .create(FileApi::class.java)
        }
    }
}
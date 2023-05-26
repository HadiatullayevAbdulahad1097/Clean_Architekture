package com.example.cleanarhitekture.remote

import com.example.cleanarhitekture.ui.model.NoteResponse
import com.example.cleanarhitekture.ui.model.Note
import com.example.cleanarhitekture.ui.model.PostNote
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface MovieInterface {

    @GET("plan")
    fun getNote(): Call<Note>

    @POST("plan/")
    fun postNote(@Body postNote: PostNote): Call<NoteResponse>
}
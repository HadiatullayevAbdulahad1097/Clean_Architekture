package com.example.cleanarhitekture.ui.useCase

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cleanarhitekture.remote.MovieInterface
import com.example.cleanarhitekture.ui.model.Note
import com.example.cleanarhitekture.ui.model.NoteResponse
import com.example.cleanarhitekture.ui.model.PostNote
import com.example.cleanarhitekture.ui.repository.InterfaceFromUseCase
import com.example.cleanarhitekture.ui.viewModel.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostNoteUseCase(val movieInterface: MovieInterface) : InterfaceFromUseCase {
    private val TAG = "PostNoteUseCase"
    val result = MutableLiveData<Result<NoteResponse>>()
    override fun postNote(postNote: PostNote): LiveData<Result<NoteResponse>> {
        movieInterface.postNote(postNote).enqueue(object : Callback<NoteResponse> {
            override fun onResponse(call: Call<NoteResponse>, response: Response<NoteResponse>) {
                if (response.isSuccessful) {
                    Log.e(TAG, "onResponse: ")
                    result.postValue(Result.success(response.body()!!))
                } else {
                    result.postValue(Result.error(response.code().toString()))
                }
            }

            override fun onFailure(call: Call<NoteResponse>, t: Throwable) {
                result.postValue(Result.error(t.message))
            }
        })
        return result
    }
}
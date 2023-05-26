package com.example.cleanarhitekture.ui.useCase

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cleanarhitekture.remote.MovieInterface
import com.example.cleanarhitekture.ui.model.Note
import com.example.cleanarhitekture.ui.repository.InterfaceFromGetNoteUseCase
import com.example.cleanarhitekture.ui.viewModel.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetNoteUseCase(val movieInterface: MovieInterface) : InterfaceFromGetNoteUseCase {
    private val TAG = "GetNoteUseCase"
    val result = MutableLiveData<Result<Note>>()
    override fun getNote(): LiveData<Result<Note>> {
        movieInterface.getNote().enqueue(object : Callback<Note> {
            override fun onResponse(call: Call<Note>, response: Response<Note>) {
                if (response.isSuccessful) {
                    Log.e(TAG, "onResponse: ")
                    result.postValue(Result.success(response.body()!!))
                } else {
                    result.postValue(Result.error(response.code().toString()))
                }
            }

            override fun onFailure(call: Call<Note>, t: Throwable) {
                result.postValue(Result.error(t.message))
            }
        })
        return result
    }
}
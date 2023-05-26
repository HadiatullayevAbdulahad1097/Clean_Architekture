package com.example.cleanarhitekture.ui.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cleanarhitekture.ui.model.Note
import com.example.cleanarhitekture.ui.viewModel.Result

interface InterfaceFromGetNoteUseCase {

    fun getNote(): LiveData<Result<Note>>

}
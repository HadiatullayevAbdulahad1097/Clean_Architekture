package com.example.cleanarhitekture.ui.repository

import androidx.lifecycle.LiveData
import com.example.cleanarhitekture.ui.model.Note
import com.example.cleanarhitekture.ui.model.NoteResponse
import com.example.cleanarhitekture.ui.model.PostNote
import com.example.cleanarhitekture.ui.viewModel.Result

interface InterfaceFromUseCase {

    fun postNote(postNote: PostNote): LiveData<Result<NoteResponse>>

}
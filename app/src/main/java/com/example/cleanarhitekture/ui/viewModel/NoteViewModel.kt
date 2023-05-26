package com.example.cleanarhitekture.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import com.example.cleanarhitekture.ui.model.Note
import com.example.cleanarhitekture.ui.model.NoteResponse
import com.example.cleanarhitekture.ui.model.PostNote
import com.example.cleanarhitekture.ui.useCase.GetNoteUseCase
import com.example.cleanarhitekture.ui.useCase.PostNoteUseCase
import com.example.cleanarhitekture.ui.utils.UtilsFunction
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.internal.Util
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val postNoteUseCase: PostNoteUseCase,
    private val getNoteUseCase: GetNoteUseCase
) : ViewModel() {

    var postNoteLd = MutableLiveData<Result<NoteResponse>>()

    fun postNote(postNote: PostNote) {
        postNoteUseCase.postNote(postNote).observe(UtilsFunction.owner){
            postNoteLd.postValue(it)
            getNote()
        }
    }

    var getNoteLd = MutableLiveData<Result<Note>>()

    fun getNote() {
        getNoteUseCase.getNote().observe(UtilsFunction.owner) {
            getNoteLd.postValue(it)
        }
    }
}
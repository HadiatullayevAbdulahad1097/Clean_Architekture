package com.example.cleanarhitekture.ui.utils.dialogs

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.cleanarhitekture.R
import com.example.cleanarhitekture.databinding.ItemDialogPostNoteBinding
import com.example.cleanarhitekture.ui.adapter.RvNoteAdapter
import com.example.cleanarhitekture.ui.model.Note
import com.example.cleanarhitekture.ui.model.PostNote
import com.example.cleanarhitekture.ui.viewModel.NoteViewModel
import com.example.cleanarhitekture.ui.viewModel.Status
import java.text.SimpleDateFormat
import java.util.Calendar

class AddNoteDialog(
    val fragment: Fragment,
) {
    private var customDialog: AlertDialog? = null
    lateinit var dialogBinding: ItemDialogPostNoteBinding

    fun show(viewModel: NoteViewModel) {
        if (customDialog == null) {
            initObserve(viewModel)
            createDialog(viewModel)
        }
    }

    fun initObserve(viewModel: NoteViewModel){
        viewModel.postNoteLd.observe(fragment.viewLifecycleOwner){
            when (it.status) {
                Status.SUCCESS -> {
                    Log.e("AddNoteDialog", "onCreate: Success")
                }

                Status.ERROR -> {
                    Log.e("AddNoteDialog", "onCreate: Error")
                }
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun createDialog(viewModel: NoteViewModel) {
        customDialog = AlertDialog.Builder(fragment.requireContext()).create()
        dialogBinding =
            ItemDialogPostNoteBinding.inflate(LayoutInflater.from(fragment.requireContext()))
        customDialog?.setView(dialogBinding.root)

        dialogBinding.btnSave.setOnClickListener {
            val title = dialogBinding.edtTitle.text.toString().trim()
            val note = dialogBinding.edtNote.text.toString().trim()
            if (title.isNotBlank() && note.isNotBlank()) {
                val time = Calendar.getInstance().time
                val simpleFormat = SimpleDateFormat("yyyy-MM-dd")
                val date = simpleFormat.format(time)
                val postNote = PostNote("Yangi", note, date, title)
                viewModel.postNote(postNote)
                Toast.makeText(fragment.requireContext(), "Malumot Saqlandi", Toast.LENGTH_SHORT)
                    .show()
                dismiss()
            } else {
                Toast.makeText(fragment.requireContext(), "Malumot To'liq emas", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        dialogBinding.btnBack.setOnClickListener {
            dismiss()
        }

        customDialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
        customDialog?.window?.setWindowAnimations(R.style.DialogTheme)
        customDialog?.setCancelable(false)
        customDialog?.show()
    }

    fun dismiss() {
        customDialog?.dismiss()
        customDialog = null
    }
}
package com.example.cleanarhitekture.ui.fragment.movie

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.cleanarhitekture.databinding.FragmentMoviesBinding
import com.example.cleanarhitekture.ui.adapter.RvNoteAdapter
import com.example.cleanarhitekture.ui.model.InfoNote
import com.example.cleanarhitekture.ui.model.Note
import com.example.cleanarhitekture.ui.utils.UtilsFunction
import com.example.cleanarhitekture.ui.utils.dialogs.AddNoteDialog
import com.example.cleanarhitekture.ui.viewModel.NoteViewModel
import com.example.cleanarhitekture.ui.viewModel.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : Fragment() {
    private lateinit var binding: FragmentMoviesBinding
    private lateinit var addNoteDialog: AddNoteDialog
    private val viewModel: NoteViewModel by viewModels()
    private val list = ArrayList<InfoNote>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentMoviesBinding.inflate(layoutInflater)
        addNoteDialog = AddNoteDialog(this)
        UtilsFunction.owner = this
        initObserve()
        viewModel.getNote()
    }

    private fun initObserve() {
        viewModel.getNoteLd.observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    Log.e("MoviesFragment", "onCreate: ${it.data}")
                    val noteValue = (it.data as Note)
                    noteValue.let { note ->
                        list.clear()
                        list.addAll(note)
                    }
                    binding.rvNote.adapter = RvNoteAdapter(list)
                }

                Status.ERROR -> {
                    Log.e("MoviesFragment", "onCreate: Error")
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.add.setOnClickListener {
            addNoteDialog.show(viewModel)
        }
    }
}
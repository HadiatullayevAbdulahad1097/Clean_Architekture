package com.example.cleanarhitekture.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanarhitekture.databinding.ItemNoteBinding
import com.example.cleanarhitekture.ui.model.InfoNote

class RvNoteAdapter(var list: ArrayList<InfoNote>) :
    RecyclerView.Adapter<RvNoteAdapter.Vh>() {
    inner class Vh(var itemNoteBinding: ItemNoteBinding) : RecyclerView.ViewHolder(itemNoteBinding.root) {
        fun onBind(kurslar: InfoNote) {
            itemNoteBinding.note.text = kurslar.matn
            itemNoteBinding.title.text = kurslar.sarlavha
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size
}
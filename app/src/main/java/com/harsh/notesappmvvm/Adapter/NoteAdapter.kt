package com.harsh.notesappmvvm.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.harsh.notesappmvvm.DB.NoteEntity
import com.harsh.notesappmvvm.Fragments.FirstFragment
import com.harsh.notesappmvvm.Interfaces.OnItemClicked
import com.harsh.notesappmvvm.R

class NoteAdapter(var notesEntity: ArrayList<NoteEntity>, var onItemClicked: OnItemClicked) :
    RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(notesEntity[position])
    };

    override fun getItemCount() = notesEntity.size

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        private val contentTextView: TextView = itemView.findViewById(R.id.contentTextView)
        private val btnUpdate: Button = itemView.findViewById(R.id.btnUpdate)
        private val btnDelete: Button = itemView.findViewById(R.id.btnDelete)

        fun bind(note: NoteEntity) {
            titleTextView.text = note.title
            contentTextView.text = note.content
            btnUpdate.setOnClickListener() {
                onItemClicked.UpdateNote(note)
            }
            btnDelete.setOnClickListener() {
                onItemClicked.deleteNote(note)
            }
        }


    }

    private class DiffCallback : DiffUtil.ItemCallback<NoteEntity>() {
        override fun areItemsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
            return oldItem == newItem
        }
    }
}
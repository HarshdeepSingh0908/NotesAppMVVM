package com.harsh.notesappmvvm.Fragments

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Update
import com.harsh.notesappmvvm.Adapter.NoteAdapter
import com.harsh.notesappmvvm.DB.NoteDatabase
import com.harsh.notesappmvvm.DB.NoteEntity
import com.harsh.notesappmvvm.Interfaces.OnItemClicked
import com.harsh.notesappmvvm.MainActivity
import com.harsh.notesappmvvm.R
import com.harsh.notesappmvvm.databinding.FragmentFirstBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class FirstFragment : Fragment(), OnItemClicked {
    private lateinit var binding: FragmentFirstBinding
    private lateinit var noteAdapter: NoteAdapter
    var notes = arrayListOf<NoteEntity>()
    lateinit var mainActivity: MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = activity as MainActivity

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFirstBinding.inflate(layoutInflater)
        noteAdapter = NoteAdapter(notes, this)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = noteAdapter
        }
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_firstFragment_to_secondFragment)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            getNotes()
    }

    fun getNotes(){
        notes.clear()
        GlobalScope.launch {
            notes.addAll( mainActivity.notesDatabase.noteDao().getAllNotes())
            noteAdapter.notifyDataSetChanged()
        }
    }

    override fun deleteNote(note: NoteEntity) {
        GlobalScope.launch(Dispatchers.IO) {
           mainActivity.notesDatabase.noteDao().delete(
                note
            )
        }
        getNotes()

    }

    override fun UpdateNote(note: NoteEntity) {
        var dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.update_note)
        var etTitle = dialog.findViewById<EditText>(R.id.etTitle)
        var etContent = dialog.findViewById<EditText>(R.id.etContent)
        var btnUpdate = dialog.findViewById<Button>(R.id.btnUpdate)
        btnUpdate.setOnClickListener{
            if (etTitle.toString().isNotEmpty() && etContent.toString().isNotEmpty()){
                GlobalScope.launch(Dispatchers.IO) {
                    
                    mainActivity.notesDatabase.noteDao().updateNote(
                        note
                    )
                }
            }
        }
        dialog.show()

    }


}
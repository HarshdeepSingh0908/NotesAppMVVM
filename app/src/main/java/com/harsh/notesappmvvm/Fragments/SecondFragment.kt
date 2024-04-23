package com.harsh.notesappmvvm.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.harsh.notesappmvvm.DB.NoteDatabase
import com.harsh.notesappmvvm.DB.NoteEntity
import com.harsh.notesappmvvm.R
import com.harsh.notesappmvvm.databinding.FragmentSecondBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SecondFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondBinding.inflate(layoutInflater)

        binding.saveButton.setOnClickListener {
            val title = binding.titleEditText.text.toString()
            val content = binding.contentEditText.text.toString()

            if (title.isNotEmpty() && content.isNotEmpty()) {

                GlobalScope.launch(Dispatchers.IO) {
                    val noteDao = NoteDatabase.getInstance(requireContext()).noteDao()
                    noteDao.insert(NoteEntity(title = title, content = content))
                }

                findNavController().popBackStack()
            } else {

            }
        }

        return binding.root
    }
}
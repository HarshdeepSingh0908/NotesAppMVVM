package com.harsh.notesappmvvm.Interfaces

import com.harsh.notesappmvvm.DB.NoteEntity

interface OnItemClicked {
    fun deleteNote(note : NoteEntity)
    fun UpdateNote(note : NoteEntity)
}
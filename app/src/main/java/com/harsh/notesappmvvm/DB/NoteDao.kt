package com.harsh.notesappmvvm.DB

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteDao {
    @Insert
    fun insert(note: NoteEntity)

    @Query("SELECT * FROM notes")
    fun getAllNotes(): List<NoteEntity>

    @Update
    fun updateNote(note: NoteEntity)

    @Delete
    fun delete(noteEntity: NoteEntity)



}
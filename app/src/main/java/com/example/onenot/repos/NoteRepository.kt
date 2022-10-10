package com.example.onenot.repos

import androidx.lifecycle.LiveData
import com.example.onenot.Note
import com.example.onenot.NotesDao

class NoteRepository(private  val notesdao:NotesDao) {

val allnotes:LiveData<List<Note>> =  notesdao.getAllNotes()

    suspend fun insert(note:Note){
        notesdao.insert(note)
    }

    suspend fun  delete(note: Note){
        notesdao.delete(note)
    }

    suspend fun update(note: Note){
        notesdao.update(note)
    }

}
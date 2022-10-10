package com.example.onenot.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.onenot.Note
import com.example.onenot.NoteDatabase
import com.example.onenot.repos.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class NoteViewModel(appctx: Application) : AndroidViewModel(appctx) {

    val allnte : LiveData<List<Note>>
    val repos : NoteRepository
    init {

        val dao = NoteDatabase.getDatabase(appctx).noteDao
        repos =  NoteRepository(dao)
        allnte  = repos.allnotes
    }

    fun deleteNote(note:Note) = viewModelScope.launch (Dispatchers.IO){
        repos.delete(note)
    }

    fun  updateNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repos.update(note)
    }

    fun  addNote(note: Note) =viewModelScope.launch(Dispatchers.IO) {
        repos.insert(note)
    }






}
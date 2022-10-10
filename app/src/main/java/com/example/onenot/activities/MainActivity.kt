package com.example.onenot.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.onenot.Note
import com.example.onenot.R
import com.example.onenot.adapters.NoteAdapter
import com.example.onenot.adapters.NoteClickDeleteListener
import com.example.onenot.adapters.NoteClickInterface
import com.example.onenot.view_model.NoteViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), NoteClickInterface, NoteClickDeleteListener {
    lateinit var recyclerView: RecyclerView
    lateinit var addbtn : FloatingActionButton
    lateinit var viewmodal : NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addbtn=findViewById(R.id.floatingActionButton)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val  noteAdapter = NoteAdapter(this,this,this)
        recyclerView.adapter = noteAdapter
        viewmodal = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application))
            .get(NoteViewModel::class.java)

        viewmodal.allnte.observe(this, Observer { list->

            list?.let {
                noteAdapter.updateList(it)
            }

        })
        addbtn.setOnClickListener {
            val intent = Intent(this@MainActivity,AddNoteActivity::class.java)
            intent.putExtra("notetype","add")
            startActivity(intent)
            this.finish();
        }

    }

    override fun onClick(note: Note) {
        val intent = Intent(this@MainActivity,AddNoteActivity::class.java)
        intent.putExtra("notetype","edit")
        intent.putExtra("noteTitle",note.noteTitle)
        intent.putExtra("noteDesc",note.noteDescription)
        intent.putExtra("id",note.id)
        startActivity(intent)
    }

    override fun onDeleteClick(note: Note) {
        viewmodal.deleteNote(note)
        Toast.makeText(this,"${note.noteTitle} Deleted",Toast.LENGTH_SHORT).show()
    }
}
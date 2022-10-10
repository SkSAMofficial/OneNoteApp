package com.example.onenot.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.example.onenot.Note
import com.example.onenot.R
import com.example.onenot.view_model.NoteViewModel
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*

class AddNoteActivity : AppCompatActivity() {
    lateinit var title : EditText
    lateinit var desc :  EditText
    lateinit var addbtn : Button
    lateinit var noteType : String
    lateinit var viewModel : NoteViewModel
     var  noteId = -1
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
        addbtn=findViewById(R.id.add)
        title=findViewById(R.id.title)
        desc=findViewById(R.id.desc)
        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.
        getInstance(application)).get(NoteViewModel::class.java)
        setListeners()

        noteType = intent.getStringExtra("notetype").toString()

        if (noteType.equals("add")){


        }else{
            val noteTitle = intent.getStringExtra("noteTitle")
            val noteDesc = intent.getStringExtra("noteDesc")
            noteId = intent.getStringExtra("id")?. let { Integer.parseInt(it) }!!
            title.setText(noteTitle)
            desc.setText(noteDesc)
            addbtn.setText("Update Note")
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun setListeners() {
        addbtn.setOnClickListener {
            val sdf  = SimpleDateFormat("dd mm yyyy - HH:mm")
            val currDate:String = sdf.format(Date())
            if (noteType.equals("edit"))
            { val updateNote = Note(title.text.toString()
                    ,desc.text.toString(),currDate)
                updateNote.id = noteId
                viewModel.updateNote(updateNote)
                Snackbar.make(it,"Note Updated",Snackbar.LENGTH_SHORT).show()


            }  else {
                if (title.text.isEmpty() || desc.text.isEmpty()) {
                    Snackbar.make(it, "Please Fill All Details", Snackbar.LENGTH_SHORT).show()
                } else {
                    viewModel.addNote(Note(title.text.toString(),desc.text.toString(),currDate))
                }
            }
        startActivity(Intent(this,MainActivity::class.java))
        this.finish()
        }
    }
}
package com.example.onenot.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.onenot.Note
import com.example.onenot.R

class NoteAdapter(
    val context: Context,
    val noteClickInterface: NoteClickInterface,
    val noteClickDeleteListener: NoteClickDeleteListener
) : RecyclerView.Adapter<NoteAdapter.MyHolder>() {

    val allnotes = ArrayList<Note>()

    inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.title)
        val time = itemView.findViewById<TextView>(R.id.time)
        val del = itemView.findViewById<ImageView>(R.id.delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.note_rv_item, parent, false)
        return MyHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.title.setText(allnotes.get(position).noteTitle)
        holder.time.setText("Last Updated " + allnotes.get(position).timeStamp)

        holder.del.setOnClickListener {
            noteClickDeleteListener.onDeleteClick(allnotes.get(position))
        }
        holder.itemView.setOnClickListener {
            noteClickInterface.onClick(allnotes.get(position))
        }
    }

    override fun getItemCount(): Int {
        return allnotes.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newlist: List<Note>) {
        allnotes.clear()
        allnotes.addAll(newlist)
        notifyDataSetChanged()
    }
}

interface NoteClickDeleteListener {
    fun onDeleteClick(note: Note)
}

interface NoteClickInterface {
    fun onClick(note: Note)
}

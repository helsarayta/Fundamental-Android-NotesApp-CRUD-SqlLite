package com.heydieandroid.mynotesapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.heydieandroid.mynotesapp.databinding.ItemNoteBinding

class NoteAdapter(private val onItemClickedCallBack: OnItemCallback) :RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    var listNotes = ArrayList<Note>()
        set(listNotes) {
            if(listNotes.size > 0) {
                this.listNotes.clear()
            }
            this.listNotes.addAll(listNotes)
        }

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemNoteBinding.bind(itemView)
        fun bind(note: Note) {
            binding.tvItemTitle.text = note.title
            binding.tvItemDate.text = note.date
            binding.tvItemDescription.text = note.description
            binding.cvItemNote.setOnClickListener {
                onItemClickedCallBack.onItemClicked(note, adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listNotes.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(listNotes[position])
    }

    interface OnItemCallback {
        fun onItemClicked(selectedNote:Note?, position: Int?)
    }

    fun addItem(note: Note) {
        this.listNotes.add(note)
        notifyItemInserted(this.listNotes.size - 1)
    }

    fun updateItem(position: Int, note: Note) {
        this.listNotes[position] = note
        notifyItemChanged(position, note)
    }
    fun removeItem(position: Int) {
        this.listNotes.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, this.listNotes.size)
    }
}
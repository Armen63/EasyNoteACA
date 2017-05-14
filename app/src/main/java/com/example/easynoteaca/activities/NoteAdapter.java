package com.example.easynoteaca.activities;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.easynoteaca.R;
import com.example.easynoteaca.pojo.Note;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteViewHolder>{

    private List<Note> data;


    public void setNotes(List<Note> notes){
        this.data=notes;

    }

    public void addNote(Note note){
        data.add(note);
    }

    public NoteAdapter() {

    }
    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}

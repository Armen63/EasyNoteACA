package com.example.easynoteaca.util.note;


import com.example.easynoteaca.pojo.Note;

import java.util.List;

public abstract class NoteStorage {

    public abstract void createNote(Note note, NoteListener noteListener);

    public abstract void findNote(long id, NoteListener noteListener);

    public abstract void findAllNotes(NotesFoundListener notesFoundListener);

    public abstract void updateNote(Note note, NoteListener noteListener);

    public abstract void deleteNote(Note note, NoteDeleteListener noteDeleteListener);


    protected void notifyNoteFound(Note note, NoteListener noteListener){
        if(noteListener != null){
            noteListener.onNote(note);
        }
    }

    protected void notifyNotesFound(List<Note> notes, NotesFoundListener notesFoundListener){
        if(notesFoundListener != null){
            notesFoundListener.onNotesFound(notes);
        }
    }

    public interface NoteDeleteListener{
        void onNoteDeleted(boolean successful);
    }

    public interface NotesFoundListener{
        void onNotesFound(List<Note> notes);
    }

    public interface NoteListener{
        void onNote(Note note);
    }

}

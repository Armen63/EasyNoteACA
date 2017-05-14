package com.example.easynoteaca.util.note;

import com.example.easynoteaca.activities.App;
import com.example.easynoteaca.pojo.Note;
import com.example.easynoteaca.util.PreferancesHelper;
import com.example.easynoteaca.util.StorageHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FileNoteStorage extends NoteStorage {

    private static final String NOTES_FILE_NAME = "FileNoteStorage.Notes";

    private NotesWrapper notesWrapper;


    public FileNoteStorage() {
        notesWrapper = (NotesWrapper) StorageHelper.deserialize(getFileName());

        if(notesWrapper == null){
            notesWrapper = new NotesWrapper();
        }
    }

    private String getFileName(){
        return String.format("%s_%s",
                NOTES_FILE_NAME,
                PreferancesHelper.getInstance(App.getInstance()).getUserId());
    }

    @Override
    public void createNote(Note note, NoteListener noteListener) {
        note.setId(System.currentTimeMillis());
        note.setCreateDate(new Date());

        notesWrapper.getNotes().add(note);
        StorageHelper.serialize(getFileName(), notesWrapper);

        notifyNoteFound(note, noteListener);
    }

    @Override
    public void findNote(long id, NoteListener noteListener) {
        for (Note note : notesWrapper.getNotes()){
            if(note.getId() == id){
                notifyNoteFound(note, noteListener);
                return;
            }
        }

        notifyNoteFound(null, noteListener);
    }

    @Override
    public void findAllNotes(NotesFoundListener notesFoundListener) {
        notifyNotesFound(notesWrapper.getNotes(), notesFoundListener);
    }

    @Override
    public void updateNote(Note note, NoteListener noteListener) {

    }

    @Override
    public void deleteNote(Note note, NoteDeleteListener noteDeleteListener) {

    }


    public static class NotesWrapper implements Serializable {
        static final long serialVersionUID = -1;

        private List<Note> notes;

        public NotesWrapper() {
            notes = new ArrayList<>();
        }

        public List<Note> getNotes() {
            return notes;
        }
    }

}

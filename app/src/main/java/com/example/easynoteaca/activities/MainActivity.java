package com.example.easynoteaca.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.easynoteaca.R;
import com.example.easynoteaca.pojo.Note;
import com.example.easynoteaca.util.PreferancesHelper;
import com.example.easynoteaca.util.note.FileNoteStorage;
import com.example.easynoteaca.util.note.NoteStorage;

import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String NOTES_FILE_NAME = "FileNoteStorage.Notes";
    private static final int FAB_REQUEST_CODE = 1;
    private NoteStorage noteStorage;
    private RecyclerView recyclerView;
    private NoteAdapter adapter;
    private FloatingActionButton fab;
    private Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Main Screen");

        fab = (FloatingActionButton) findViewById(R.id.fab);
        findViewById(R.id.fab_exit).setOnClickListener(this);

        fab.setOnClickListener(this);
        noteStorage = new FileNoteStorage();

        recyclerView = (RecyclerView) findViewById(R.id.main_list_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new NoteAdapter();
        recyclerView.setAdapter(adapter);



        noteStorage.findAllNotes(new NoteStorage.NotesFoundListener() {
            @Override
            public void onNotesFound(List<Note> notes) {
                adapter.setNotes(notes);
                adapter.notifyDataSetChanged();
            }
        });
    }


    private void logout() {
        PreferancesHelper.getInstance(this).resetAll();
        startActivity(new Intent(this, StartupActivity.class));
        finish();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                Intent intent = new Intent(MainActivity.this, CreateNoteActivity.class);
                startActivityForResult(intent, FAB_REQUEST_CODE);
                break;
            case R.id.fab_exit:
                logout();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case FAB_REQUEST_CODE: {
                if (resultCode == Activity.RESULT_OK) {
                    note = (Note) data.getSerializableExtra(CreateNoteActivity.NEW_NOTE);
                    noteStorage.createNote(note, new NoteStorage.NoteListener() {
                        @Override
                        public void onNote(Note note) {
//                            adapter.addNote(note);
                            adapter.notifyDataSetChanged();
                        }
                    });
                }
                break;
            }
        }
    }

}

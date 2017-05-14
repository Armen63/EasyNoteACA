package com.example.easynoteaca.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.easynoteaca.R;
import com.example.easynoteaca.pojo.Note;
import com.example.easynoteaca.util.note.FileNoteStorage;
import com.example.easynoteaca.util.note.NoteStorage;

import petrov.kristiyan.colorpicker.ColorPicker;

public class CreateNoteActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String NEW_NOTE = "CreteNote.Activity.NewNote";
    private NoteStorage noteStorage;
    private EditText titleEt;
    private EditText descriptionEt;
    private TextView colorPickerTV;
    private CheckBox importantChb;
    private Button addBtn;
    private int backgroundColorInteger = Color.GREEN;

    private Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createnote);
        setTitle("Create Note");

        titleEt = (EditText) findViewById(R.id.create_note_title_et);
        descriptionEt = (EditText) findViewById(R.id.create_note_description_et);
        importantChb = (CheckBox) findViewById(R.id.create_note_chb);
        addBtn = (Button) findViewById(R.id.create_note_add_btn);
        colorPickerTV = (TextView) findViewById(R.id.color_picker);
        colorPickerTV.setBackgroundColor(Color.GREEN);
        colorPickerTV.setOnClickListener(this);
        addBtn.setOnClickListener(this);
        noteStorage = new FileNoteStorage();
    }




    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.create_note_add_btn:
                saveNote();
                break;
            case R.id.color_picker:
                ColorPicker colorPicker = new ColorPicker(this);
                colorPicker.show();
                colorPicker.setOnChooseColorListener(new ColorPicker.OnChooseColorListener() {
                    @Override
                    public void onChooseColor(int position, int color) {
                        colorPickerTV.setBackgroundColor(color);
                        backgroundColorInteger = color;
                    }

                    @Override
                    public void onCancel() {
                    }
                });

                break;
        }
    }

    private void saveNote() {
        if(!checkAllFieldsValue()){
            return;
        }
        String title = titleEt.getText().toString();
        String description = descriptionEt.getText().toString();
        boolean important = importantChb.isChecked();

        note = new Note(title, description, important, backgroundColorInteger);

        handelNoteFound(note);


    }

    private void handelNoteFound(Note note) {
        if (note == null) {
            Toast.makeText(this, R.string.wrong_data, Toast.LENGTH_SHORT).show();
            return;
        }

        finish();

    }

    @Override
    public void finish() {
        Intent intent = new Intent();
        intent.putExtra(NEW_NOTE, note);
        setResult(Activity.RESULT_OK, intent);
        super.finish();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finishAffinity();
    }
    private boolean checkAllFieldsValue(){

        if(titleEt.getText().length() == 0 || descriptionEt.getText().length() == 0 ){
            Toast.makeText(this,R.string.wrong_data,Toast.LENGTH_LONG).show();
            return  false;
        }
        return true;
    }
}

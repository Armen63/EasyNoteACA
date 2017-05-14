package com.example.easynoteaca.activities;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.easynoteaca.R;
import com.example.easynoteaca.pojo.Note;

import java.util.Calendar;

public class NoteViewHolder extends RecyclerView.ViewHolder {

    private TextView titleTv;
    private TextView descriptionTv;
    private CheckBox importantChb;
    private TextView datePickerTv;
    private View leftColor;

    public NoteViewHolder(View itemView) {
        super(itemView);

        titleTv = (TextView) itemView.findViewById(R.id.item_note_title);
        descriptionTv = (TextView) itemView.findViewById(R.id.item_note_description);
        importantChb = (CheckBox) itemView.findViewById(R.id.item_note_chb);
        datePickerTv = (TextView) itemView.findViewById(R.id.item_note_dp);
        leftColor = itemView.findViewById(R.id.leftColor);
    }

    public void bind(Note note) {
        Context context = App.getInstance().getApplicationContext();

        leftColor.setBackgroundColor(note.getColor());
        titleTv.setText(titleTv != null ? note.getTitle() : "");
        descriptionTv.setText(titleTv != null ? note.getDescription() : "");
        importantChb.setChecked(note.isImportant());
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        datePickerTv.setText(new StringBuilder()
                .append(day).append(".").append(month + 1).append(".")
                .append(year));
    }

}

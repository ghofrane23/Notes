package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddNoteActivity extends AppCompatActivity {

    private EditText noteEditText;
        Note notes;
    boolean isOldNote= false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        noteEditText = findViewById(R.id.note_text);
        notes = new Note();
        try {
            notes= (Note) getIntent().getSerializableExtra("LAST_NOTE");
            String text = getIntent().getStringExtra("LAST_NOTE");
            if(text != null)
                noteEditText.setText(notes.getNoteText() );
        } catch (Exception e){
            e.printStackTrace();
        }


        Button button = findViewById(R.id.add_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            // on click for the save button
            public void onClick(View v) {
                 if (!isOldNote) {
                    notes= new Note();
                }
                Intent i = new Intent();
                // if the rúsere didnt enter anything than we cancel
                //if he wrote somthing than else = ok and we put extra
                // to this intent and it#s going to attach the new test(extra) with others
                if(TextUtils.isEmpty(noteEditText.getText())) {
                    setResult(RESULT_CANCELED, i);
                } else {
                    setResult(RESULT_OK, i);
                    i.putExtra("NOTE_TEXT", noteEditText.getText().toString());
                }
                // close the activity
                finish();
            }
        });
    }
}

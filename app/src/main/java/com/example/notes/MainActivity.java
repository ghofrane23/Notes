package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Database;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity implements NoteListAdapter.ItemClickListener {

    public static final int ADD_NOTE_REQUEST = 1;
    public static final int UPDATE_NOTE_REQUEST = 2;  //TODO: You can use this for the assignment

    private RecyclerView noteListView;
    private FloatingActionButton fab;
    private ViewModel viewModel;
    private Note lastClickedNote;

    @Override
    // 1.on create : set up the view in particular we get referencr to the note
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        noteListView = findViewById(R.id.note_list);
        final NoteListAdapter adapter = new NoteListAdapter(this);
        adapter.setItemClickListener(this); //  for the floating action button
        noteListView.setAdapter(adapter);
        noteListView.setLayoutManager(new LinearLayoutManager(this));

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            // 2. will create an intent to start the other activity (in this case ad dNote activity))
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AddNoteActivity.class);
                startActivityForResult(i, ADD_NOTE_REQUEST); // startActivityForResult (new compared to the last assignment) is going to to expect a result from AddNoteActivity ,
                // here the reuslt is either ok (menas user indeed enter new node ) or canceled (MEANS did click on add note but didnt write any new notte) )
            }
        });

        //TODO: setup view model
        //viewModel = ViewModelProviders.of(this).get(ViewModel.class);
        viewModel = new ViewModel(getApplication());// for updating the view (everyime there is changes )
        viewModel.getNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                adapter.setNotes(notes);
            }
        });

    }
// 3. we can  listen to the activity here
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //here if the request is an add note request and if the result = ok means he indeed write something
        // then we will create new note and set the text to the text return from  the f note activity
        if(requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK){ // if it was an add note request then result return ok than we will create new  note object and we will put it in the database
            Note note = new Note();
            note.setNoteText(data.getStringExtra("NOTE_TEXT"));
            //TODO: call view model insert
            viewModel.insert(note);
        }
        if (requestCode == UPDATE_NOTE_REQUEST && resultCode == RESULT_OK){
            Note note = new Note();
            note.setNoteText(data.getStringExtra("NOTE_TEXT"));
            viewModel.insert(lastClickedNote);
            noteListView.notifyAll();

        }
        //TODO: For the assignment you can add the case of handling a note update here:

    }
// if you wnat to handle click on the note (you can use this to handle editing existiong note  )
    @Override
    public void onItemClicked(Note note) {
        lastClickedNote = note;
        Intent i = new Intent(MainActivity.this, AddNoteActivity.class);
        i.putExtra("LAST_NOTE", String.valueOf(note));
        startActivityForResult(i, UPDATE_NOTE_REQUEST);
        //TODO: For the assignment you can add the code for opening the AddNoteActivity
        // for an existing Note - and handling its update - here
    }

    @Override
    public void onItemLongClicked(Note note) {

    }


}

package com.example.notes;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ViewModel extends AndroidViewModel {

    NoteRepository repository;
    //
    private LiveData<List<Note>> notes;
// default constructor
    public ViewModel(@NonNull Application application) {
        super(application);
        // store reference to  rapository
        repository = new NoteRepository(application);
        notes = repository.getAllNotes();
    }

    public LiveData<List<Note>> getNotes(){
        return notes;
    }
    // want to be able to insert the note and
    public void insert(Note note){
        repository.insert(note);
    }

    public void updateNote(Note note){
        repository.insert(note);
    }


}

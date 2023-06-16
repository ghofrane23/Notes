package com.example.notes;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
// stands data acces object
public interface NoteDao {
// this provide methodes to acces note entity : it s like sql but we implement it as java method
    @Query("SELECT * FROM note")
    LiveData<List<Note>> getAllNotes();
// here we are going to  create a list of notes note objects and its wrapped as live data
    // mean we get live data : get automatic update in the UI about the new data coming from user (the UI will be inform that
// he should refresh to show this new note with other notes )
    @Insert
    void insertNote(Note note);// this will help us to use add note button


    @Update
    void updateNote(Note note);

    @Delete
    void deleteNote(Note note);

}


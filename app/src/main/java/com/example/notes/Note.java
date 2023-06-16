package com.example.notes;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
// meaning mark this room as entity
public class Note {
// define attributes  autogenrated = so we dont have to do it manually
    @PrimaryKey(autoGenerate = true)
    private int id;
// this entity note has 2 field 1 primary key and other is text
    @ColumnInfo(name="note_text")
    private String noteText;

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }
    public String getNoteText(){
        return noteText;
    }

    public void setNoteText(String noteText){
        this.noteText = noteText;
    }
}

package com.example.notes;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Scheduler;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class NoteRepository {
// we create repository = works as separtor fromdata model and view model

    private NoteDao noteDao;
    //
    private LiveData<List<Note>> notes;
    //first we want to get acces to our constructir  that retrieve acces to the DB
    public NoteRepository(Application application){
        AppDatabase db = AppDatabase.getInstance(application);
        // store the refernce in local vatiable notedao
        noteDao = db.noteDao();

        notes = noteDao.getAllNotes();
    }
    // we want to pass all reference to note and we do that by creating a getter for the notes object getallnotes
    public LiveData<List<Note>> getAllNotes(){
        return notes;
    }
//  from perspective of view model to be able to insert a note (we already created insert method in dao )
    public void insert(final Note note){
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                noteDao.insertNote(note);
            }
        }).subscribeOn(Schedulers.io()).subscribe();//tell android to run this code on a fred that is provided by this schedular and it puts it into action
    }


    public void updateNote(Note note){
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                noteDao.insertNote(note);
            }
        }).subscribeOn(Schedulers.io()).subscribe();

    }

    public void delete(final Note note){
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                noteDao.insertNote(note);
            }
        }).subscribeOn(Schedulers.io()).subscribe();//tell android to run this code on a fred that is provided by this schedular and it puts it into action
    }

}



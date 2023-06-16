package com.example.notes;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = {Note.class}, version=1)
// means we room it
public abstract class AppDatabase extends RoomDatabase {
// we add the as abstract
    public abstract NoteDao noteDao();
    // we create a static refrence callled instance
// here we create a sigleton pattern (instance) means there is gonna be only one instance in class at the database
    private static AppDatabase INSTANCE;
// we write getter to this refernve instance
    public static AppDatabase getInstance(Application application){
        if(INSTANCE == null)
            INSTANCE = Room.databaseBuilder(application, AppDatabase.class, "notes-db").
                    fallbackToDestructiveMigration().build(); // frameback migration in case of update or relaunch the app it need to migrate
        // here we need to have appliction parameter
        // we check if it#s null than create new instance and retun  it
        return INSTANCE;
    }
}

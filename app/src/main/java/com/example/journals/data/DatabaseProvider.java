package com.example.journals.data;

import android.content.Context;

import androidx.room.Room;

public class DatabaseProvider {

    private static AppDatabase appDbInstance;

    public static AppDatabase getAppDbInstance(Context context){
        if(DatabaseProvider.appDbInstance == null){
            DatabaseProvider.appDbInstance = Room.
                    databaseBuilder(
                            context,
                            AppDatabase.class,
                            "AppDatabase").build();
        }
        return DatabaseProvider.appDbInstance;
    }
}

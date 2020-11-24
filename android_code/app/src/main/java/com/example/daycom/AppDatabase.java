package com.example.daycom;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {repair.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract repairDao repDao();
    private static AppDatabase INST;
    public static AppDatabase getDatabase(Context cn){
        if(INST==null) {
            INST = Room.databaseBuilder(cn.getApplicationContext(), AppDatabase.class, "Daycom").allowMainThreadQueries().build();
        }
        return INST;

        }
        public static void destroyInterface(){
        INST=null;
    }
}

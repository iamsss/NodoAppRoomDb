package com.example.nodoapp.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.nodoapp.model.Nodo;

@Database(entities = {Nodo.class},version = 1)
public abstract class NodoRoomDataBase extends RoomDatabase {
    private static volatile NodoRoomDataBase INSTANCE;

    public abstract NodoDao nodoDao();

    public static NodoRoomDataBase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (NodoRoomDataBase.class) {
                if (INSTANCE == null) {
                    // Create our db
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            NodoRoomDataBase.class, "nodo_database")
                            .build();
                }
            }
        }
        return  INSTANCE;
    }

}

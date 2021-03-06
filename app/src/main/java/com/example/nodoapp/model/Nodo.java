package com.example.nodoapp.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "nodo_table")
public class Nodo {
    @PrimaryKey(autoGenerate = true)
    private  int id;
    @NonNull
    @ColumnInfo(name = "nodo_col")
    private String noDo;

    public Nodo(@NonNull String noDo) {
        this.noDo = noDo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getNoDo() {
        return noDo;
    }

    public void setNoDo(@NonNull String noDo) {
        this.noDo = noDo;
    }
}

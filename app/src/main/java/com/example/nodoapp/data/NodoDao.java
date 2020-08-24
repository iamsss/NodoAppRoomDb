package com.example.nodoapp.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.nodoapp.model.Nodo;

import java.util.List;

@Dao
public interface NodoDao {

    //CRUD Operation

    @Insert
    void  insert(Nodo nodo);

    @Query("DELETE FROM nodo_table")
    void deleteAll();

    @Query("DELETE FROM nodo_table WHERE id = :id")
    int deleteANodo(int id);

    @Update
    void update(Nodo nodo);

    @Query("SELECT * FROM nodo_table")
    List<Nodo> getAllNodos();
}

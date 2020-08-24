package com.example.nodoapp.utils;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.nodoapp.data.NodoDao;
import com.example.nodoapp.data.NodoRoomDataBase;
import com.example.nodoapp.model.Nodo;

import java.util.List;

public class NodoRepository {
    private NodoDao nodoDao;
    private LiveData<List<Nodo>> allNodos;

    public  NodoRepository(Application application){
        NodoRoomDataBase db = NodoRoomDataBase.getDatabase(application);
        nodoDao = db.nodoDao();
        allNodos = nodoDao.getAllNodos();
    }

    LiveData<List<Nodo>> getAllNodos(){
        return  allNodos;
    }

    void insert(Nodo nodo){
     new insertAsyncTask(nodoDao).execute(nodo);
    }

    private class insertAsyncTask extends AsyncTask<Nodo, Void, Void> {
        private NodoDao asyncTaskDao;

        public insertAsyncTask(NodoDao nodoDao) {
            asyncTaskDao = nodoDao;
        }

        @Override
        protected Void doInBackground(Nodo... params) {
            asyncTaskDao.insert(params[0]);
            return null;
        }
    }


}

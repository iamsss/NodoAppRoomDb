package com.example.nodoapp;

import android.content.Intent;
import android.os.Bundle;

import com.example.nodoapp.model.Nodo;
import com.example.nodoapp.model.NodoViewModel;
import com.example.nodoapp.ui.NodoListAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int NEW_RESULT_CODE = 1;
    private NodoListAdapter nodoListAdapter;
    private RecyclerView recyclerView;
    private NodoViewModel nodoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nodoViewModel = ViewModelProviders.of(this).get(NodoViewModel.class);

        recyclerView = findViewById(R.id.recyclerView);
        nodoListAdapter = new NodoListAdapter(this);
        recyclerView.setAdapter(nodoListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,NewNodoActivity.class);
                startActivityForResult(intent,NEW_RESULT_CODE);
            }
        });

        nodoViewModel.getAllNodos().observe(this, new Observer<List<Nodo>>() {
            @Override
            public void onChanged(List<Nodo> nodos) {
                //Update the Adapter List
                nodoListAdapter.setNodoList(nodos);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == NEW_RESULT_CODE && resultCode == RESULT_OK){

            Nodo nodo = new Nodo(data.getStringExtra("MyReply"));
            nodoViewModel.insert(nodo);
        }else{
            Toast.makeText(this,"Not Saved",Toast.LENGTH_SHORT).show();
        }
    }
}

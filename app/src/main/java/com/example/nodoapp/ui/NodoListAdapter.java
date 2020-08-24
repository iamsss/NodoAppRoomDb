package com.example.nodoapp.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nodoapp.R;
import com.example.nodoapp.model.Nodo;

import java.util.List;

public class NodoListAdapter extends RecyclerView.Adapter<NodoListAdapter.nodoViewHolder> {

    LayoutInflater nodoInflater;
    List<Nodo> nodoList;

    public  NodoListAdapter(Context context){
        nodoInflater = LayoutInflater.from(context);
    }
    @NonNull
    @Override
    public NodoListAdapter.nodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = nodoInflater.inflate(R.layout.recyler_view_layout,parent,false);
        return  new nodoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NodoListAdapter.nodoViewHolder holder, int position) {
        if(nodoList != null){
            Nodo current = nodoList.get(position);
            holder.nodoTextView.setText(current.getNoDo());
        }else {
            holder.nodoTextView.setText(R.string.no_todo);
        }
    }

    void setNodoList(List<Nodo> nodos){
        nodoList = nodos;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(nodoList != null){
            return nodoList.size();
        }
        return 0;
    }

    public class nodoViewHolder extends RecyclerView.ViewHolder {
        private TextView nodoTextView;
        public nodoViewHolder(@NonNull View itemView) {
            super(itemView);
            nodoTextView = itemView.findViewById(R.id.textView);
        }
    }
}

package com.example.examen_final.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.examen_final.DetalleActivity;
import com.example.examen_final.R;
import com.example.examen_final.model.Libro;

import java.util.List;

public class LibroAdapter extends RecyclerView.Adapter<LibroAdapter.PeliculaViewHolder>{

    private List<Libro>datos;

    public LibroAdapter(List<Libro>datos){
        this.datos=datos;
    }

    @NonNull
    @Override
    public PeliculaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista,parent,false);
        return new PeliculaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PeliculaViewHolder holder, int position) {

        TextView txtTitulo=holder.itemView.findViewById(R.id.textidlibrotitulo);

        Libro libro=datos.get(position);
        txtTitulo.setText(String.valueOf(libro.getTitulo()));

        ImageButton button=holder.itemView.findViewById(R.id.imageButtonidlibro);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context=v.getContext();

                Intent intent;
                intent=new Intent(context, DetalleActivity.class);

                intent.putExtra("titulo",libro.getTitulo());
                intent.putExtra("resumen",libro.getResumen());
                intent.putExtra("autor",libro.getAutor());
                intent.putExtra("fecha_publicacion",libro.getFecha_publicacion());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    class PeliculaViewHolder extends RecyclerView.ViewHolder{

        public PeliculaViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

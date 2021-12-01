package com.example.examen_final;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.examen_final.adapter.LibroAdapter;
import com.example.examen_final.model.Libro;
import com.example.examen_final.service.LibroService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Button button=findViewById(R.id.buttonRegistros);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent=new Intent(getApplicationContext(),RegistroActivity.class);
                startActivity(intent);
            }
        });


        Button button1=findViewById(R.id.buttSincronizar);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   AppDatabase db= Room.databaseBuilder(getApplicationContext()
                   ,AppDatabase.class,"examen-final").build();


                Libro libro=new Libro();

                db.peliculaDao().create(libro);

                Retrofit retrofit=new Retrofit.Builder()
                        .baseUrl("https://upn.lumenes.tk/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();



            }
        });

        Button listabut=findViewById(R.id.butidlista);
        listabut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
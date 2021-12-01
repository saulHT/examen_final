package com.example.examen_final.service;

import com.example.examen_final.model.Libro;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface LibroService {

    @GET("n00025105/libros")
    Call<List<Libro>>lista();

    @POST("n00025105/libros")
    Call<Libro> crear(@Body Libro libro);
}

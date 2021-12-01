package com.example.examen_final.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.examen_final.model.Libro;

import java.util.List;

@Dao
public interface LibroDao {
    @Query("SELECT * FROM Pelicula")
    List<Libro>lista();

    @Insert
    void create(Libro libro);
}

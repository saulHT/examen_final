package com.example.examen_final;

import androidx.room.RoomDatabase;

import com.example.examen_final.Dao.LibroDao;

public abstract class AppDatabase extends RoomDatabase {
    public abstract LibroDao peliculaDao();

}

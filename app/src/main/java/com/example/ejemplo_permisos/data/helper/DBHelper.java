package com.example.ejemplo_permisos.data.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private String crearImgs = "CREATE TABLE imgs(" +
            "idImgs INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "url VARCHAR(250) " +
            ")";

    private String crearPlayer = "CREATE TABLE players(" +
            "idPlayer INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nickNamePlayer VARCHAR(250), " +
            "idImgs INTEGER, " +
            "scorePlayer INTEGER, " +
            "FOREIGN KEY (idImgs) REFERENCES imgs(idImgs) " +
            ")";

    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(crearImgs);
        db.execSQL(crearPlayer);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS players");
        db.execSQL("DROP TABLE IF EXISTS imgs");
    }
}

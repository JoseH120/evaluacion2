package com.example.ejemplo_permisos.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ejemplo_permisos.data.helper.DBHelper;
import com.example.ejemplo_permisos.model.Imgs;
import com.example.ejemplo_permisos.model.Players;

import java.util.ArrayList;

public class DB {
    private DBHelper dbHelper;

    public DB(Context context){
        dbHelper = new DBHelper(context, "BD_prueba_image", null, 1);
    }

    public Cursor getCursorImage(){
        return dbHelper.getReadableDatabase().rawQuery("SELECT * FROM imgs", null);
    }

    public Cursor getCursorPlayer(){
        return dbHelper.getReadableDatabase().rawQuery(
        "SELECT a.idPlayer, a.nickNamePlayer, a.idImgs, a.scorePlayer FROM players a, imgs b WHERE a.idImgs = b.idImgs", null
        );
    }

    public Cursor getCursorPlayer(String idImgs){
        return dbHelper.getReadableDatabase().rawQuery(
        "SELECT a.idPlayer, a.nickNamePlayer, a.idImgs, a.scorePlayer FROM players a, imgs b WHERE a.idImgs = b.idImgs AND a.idImgs=?", new String[]{idImgs}
        );
    }

    public ArrayList<Imgs> getArrayImage(Cursor cursor){
        cursor.moveToFirst();
        ArrayList<Imgs> lstImage = new ArrayList<>();
        if(cursor != null && cursor.getCount() > 0){
            do{
                lstImage.add(new Imgs(cursor.getString(0), cursor.getString(1)));
            }while(cursor.moveToNext());
            return lstImage;
        }
        return null; // si no entra al if
    }

    public ArrayList<Players> getArrayPlayer(Cursor cursor){
        cursor.moveToFirst();
        ArrayList<Players> lstPlayer = new ArrayList<>();
        if( cursor != null && cursor.getCount() > 0){
            do{
                lstPlayer.add(new Players(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3)
                ));
            }while(cursor.moveToNext());
            return lstPlayer;
        }
        return null;
    }

    public boolean Guardar_O_ActualizarImage(Imgs image){
        ContentValues initialValues = new ContentValues();
        if(!image.getIdImg().isEmpty())
            initialValues.put("idImg", Integer.parseInt(image.getIdImg()));
            initialValues.put("url", image.getURL());
        int id = (int) dbHelper.getWritableDatabase().insertWithOnConflict(
                "imgs",
                null,
                initialValues,
                SQLiteDatabase.CONFLICT_REPLACE
        );
        return id > 0;
    }

    public boolean Guardar_O_ActualizarPlayer(Players player){
        ContentValues initialValues = new ContentValues();
        if(!player.getIdPlayer().isEmpty())
            initialValues.put("idPlayer", Integer.parseInt(player.getIdPlayer()));
            initialValues.put("nickNamePlayer", player.getNickNamePlayer());
            initialValues.put("idImgs", Integer.parseInt(player.getIdImgs()));
            initialValues.put("scorePlayer", Integer.parseInt(player.getScorePlayer()));
        int id = (int) dbHelper.getWritableDatabase().insertWithOnConflict(
            "players",
            null,
            initialValues,
            SQLiteDatabase.CONFLICT_REPLACE);

        return id > 0;
    }

    public void borrarImage(String idImg){
        dbHelper.getWritableDatabase().execSQL(String.format("delete from imgs where idImgs='%s'", idImg));
    }

    public void borrarPlayer(String idPlayer){
        dbHelper.getWritableDatabase().execSQL(String.format("delete from players where idPlayer='%s'", idPlayer));
    }
}

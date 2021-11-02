package com.example.ejemplo_permisos.data;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesConfig {
    private  SharedPreferences preferences;
    private  String NAME_FILE;
    public SharedPreferencesConfig(Context context){
        NAME_FILE = "configuration";
        preferences = context.getSharedPreferences(NAME_FILE, Context.MODE_PRIVATE);
    }

    public SharedPreferences getPreferences(){return preferences;}
}

package com.example.ejemplo_permisos.utils;

import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    public static boolean verifyEditText(EditText editText){
        if(editText.getText().toString().isEmpty()){
            editText.setError("Este campo es requerido");
            return false;
        } else if (!verifyChars(editText)) {
            editText.setError("Solo se permite letras");
            return false;
        }
        return true;
    }

    public static boolean verifyChars(EditText editText){
        Pattern ps = Pattern.compile("^[a-zA-Z]+$");
        Matcher ms = ps.matcher(editText.getText().toString());
        boolean bs = ms.matches();
        return bs;
    }
}

package com.example.ejemplo_permisos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ejemplo_permisos.data.SharedPreferencesConfig;

public class JuegoActivity extends AppCompatActivity {
    SharedPreferencesConfig preferences;
    TextView textview2, textview3, textview10;
    EditText edtrespuesta;
    Button btnenviar,btnregresar;
    String nombre, partida;
    int numero, puntaje, intentos;
    Intent intentMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        if(savedInstanceState != null){
            intentos = savedInstanceState.getInt("intentos");
        }else{
            intentos = 10;
        }

        /*if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }*/

        edtrespuesta = (EditText) findViewById(R.id.edtrespuesta);
        btnenviar = (Button) findViewById(R.id.btnenviar);
        btnregresar = (Button) findViewById(R.id.btnregresar);
        textview2 = (TextView) findViewById(R.id.textview2);
        textview3 = (TextView) findViewById(R.id.textview3);
        textview10  = (TextView) findViewById(R.id.textview10);

        preferences = new SharedPreferencesConfig(getApplicationContext());

        nombre = getIntent().getStringExtra("user");
        numero = getIntent().getIntExtra("numero", 0);
        puntaje = getIntent().getIntExtra("puntaje", 0);

        textview10.setText("Usuario: \n"+nombre);
        textview2.setText("Puntaje: \n"+puntaje);
        textview3.setText("Intentos restantes: " + intentos);
        intentMain = new Intent(JuegoActivity.this, MainActivity.class);
        btnenviar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                if(verificar()){
                    Toast.makeText(JuegoActivity.this, "Has adivinado el numero!", Toast.LENGTH_SHORT).show();
                    startActivity(intentMain);
                    finish();
                }else{
                    restar();
                    if(intentos == 0){
                        Toast.makeText(JuegoActivity.this, "!No hay mas intentos!", Toast.LENGTH_SHORT).show();
                        startActivity(intentMain);
                        finish();
                    }
                }
            }
        });

        btnregresar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Toast.makeText(JuegoActivity.this, "Has abandonado el juego", Toast.LENGTH_SHORT).show();
                startActivity(intentMain);
                finish();
            }
        });
    }

    void restar(){
        intentos -= 1;
        textview3.setText("Intentos restantes: " + intentos);
    }

    boolean verificar(){
        boolean bandera = false;
        if(!edtrespuesta.getText().toString().isEmpty()){
            if(numero == Integer.parseInt(edtrespuesta.getText().toString())){
                puntaje += intentos;
                SharedPreferences.Editor edit = preferences.getPreferences().edit();
                edit.putInt("numero", numero);
                edit.putString("partida", "nuevo");
                edit.putInt("puntaje", puntaje);
                edit.commit();
                bandera = true;
            }else if( numero > Integer.parseInt(edtrespuesta.getText().toString() )){
                edtrespuesta.setError("!El numero secreto es mayor!");
                bandera =false;
            }
            else if( numero < Integer.parseInt(edtrespuesta.getText().toString())){
                edtrespuesta.setError("!El numero secreto es menor!");
                bandera =false;
            }
        }else if(edtrespuesta.getText().toString().isEmpty()){
            edtrespuesta.setError("Campo requerido ");
            bandera = false;
        }
        return bandera;
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("intentos", intentos);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        //intentos = savedInstanceState.getInt("intentos");
    }
}
package com.example.ejemplo_permisos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ejemplo_permisos.data.SharedPreferencesConfig;

public class MainActivity extends AppCompatActivity {
    SharedPreferencesConfig preferences;
    String nombre;
    String partida;

    int numero;
    int puntaje;
    private Button btnIniciar, btnRespuesta, btnPuntaje, btnDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = new SharedPreferencesConfig(getApplicationContext());

        nombre = preferences.getPreferences().getString("user", "device");
        puntaje  = preferences.getPreferences().getInt("puntaje", 0);
        partida = preferences.getPreferences().getString("partida", "anterior");

        if(partida.equalsIgnoreCase("anterior") && savedInstanceState != null){
            numero = savedInstanceState.getInt("numero");
        }else{
            numero = (int) (Math.random() * 10 + 1);
        }

        btnIniciar = (Button) findViewById(R.id.btniniciar);
        btnRespuesta = (Button) findViewById(R.id.btnrespuesta);
        btnPuntaje = (Button) findViewById(R.id.btnpuntaje);
        btnDatos = (Button) findViewById(R.id.btndatos);

        Intent intentjuego = new Intent(MainActivity.this,JuegoActivity.class);
        btnIniciar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                intentjuego.putExtra("user", nombre);
                intentjuego.putExtra("puntaje", puntaje);
                intentjuego.putExtra("numero", numero);
                startActivity(intentjuego);
                finish();
            }
        });

        Intent intentrespuesta = new Intent(MainActivity.this,RespuestaActivity.class);
        btnRespuesta.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                intentrespuesta.putExtra("respuesta", numero);
                startActivity(intentrespuesta);
            }
        });

        Intent intentpuntaje = new Intent(MainActivity.this, PuntajeActivity.class);
        btnPuntaje.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                intentpuntaje.putExtra("puntaje", puntaje);
                intentpuntaje.putExtra("user", nombre);
                startActivity(intentpuntaje);
            }
        });

        Intent intentdatos = new Intent(MainActivity.this, DatosActivity.class);
        btnDatos.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                startActivity(intentdatos);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case  R.id.action_settings:
                startActivity(new Intent(MainActivity.this, ListadoImgsActivity.class));
                break;
        }
        return true;
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        numero = savedInstanceState.getInt("numero");
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt("numero", numero);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();
    }
}
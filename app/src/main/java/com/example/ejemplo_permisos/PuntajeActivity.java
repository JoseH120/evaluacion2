package com.example.ejemplo_permisos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class PuntajeActivity extends AppCompatActivity {
    TextView textview6;
    String nombre;
    int puntaje;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puntaje);
        nombre = getIntent().getStringExtra("user");
        puntaje = getIntent().getIntExtra("puntaje", 0);

        textview6 = (TextView) findViewById(R.id.textview6);
        textview6.setText("USUARIO:\n" + nombre + "\nPUNTAJE:\n"+ puntaje);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}
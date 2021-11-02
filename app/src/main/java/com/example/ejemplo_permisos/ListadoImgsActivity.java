package com.example.ejemplo_permisos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ejemplo_permisos.adapters.ImgsAdapter;
import com.example.ejemplo_permisos.data.DB;
import com.example.ejemplo_permisos.model.Imgs;

import java.util.ArrayList;

public class ListadoImgsActivity extends AppCompatActivity {

    private DB db;
    private ListView listview_imgs;
    private TextView tv_id, tv_url;
    private Button btagregar, btmodificar, bteliminar;
    private ArrayList<Imgs> listImgs;
    private ImgsAdapter imgsAdapter;
    private Imgs imgstmp = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_imgs);

        tv_id = (TextView) findViewById(R.id.tv_id_img);
        tv_url = (TextView) findViewById(R.id.tv_url);
        btagregar = (Button) findViewById(R.id.bt_agregar_image);
        btmodificar = (Button) findViewById(R.id.bt_modificar_players);
        bteliminar = (Button) findViewById(R.id.bt_eliminar_imgs);
        listview_imgs = (ListView) findViewById(R.id.listview_imgs);

        //
        db= new DB(ListadoImgsActivity.this);
        listImgs = db.getArrayImage(db.getCursorImage());
        if(listImgs == null){
            //
            listImgs = new ArrayList<>();
        }

        imgsAdapter = new ImgsAdapter(ListadoImgsActivity.this, listImgs);
        listview_imgs.setAdapter(imgsAdapter);

        Intent intentGuardarImagen = new Intent(ListadoImgsActivity.this, GuardarImagenActivity.class);
        btagregar.setOnClickListener(v ->{
            startActivity(intentGuardarImagen);
        });

        Intent intent_listado_players = new Intent(ListadoImgsActivity.this, ListadoPlayersActivity.class);
        btmodificar.setOnClickListener(v ->{
            intent_listado_players.putExtra("idImgs", tv_id.getText().toString());
            intent_listado_players.putExtra("url", tv_url.getText().toString());
            startActivity(intent_listado_players);
        });

        bteliminar.setOnClickListener(v ->{
            eliminar();
        });

        listview_imgs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                seleccionar(listImgs.get(position));
            }
        });

        limpiar();
    }

    private void seleccionar(Imgs image){
        imgstmp = image;
        tv_id.setText(imgstmp.getIdImg());
        tv_url.setText(imgstmp.getURL());
    }

    private void eliminar(){
        if(imgstmp != null){
            db.borrarImage(imgstmp.getIdImg());
            listImgs.remove(imgstmp);
            imgsAdapter.notifyDataSetChanged();
            imgstmp =null;
            Toast.makeText(this, "Se elimino el registro", Toast.LENGTH_SHORT).show();
            limpiar();
        }
    }

    private void limpiar(){
        tv_id.setText(null);
        tv_url.setText(null);
    }

}
package com.example.ejemplo_permisos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ejemplo_permisos.adapters.PlayersAdapter;
import com.example.ejemplo_permisos.data.DB;
import com.example.ejemplo_permisos.model.Players;

import java.util.ArrayList;

public class ListadoPlayersActivity extends AppCompatActivity {

    private DB db;
    private ListView listview_player;
    private TextView tv_id_imgs, tv_id_player, tv_url;
    private EditText edt_nick_name;
    private Button btagregar, bteliminar, btcancelar;
    private ArrayList<Players> lstPlayers;
    private PlayersAdapter playersAdapter;
    private Players playerstmp = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_players);

        tv_id_imgs  = (TextView) findViewById(R.id.tv_id_img_player);
        tv_id_player = (TextView) findViewById(R.id.tv_id_player);
        tv_url = (TextView) findViewById(R.id.tv_url_player);
        edt_nick_name = (EditText) findViewById(R.id.edtNickName);
        btagregar = (Button) findViewById(R.id.btn_agregar_player);
        btcancelar = (Button) findViewById(R.id.btn_regresar_inicio);
        bteliminar = (Button) findViewById(R.id.btn_eliminar);

        tv_id_imgs.setText(getIntent().getStringExtra("idImgs"));
        tv_url.setText(getIntent().getStringExtra("url"));
        db = new DB(getApplicationContext());
        lstPlayers = db.getArrayPlayer(db.getCursorPlayer());

        if(lstPlayers == null){
            lstPlayers = new ArrayList<>();
        }

        playersAdapter = new PlayersAdapter(ListadoPlayersActivity.this, lstPlayers);
        listview_player.setAdapter(playersAdapter);

        //
        btagregar.setOnClickListener(v->{
            //agregar();
         });

    }


}

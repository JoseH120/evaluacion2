package com.example.ejemplo_permisos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ejemplo_permisos.R;
import com.example.ejemplo_permisos.model.Players;

import java.util.List;

public class PlayersAdapter extends ArrayAdapter<Players> {
    public PlayersAdapter(@NonNull Context context, List<Players> lstPlayer){
        super(context, 0, lstPlayer);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Players player = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_player, parent, false);
        }
        TextView txvIdPlayer = convertView.findViewById(R.id.txv_id_player);
        TextView txvNickName = convertView.findViewById(R.id.txv_name_player);
        TextView txvScore = convertView.findViewById(R.id.txv_score_player);

        txvIdPlayer.setText(player.getIdPlayer());
        txvNickName.setText(player.getNickNamePlayer());
        txvScore.setText(player.getScorePlayer());

        return convertView;
    }
}

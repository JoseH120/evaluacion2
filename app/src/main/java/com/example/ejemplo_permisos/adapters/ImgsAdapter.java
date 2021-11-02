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
import com.example.ejemplo_permisos.model.Imgs;

import java.util.List;

public class ImgsAdapter extends ArrayAdapter<Imgs> {
    public ImgsAdapter(@NonNull Context context, List<Imgs> lstimgs) {
        super(context, 0, lstimgs);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Imgs image = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_imgs, parent, false);
        }

        TextView txvId = convertView.findViewById(R.id.txv_id_img);
        TextView txvURL = convertView.findViewById(R.id.txv_url);

        txvId.setText(image.getIdImg());
        txvURL.setText(image.getURL());

        return convertView;
    }
}

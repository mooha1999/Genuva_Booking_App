package com.example.weesofirebase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomArrayAdapter extends ArrayAdapter {
    CustomArrayAdapter(@NonNull Context context, int resource, @NonNull ArrayList<UserModel> objects) {
        super(context, resource, objects);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.row,parent,false);
        ImageView bandImage = view.findViewById(R.id.bandImage);
        TextView bandName = view.findViewById(R.id.bandName),
                address = view.findViewById(R.id.address),
                room = view.findViewById(R.id.room),
                date = view.findViewById(R.id.date);

        UserModel model = (UserModel) getItem(position);
        bandName.setText(model.getBandName());
        address.setText(model.getLocation());
        room.setText(model.getRoom());
        date.setText(model.getDate());
        Picasso.get().load(model.getBandImage()).into(bandImage);
        return view;
    }
}


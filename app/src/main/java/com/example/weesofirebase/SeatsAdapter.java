package com.example.weesofirebase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class SeatsAdapter extends ArrayAdapter{

    public SeatsAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Chair> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.chair,parent,false);
        TextView btn = view.findViewById(R.id.seatNum);
        Chair chair = (Chair)getItem(position);
        String num = Integer.toString(chair.getChairPosition()+1);
        btn.setText(num);
        if (chair.getChairState()>=2){
            btn.setBackgroundResource(R.drawable.ic_seat_red);
        }
        else if (chair.getChairState()==1){
            btn.setBackgroundResource(R.drawable.ic_seat_green);
        }
        else btn.setBackgroundResource(R.drawable.ic_chair);


        return view;
    }
}

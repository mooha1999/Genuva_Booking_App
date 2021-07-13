package com.example.weesofirebase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class TicketsAdapter extends ArrayAdapter {
    public TicketsAdapter(@NonNull Context context, int resource, @NonNull ArrayList<TicketsModel> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.tickets_row,parent,false);
        TextView partyName = view.findViewById(R.id.ticketPartyName),
                partyDate = view.findViewById(R.id.ticketPartyDate),
                partyPrice = view.findViewById(R.id.ticketPartyPrice),
                partySeatsNumber = view.findViewById(R.id.ticketPartySeats),
                partyCount = view.findViewById(R.id.ticketPartyCount);
        TicketsModel ticketsModel = (TicketsModel) getItem(position);
        partyName.setText(ticketsModel.getName());
        partyDate.setText(ticketsModel.getDate());
        partyPrice.setText(ticketsModel.getPrice());
        partySeatsNumber.setText(ticketsModel.getNumbers());
        partyCount.setText(ticketsModel.getCount());

        return view;
    }
}

package com.example.weesofirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Tickets extends AppCompatActivity {
    ListView ticketsList;
    ArrayList<TicketsModel> ticketsModels = new ArrayList<>();
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tickets);
        ticketsList = findViewById(R.id.ticketsList);
        setData();


    }
    private void setData(){
        ticketsModels.clear();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot shot : dataSnapshot.child("Tickets").getChildren()){
                    TicketsModel ticketsModel = shot.getValue(TicketsModel.class);
                    ticketsModels.add(ticketsModel);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

package com.example.weesofirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Seats extends AppCompatActivity {
    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
    private ArrayList<Chair> chairs = new ArrayList<>();
    private String concertID,concertLocation,concertBand;
    private GridView grid;
    private SeatsAdapter seatsAdapter;
    private Button book;
    TextView seatsNum,seatsPrice;
    private int totalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seats);
        Intent intent = getIntent();
        concertID = intent.getStringExtra("id");
        concertLocation = intent.getStringExtra("location") + " Concerts";
        concertBand = intent.getStringExtra("bandName");
        grid = findViewById(R.id.rv);
        setData();
        book = findViewById(R.id.bookBtn);
        seatsNum = findViewById(R.id.seatsNum);seatsPrice = findViewById(R.id.seatsPrice);totalPrice=0;
        chairListeners();


    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        for (int i =0;i<30;i++){
            if (chairs.get(i).getChairState()==1)
            reference.child("Concerts").child(concertLocation).child(concertID).child("Chairs").child(chairs.get(i).getChairKey()).child("chairState").setValue(0);
        }
        finish();
    }
    private void setData(){

        Sakia.sakiaDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                chairs.clear();
                for (DataSnapshot shot : dataSnapshot.child("Concerts").child(concertLocation).child(concertID).child("Chairs").getChildren()){
                    Chair chair = shot.getValue(Chair.class);
                    chairs.add(chair);
                }
                seatsAdapter = new SeatsAdapter(Seats.this,R.layout.chair,chairs);
                grid.setAdapter(seatsAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void chairListeners(){
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if (chairs.get(i).getChairState()==0) {
                    reference.child("Concerts").child(concertLocation).child(concertID)
                            .child("Chairs").child(chairs.get(i).getChairKey()).child("chairState").setValue(1);
                    setData();
                    if (seatsNum.getText().toString().equals("No seats selected")) {
                        seatsNum.setText(Integer.toString(chairs.get(i).getChairPosition() + 1));

                    }
                    else {
                        int num = chairs.get(i).getChairPosition()+1;
                        seatsNum.append(", " + num);

                    }
                    totalPrice += chairs.get(i).getChairPrice();
                    seatsPrice.setText(totalPrice + "L.E");
                }
                else if (chairs.get(i).getChairState()==1){
                    reference.child("Concerts").child(concertLocation).child(concertID)
                            .child("Chairs").child(chairs.get(i).getChairKey()).child("chairState").setValue(0);
                    setData();
                    seatsNum.setText("No seats selected");
                    seatsPrice.setText("0 L.E");
                    totalPrice=0;
                    int check = 0;
                    for (int j =0;j<30;j++){
                        if (chairs.get(j).getChairState()==1){
                            if (seatsNum.getText().toString().equals("No seats selected")){
                                seatsNum.setText(Integer.toString(chairs.get(j).getChairPosition() + 1));
                            }
                            else{
                                int num = chairs.get(j).getChairPosition()+1;
                                seatsNum.append("," + num);
                            }
                            totalPrice+=chairs.get(j).getChairPrice();
                            seatsPrice.setText(totalPrice+ " L.E");
                        }
                        else if (chairs.get(j).getChairState() != 1)
                            check++;
                    }
                    if (check==29){
                        seatsNum.setText("No seats selected");
                        seatsPrice.setText("0 L.E");
                        totalPrice=0;
                    }

                }

            }
        });

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String
                for(int i=0;i<30;i++){
                    if(chairs.get(i).getChairState()==1){
                        reference.child("Concerts").child(concertLocation).child(concertID)
                                .child("Chairs").child(chairs.get(i).getChairKey()).child("chairState").setValue(2);

                    }
                    //TicketsModel ticketsModel = new TicketsModel()
                    seatsNum.setText("No seats selected");
                    seatsPrice.setText("0 L.E");
                    totalPrice = 0;
                }
            }
        });
    }
}

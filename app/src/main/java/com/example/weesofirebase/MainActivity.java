package com.example.weesofirebase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    protected static FirebaseDatabase database = FirebaseDatabase.getInstance();
    protected static DatabaseReference reference = database.getReference();
    protected static StorageReference storageReference = FirebaseStorage.getInstance().getReference();
    private Button opera,sakia,addConcert,tickets;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        opera = findViewById(R.id.opera);
        sakia = findViewById(R.id.sakia);
        tickets = findViewById(R.id.tickets);
        tickets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Tickets.class);
                startActivity(intent);
            }
        });
        opera.setOnClickListener(this);
        sakia.setOnClickListener(this);
        addConcert = findViewById(R.id.addConcert);
        addConcert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,CreateConcert.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View view) {
        Fragment fragment = null;
        switch (view.getId()){
            case R.id.opera:
                fragment = new Opera();
                break;
            case R.id.sakia:
                fragment = new Sakia();
                break;
        }
        getSupportFragmentManager()
        .beginTransaction()
        .replace(R.id.frame_layout,fragment).commit();


    }
}

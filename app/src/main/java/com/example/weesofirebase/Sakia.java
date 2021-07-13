package com.example.weesofirebase;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Sakia extends Fragment {

    protected static DatabaseReference sakiaDatabaseReference = MainActivity.database.getReference();
    protected static ArrayList<UserModel> sakiaArrayList = new ArrayList<>();
    private ListView sakiaList;
    private CustomArrayAdapter adapter;

    public Sakia() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sakia,container,false);
        sakiaList = view.findViewById(R.id.sakiaList);
        setData();
        sakiaList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Opera.concert = sakiaArrayList.get(i);
                Intent intent = new Intent(getActivity(),Seats.class);
                intent.putExtra("location",sakiaArrayList.get(i).getLocation());
                intent.putExtra("id",sakiaArrayList.get(i).getId());
                intent.putExtra("bandName",sakiaArrayList.get(i).getBandName());
                startActivity(intent);
            }
        });

        return view;
    }
    private void setData(){
        sakiaDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                sakiaArrayList.clear();

                for (DataSnapshot shot : dataSnapshot.child("Concerts").child("Sakia Concerts").getChildren()){
                    UserModel userModel = shot.getValue(UserModel.class);

                    sakiaArrayList.add(userModel);

                }
                adapter = new CustomArrayAdapter(getContext(),R.layout.row,sakiaArrayList);

                sakiaList.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}

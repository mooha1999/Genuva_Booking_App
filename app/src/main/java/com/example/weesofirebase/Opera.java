package com.example.weesofirebase;

import android.content.Intent;
import android.graphics.Path;
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
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Opera extends Fragment {

    protected static DatabaseReference operaDatabaseReference = MainActivity.database.getReference();
    protected static ArrayList<UserModel> OperaArrayList = new ArrayList<>();
    protected static UserModel concert;
    private ListView operaList;
    private CustomArrayAdapter adapter;

    public Opera() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_opera, container, false);
        operaList = view.findViewById(R.id.operaList);
        setData();
        operaList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                concert = OperaArrayList.get(i);
                Intent intent = new Intent(getActivity(),Seats.class);
                intent.putExtra("location",OperaArrayList.get(i).getLocation());
                intent.putExtra("id",OperaArrayList.get(i).getId());
                intent.putExtra("bandName",OperaArrayList.get(i).getBandName());
                startActivity(intent);
            }
        });

        return view;
    }
    private void setData(){
        operaDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                OperaArrayList.clear();

                for (DataSnapshot shot : dataSnapshot.child("Concerts").child("Opera Concerts").getChildren()){
                    UserModel userModel = shot.getValue(UserModel.class);

                    OperaArrayList.add(userModel);

                }
                adapter = new CustomArrayAdapter(getContext(),R.layout.row,OperaArrayList);
                operaList.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}

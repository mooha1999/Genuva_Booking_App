package com.example.weesofirebase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.net.Uri;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.UploadTask;

public class CreateConcert extends AppCompatActivity {

    private EditText bandName,date;
    private RadioGroup location,room;
    private RadioButton room1,room2,room3,room4,chooseOpera,chooseSaqia;
    private ImageView bandImage;
    private Button submit,setImage;
    private String imageUri;
    private Chair[] chairs = new Chair[30];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_concert);
        initialisation();
        selectImage();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check();
            }
        });
    }
    protected void selectImage(){
        setImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK);
                pickPhoto.setType("image/*");
                startActivityForResult(pickPhoto,10);
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10 && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            bandImage.setImageURI(uri);
            uploadImage(uri);
        }
    }
    protected void uploadImage(Uri uri){
        final String key = MainActivity.reference.push().getKey();
        MainActivity.storageReference.child("Concerts").child(key).putFile(uri)
                .addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(CreateConcert.this,"Successful_1",Toast.LENGTH_LONG).show();
                            MainActivity.storageReference.child("Concerts").child(key).getDownloadUrl()
                                    .addOnCompleteListener(new OnCompleteListener<Uri>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Uri> task) {
                                            imageUri = task.getResult().toString();
                                            Toast.makeText(CreateConcert.this,"Successful_2",Toast.LENGTH_LONG).show();
                                        }
                                    });
                        }
                    }
                });
    }
    protected void initialisation(){
        bandName = findViewById(R.id.setBandName);
        date = findViewById(R.id.setBandDate);
        location = findViewById(R.id.location);
        room = findViewById(R.id.room);
        room1 = findViewById(R.id.room1);room2 = findViewById(R.id.room2);room3 = findViewById(R.id.room3);room4 = findViewById(R.id.room4);
        chooseOpera = findViewById(R.id.chooseOpera);
        chooseSaqia = findViewById(R.id.chooseSakia);
        bandImage = findViewById(R.id.bandImage);
        submit = findViewById(R.id.submit);
        setImage = findViewById(R.id.setBandImage);
    }
    protected void check(){
        bandName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (bandName.getText().toString().length() <= 3){
                    Toast.makeText(CreateConcert.this,"Band name is too short",Toast.LENGTH_LONG).show();
                    submit.setClickable(false);
                }
                else
                    submit.setClickable(true);


            }
        });
        String BandName = bandName.getText().toString(),
               BandDate = date.getText().toString(),
                Room = "",Location,id,chairID;

        //Room Radio Group
        int checkedId = room.getCheckedRadioButtonId();
        if (checkedId == room1.getId()){
            Room = "Room 1";
        }
        else if (checkedId == room2.getId()){
            Room = "Room 2";
        }
        else if (checkedId == room3.getId()){
            Room = "Room 3";
        }
        else if (checkedId == room4.getId()){
            Room = "Room 4";
        }
        //End of Room

        //Location Radio Group
        checkedId = location.getCheckedRadioButtonId();
        if (checkedId == chooseOpera.getId()){
            Location = chooseOpera.getText().toString();
            id = Opera.operaDatabaseReference.child("Concerts").child("Opera Concerts").push().getKey();

            UserModel OperaUserModel = new UserModel(imageUri, BandName,BandDate,Location,Room,id);
            /*for (int i = 0;i<30; i++){
                String chairID = Opera.operaDatabaseReference.child("Concerts").child("Opera Concerts").child(id).push().getKey();
                OperaUserModel.setChairs(chairID,i);
            }*/
            Opera.operaDatabaseReference.child("Concerts").child("Opera Concerts").child(id).setValue(OperaUserModel);
            for (int i =0;i<30;i++){
                chairID = Opera.operaDatabaseReference.child("Concerts").child("Opera Concerts").child(id).child("Chairs").push().getKey();
                Chair chair = new Chair(chairID,i);
                Opera.operaDatabaseReference.child("Concerts").child("Opera Concerts").child(id).child("Chairs").child(chairID).setValue(chair);
            }


        }

        else if (checkedId == chooseSaqia.getId()){

            Location = chooseSaqia.getText().toString();
            id = Sakia.sakiaDatabaseReference.child("Concerts").child("Sakia Concerts").push().getKey();
            UserModel SakiaUserModel = new UserModel(imageUri, BandName,BandDate,Location,Room,id);
            Sakia.sakiaDatabaseReference.child("Concerts").child("Sakia Concerts").child(id).setValue(SakiaUserModel);
            for (int i =0;i<30;i++){
                chairID = Opera.operaDatabaseReference.child("Concerts").child("Sakia Concerts").child(id).child("Chairs").push().getKey();
                Chair chair = new Chair(chairID,i);
                Opera.operaDatabaseReference.child("Concerts").child("Sakia Concerts").child(id).child("Chairs").child(chairID).setValue(chair);
            }

        }
        //End of Location
    }


}

package com.example.klnresearch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.UUID;

public class test_select extends AppCompatActivity {

    private TextInputEditText pname,pspeed,ptime,ptemp;
    private Button saveBtn,showBtn;

    private FirebaseFirestore db;

    private String uTitle,uId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_test_show2);

        pname = findViewById(R.id.name);
        pspeed = findViewById(R.id.speed);
        ptime = findViewById(R.id.time);
        ptemp = findViewById(R.id.temp);
        saveBtn = findViewById(R.id.save);
        showBtn = findViewById(R.id.show_all);

        db = FirebaseFirestore.getInstance();

       Bundle bundle = getIntent().getExtras();
       if (bundle != null){
           saveBtn.setText("Test Update");
           uTitle = bundle.getString("uTitle");
           uId = bundle.getString("uId");
           pname.setText(uTitle);

       }else{
           saveBtn.setText("Test Save");
       }
       showBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               startActivity(new Intent(test_select.this,test_show.class                                                                                                                                                                                                                                                       ));
           }
       });
       saveBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String name = pname.getText().toString();
               String time = ptime.getText().toString();
               String temp = ptemp.getText().toString();
               String speed = pspeed.getText().toString();

               Bundle bundle1 = getIntent().getExtras();
               if (bundle1 != null){
                   String id =uId;
                   updateToFirebaseStore(id,name,time,temp,speed);
               }else {
                   String id = UUID.randomUUID().toString();
                   saveToFireStore(id,name,time,temp,speed);
               }
           }


       });


    }
    private void saveToFireStore(String id, String name, String time, String temp, String speed) {
        if (!name.isEmpty() && !speed.isEmpty()){
            HashMap<String, Object> map = new HashMap<>();
            map.put("id",id);
            map.put("name",name);
            map.put("time",time);
            map.put("temp",temp);
            map.put("speed",speed);

            db.collection("Documents").document(id).set(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(test_select.this, "Test Saved!!", Toast.LENGTH_SHORT).show();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(test_select.this, "Failed!!", Toast.LENGTH_SHORT).show();
                }
            });
        }else {
            Toast.makeText(test_select.this,"Empty Fields not Allowed",Toast.LENGTH_SHORT).show();
        }
    }

    private void updateToFirebaseStore(String id, String name, String time, String temp, String speed) {
        db.collection("Documents").document(id).update("name",name,"time",time,"temp",temp,"speed",speed)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(test_select.this, "Data Updated!!", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(test_select.this, "Error: "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(test_select.this,e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


}
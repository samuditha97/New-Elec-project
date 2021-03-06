package com.example.klnresearch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.UUID;

public class ViewTest extends AppCompatActivity {
    RecyclerView recyclerView;
    ViewAdapterModel viewAdapterModel;
    ArrayList<SenderModel> list;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private InputStream mmInStream;
    private BluetoothSocket mBTSocket;
    private OutputStream mmOutStream;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_test);



        recyclerView =findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("MCDataBase");
        new ItemTouchHelper(itemTouchHelper).attachToRecyclerView(recyclerView);
        list=new ArrayList<>();
        viewAdapterModel=new ViewAdapterModel(this,list, mBTSocket, mmInStream, mmOutStream);
        recyclerView.setAdapter(viewAdapterModel);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot db:snapshot.getChildren()){
                    SenderModel senderModel = db.getValue(SenderModel.class);
                    list.add(senderModel);

                }
                viewAdapterModel.notifyDataSetChanged();
                Toast.makeText(ViewTest.this, "Successfull", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ViewTest.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    ItemTouchHelper.SimpleCallback itemTouchHelper =
            new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {
                @Override
                public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                    return false;
                }

                @Override
                public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                    list.remove(viewHolder.getAbsoluteAdapterPosition());
                    viewAdapterModel.deleteData(list);


                }

                };
            }
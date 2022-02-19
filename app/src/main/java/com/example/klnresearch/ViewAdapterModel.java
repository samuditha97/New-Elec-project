package com.example.klnresearch;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.RestrictionEntry;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewAdapterModel extends RecyclerView.Adapter<ViewAdapterModel.MyViewHolder> {

    Context context;
    ArrayList<SenderModel> list;



    public ViewAdapterModel(Context context, ArrayList<SenderModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewAdapterModel.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.viewmodel,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        SenderModel senderModel=list.get(position);
        holder.name.setText(senderModel.getNamesend().toString());
        holder.speed.setText(senderModel.getSpeedsend().toString());
        holder.time.setText(senderModel.getTimesend().toString());
        holder.temp.setText(senderModel.getTempsend().toString());

      holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Display a toast message to user
                //display the title of the test
                Toast.makeText(context, "clicked:"+senderModel.getNamesend(), Toast.LENGTH_SHORT).show();
                new AlertDialog.Builder(context)
                        .setMessage("Test: "+senderModel.getNamesend())
                        .setTitle("Do you want to run?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.i("Result","Success");
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.i("Result","Success");
                            }
                        })
                        .show();
            }

        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void deleteData(ArrayList<SenderModel> list ) {
    }



    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,speed,time,temp;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameValue);
            speed = itemView.findViewById(R.id.speedValue);
            time = itemView.findViewById(R.id.timeValue);
            temp = itemView.findViewById(R.id.tempValue);
        }
    }

}

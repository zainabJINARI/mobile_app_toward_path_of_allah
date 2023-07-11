package com.example.tasbih;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter_Model extends RecyclerView.Adapter<Adapter_Model.MyViewHolder> {
    Context context;
    static  ArrayList<Model> models;
    static  int currentPosition = -1;
    static  int currentCount = 0;
    public Adapter_Model(Context context , ArrayList<Model> models){
        this.context=context;
        this.models= models;
    }


    @NonNull
    @Override
    public Adapter_Model.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //this is where you inflate the layout (giving a look to our rows)
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dikr,parent,false);
        return new Adapter_Model.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_Model.MyViewHolder holder, int position) {
      //assign values to the views  we created in the recycler view dikr layout file
        // based on the position of the recycler view
        holder.title.setText(models.get(position).getTitle());
        holder.source.setText(models.get(position).getSource());
        holder.counter.setText(String.valueOf(models.get(position).getCounter()));

        // check if the current position is equal to the current item position
        // and set the counter to the current count
        if (currentPosition == position) {
            holder.counter.setText(String.valueOf(currentCount));
        } else {
            holder.counter.setText(String.valueOf(0));
            holder.counter.setText(String.valueOf(models.get(position).getCounter()));
        }

    }

    @Override
    public int getItemCount() {

        //the recyvler view just wants to know the number of items you want displayed
        return models.size();
    }
    public static class MyViewHolder extends  RecyclerView.ViewHolder{
        //grabbing the views from our dikr file l
        //kinda like the on create method
        TextView title;

        TextView source;
        Button counter;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title= itemView.findViewById(R.id.title);
            source = itemView.findViewById(R.id.source);
            counter = itemView.findViewById(R.id.counter);
            counter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // get the current position of the ViewHolder
                    int position = getAdapterPosition();
                    //Toast.makeText(v.getContext(), String.valueOf(position), Toast.LENGTH_SHORT).show();

                    // check if the current position is not equal to the saved position
                    // and reset the current count to 0
                    if (position != currentPosition) {
                        currentCount = 0;
                    }

                    // check if the current count is less than the counter value of the current item
                    if (currentCount < models.get(position).getCounter()) {
                        // increment the count
                        currentCount++;
                        // show the updated count in a toast message
                        //  Toast.makeText(v.getContext(), "Clicked: " + currentCount, Toast.LENGTH_SHORT).show();
                        // save the current position
                        currentPosition = position;
                        // update the counter text
                        counter.setText(String.valueOf(currentCount));
                    }
                }
            });
        }


    }
}

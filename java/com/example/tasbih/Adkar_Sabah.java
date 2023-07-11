package com.example.tasbih;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class Adkar_Sabah extends AppCompatActivity {
    private ArrayList<Model> model = new ArrayList<>();
    private ArrayList<String> id, dikr, source;
    private  ArrayList<Integer> nbrDik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adkar_sabah);
        RecyclerView recyclerView = findViewById(R.id.recycleView);
        id = new ArrayList<>();
        dikr= new ArrayList<>();
        source= new ArrayList<>();
        nbrDik = new ArrayList<>();
        setModel();
        Adapter_Model adapter = new Adapter_Model(this,model);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    private void setModel(){
        storeDataInArrays();
        for(int i = 0; i<id.size(); i++){
            model.add(new Model(nbrDik.get(i),dikr.get(i),source.get(i)));
        }
    }
    public void storeDataInArrays(){
        Cursor cursor =MainActivity.getMyDB().readAdkarSabah();
        if(cursor.getCount() ==0){
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                id.add(cursor.getString(0));
                dikr.add(cursor.getString(1));
                nbrDik.add(cursor.getInt(2));
                source.add(cursor.getString(4));

            }
        }
    }
}
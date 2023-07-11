package com.example.tasbih;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class Adkar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adkar);
    }
    public void adkarnawm(View view){
        Intent intent = new Intent(this,Adkar_Nawm.class);
        startActivity(intent);
    }
    public void adkarlmassa(View view){
        Intent intent = new Intent(this,Adkar_Massae.class);
        startActivity(intent);
    }
    public void adkarsabah(View view){
        Intent intent = new Intent(this,Adkar_Sabah.class);
        startActivity(intent);
    }
}
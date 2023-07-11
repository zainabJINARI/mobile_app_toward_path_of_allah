package com.example.tasbih;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class tsbih extends AppCompatActivity {
    int counter = 0;
    TextView nbrtsbi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tsbih);
    }


    public void reset(View view) {
        counter = 0;
        nbrtsbi = findViewById(R.id.nbrTasb);
        nbrtsbi.setText("'"+String.valueOf(counter)+"'");
    }

    public void start(View view) {
        Button btn = findViewById(R.id.reset);
        Button btn2 = findViewById(R.id.btntasb);
        btn.setVisibility(View.VISIBLE);
        btn2.setVisibility(View.GONE);
        System.out.println(counter);
        nbrtsbi = findViewById(R.id.nbrTasb);
        nbrtsbi.setText(String.valueOf(counter));
        View myView = findViewById(R.id.interfac);
        Toast.makeText(tsbih.this, "إضغط على أي مكان في الشاشة", Toast.LENGTH_SHORT).show();
        myView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter=counter+1;
                nbrtsbi = findViewById(R.id.nbrTasb);
                nbrtsbi.setText(String.valueOf(counter));

            }


        });


    }

}
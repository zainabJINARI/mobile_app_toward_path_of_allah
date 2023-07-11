package com.example.tasbih;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Salat_Takyin extends AppCompatActivity {
    int fajr,dohr,asr,maghrib,ichae;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salat_takyin);
    }
    public void onRadioButtonClick(){
        RadioButton radioButtonFajer;
        RadioButton radioButtonDoher;
        RadioButton radioButtonAser;
        RadioButton radioButtonMaghrib;
        RadioButton radioButtonIchae;
        //fajr
        RadioGroup radioGrpf = findViewById(R.id.grpFajr);
        radioButtonFajer = findViewById(radioGrpf.getCheckedRadioButtonId());
        if(radioButtonFajer.getText().equals("في الوقت")){ fajr = 1;}else{fajr=0;}
        //dohr
        RadioGroup radioGrpd = findViewById(R.id.grpDOHR);
        radioButtonDoher = findViewById(radioGrpd.getCheckedRadioButtonId());
        if(radioButtonDoher.getText().equals("في الوقت")){ dohr = 1;}else{dohr=0;}
        //Asr
        RadioGroup radioGrpA = findViewById(R.id.grpASR);
        radioButtonAser = findViewById(radioGrpA.getCheckedRadioButtonId());
        if(radioButtonAser.getText().equals("في الوقت")){ asr = 1;}else{asr=0;}
        //maghrib
        RadioGroup radioGrpM = findViewById(R.id.grpMAGHRIB);
        radioButtonMaghrib = findViewById(radioGrpM.getCheckedRadioButtonId());
        if(radioButtonMaghrib.getText().equals("في الوقت")){ maghrib = 1;}else{maghrib=0;}
        //ichae
        RadioGroup radioGrpI = findViewById(R.id.grpICHAE);
        radioButtonIchae = findViewById(radioGrpI.getCheckedRadioButtonId());
        if(radioButtonIchae.getText().equals("في الوقت")){ ichae = 1;}else{ichae=0;}

    }
    public void save(View view){
        onRadioButtonClick();
        if(!MainActivity.getMyDB().idAdded()) {
            MainActivity.getMyDB().addProgress(fajr, dohr, asr, maghrib, ichae);
        }else{
            Toast.makeText(this, "progress for today already added", Toast.LENGTH_SHORT).show();
        }
    }
    public void progressActivity(View view){
        Intent intent = new Intent(this,Progress_Activity.class);
        startActivity(intent);
    }


}
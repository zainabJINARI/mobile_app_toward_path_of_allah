package com.example.tasbih;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class Progress_Activity extends AppCompatActivity {
    private ArrayList<Float> dayScore;
    private  ArrayList<Date> dates;
    BarChart barChart;
    private ArrayList<String> labelnames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        dayScore = new ArrayList<>();
        dates = new ArrayList<>();
        barChart=findViewById(R.id.bar_chart);
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        labelnames = new ArrayList<>();
        try {
            storeDataInArrays();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
       // for(float val: dayScore){
            for(int i=0;i<dayScore.size();i++) {

                BarEntry barEntry = new BarEntry(i, dayScore.get(i));
                barEntries.add(barEntry);
                labelnames.add("Day"+(i+1));
            }
       // }
        BarDataSet barDataSet = new BarDataSet(barEntries,"Salat progress");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        barDataSet.setValueTextSize(15f);
        barChart.setData(new BarData(barDataSet));
        barChart.animateY(2000);
        barChart.getDescription().setText("Salat Progress for last 7 days");
        barChart.getDescription().setTextColor(Color.WHITE);
        barChart.getDescription().setTypeface(Typeface.DEFAULT_BOLD);
        XAxis xaxis = barChart.getXAxis();
        YAxis yAxis = barChart.getAxisLeft();
        yAxis.setAxisMinimum(0f);
        yAxis.setAxisMaximum(100f);
        yAxis.setGranularity(20f);
        yAxis.setTextColor(Color.WHITE);
        xaxis.setTextColor(Color.WHITE);
        xaxis.setValueFormatter(
                new IndexAxisValueFormatter(labelnames)
        );
        xaxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xaxis.setDrawGridLines(false);
        xaxis.setDrawAxisLine(false);
        xaxis.setGranularity(1f);
        xaxis.setLabelCount(labelnames.size());
        barChart.invalidate();

    }
    public void storeDataInArrays() throws ParseException {
        int counter = 1;
        Cursor cursor =MainActivity.getMyDB().readWeekProgress();
        if(cursor.getCount() ==0){
            Toast.makeText(this, "No progress", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                if(counter<=7){


                    float score = (((cursor.getInt(2)+cursor.getInt(3)+cursor.getInt(4)+cursor.getInt(5)+cursor.getInt(6)))*100)/5;
                    dayScore.add(score);
                    System.out.println(((cursor.getInt(2)+cursor.getInt(3)+cursor.getInt(4)+cursor.getInt(5)+cursor.getInt(6))/5)*100);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    dates.add(dateFormat.parse(cursor.getString(1)));
                }
                counter +=1;
            }


        }
    }


}
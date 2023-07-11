package com.example.tasbih;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;


import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    private int counter = 0;
    private TextView nbrtsbi;

    private static MydatabaseHelper myDB;

    public static MydatabaseHelper getMyDB() {
        return myDB;
    }

    private ArrayList<String> id, aya, source;
    NotificationChannel channel;
    NotificationManager manager;
    NotificationManager man;
    NotificationChannel chann;
    private static final String TAG = "AdkarWorkManager";
    String CHANNEL_ID = "sign";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            channel = new NotificationChannel("reminder", "My notification", NotificationManager.IMPORTANCE_DEFAULT);
            manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }


        myDB = new MydatabaseHelper(MainActivity.this);
        id = new ArrayList<>();
        aya = new ArrayList<>();
        source = new ArrayList<>();
        // Create the periodic work request to show the notification every day at 9am
        PeriodicWorkRequest notificationRequest = new PeriodicWorkRequest.Builder(NotificationWorker.class, 1, TimeUnit.DAYS)
                .setInitialDelay(getInitialDelay(), TimeUnit.MILLISECONDS)
                .build();

        // Schedule the work request
        WorkManager workManager = WorkManager.getInstance(this);
        workManager.enqueueUniquePeriodicWork(TAG, ExistingPeriodicWorkPolicy.REPLACE, notificationRequest);

    }

    public void adkar(View view) {
        Intent intent = new Intent(this, Adkar.class
        );
        startActivity(intent);

    }

    public void salat(View view) {
        Intent intent = new Intent(this, Salat.class);
        startActivity(intent);
    }

    public void tasbih(View view) {
        Intent intent = new Intent(this, tsbih.class);
        startActivity(intent);
    }

    public void sign(View view) {
        Toast.makeText(this, "clicked", Toast.LENGTH_SHORT).show();
        storeDataInArrays();
        Random rand = new Random();
        int randomIndex = rand.nextInt(id.size()) + 1;
        String ayat = aya.get(randomIndex);
        String sourceT = source.get(randomIndex);
        createNotificationChannel();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "sign");
        builder.setContentTitle(sourceT);
        builder.setContentText(ayat);
        builder.setSmallIcon(R.drawable.sign);
        builder.setStyle(new NotificationCompat.BigTextStyle().bigText(ayat));
        builder.setPriority(NotificationCompat.PRIORITY_HIGH);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        int notificationId = (int) System.currentTimeMillis();
        // notificationId is a unique int for each notification that you must define
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        notificationManager.notify(notificationId, builder.build());




    }

    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() ==0){
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                id.add(cursor.getString(0));
                aya.add(cursor.getString(1));
                source.add(cursor.getString(2));

            }
        }
    }
    private long getInitialDelay() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 7);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        long initialDelay = calendar.getTimeInMillis() - System.currentTimeMillis();
        if (initialDelay < 0) {
            initialDelay += AlarmManager.INTERVAL_DAY;
        }
        return initialDelay;
    }
    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Sign";
            String description ="Notification sign frop quoran";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


}

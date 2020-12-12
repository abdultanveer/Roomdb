package com.next.roomdb;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.next.roomdb.receivers.SendSmsReceiver;

public class SmsSchedulerActivity extends AppCompatActivity {
    AlarmManager alarmManager ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_scheduler);
         alarmManager =
                (AlarmManager) getSystemService(ALARM_SERVICE);
       // Toast.makeText(this, "sending birthday sms to sunil", Toast.LENGTH_SHORT).show();


    }

    public void scheduleSms(View view) {
        Intent alarmIntent = new Intent(SmsSchedulerActivity.this, SendSmsReceiver.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,345,alarmIntent,PendingIntent.FLAG_UPDATE_CURRENT);
                //.getActivity(this,123,alarmIntent, PendingIntent.FLAG_ONE_SHOT);
        int oneMinutesinMillis = 1*60*1000;
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+oneMinutesinMillis,pendingIntent);

    }


}
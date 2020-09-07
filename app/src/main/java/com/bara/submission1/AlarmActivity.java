package com.bara.submission1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;

import com.bara.submission1.reminder.AlarmReceiver;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AlarmActivity extends AppCompatActivity {

    private AlarmReceiver alarmReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
    }

    public void setReminder(View view) {
        alarmReceiver = new AlarmReceiver();
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 9);
        c.set(Calendar.MINUTE, 0);
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        String time = dateFormat.format(c.getTime());
        alarmReceiver.setDailyAlarm(this, time);

    }

    public void cancelReminder(View view) {
        alarmReceiver = new AlarmReceiver();
        alarmReceiver.cancelAlarm(this);
    }
}
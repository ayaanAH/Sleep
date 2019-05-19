package com.example.sleep;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class sleepDepth extends AppCompatActivity
{

    EditText weekDaysSleep, weekEndsSleep;
    TextView sleepDepth;
    private static final String CHANNEL_ID = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep_depth);
        weekDaysSleep=findViewById(R.id.weekdays);
        weekEndsSleep=findViewById(R.id.weekends);
        sleepDepth=findViewById(R.id.sleepdepth);
        createNotificationChannel();

    }


    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


    protected int checkDepth()
    {
        String wDays, wEnds;
        int requiredSleep=56, weekdays, weekends;
        wDays=weekDaysSleep.getText().toString();
        wEnds=weekEndsSleep.getText().toString();
        weekdays=Integer.parseInt(wDays);
        weekends=Integer.parseInt(wEnds);
        int total = totalDepth(requiredSleep,weekdays,weekends);

        return total;
    }

    protected int totalDepth(int reqSleep,int wDays, int wEnds)
    {
        int totalSleep = (wDays*5)+(wEnds*2);
        int totalDepth = reqSleep - totalSleep;
        Log.i("DEPTH : ", String.valueOf(totalDepth));
        return totalDepth;
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void checkResults(View view)
    {
        int result;
        String msg="";
        result = checkDepth();
        if (result>0)
            msg="You have slept "+result+"hrs less than recommended";
        else if (result<-5)
            msg = "GET UP! You are getting lazier everyday.You have slept "+result+" hours more than recommended";
        else
            msg="You have had enough sleep";
        sleepDepth.setText(msg);
        sleepDepth.setTextColor(Color.RED);
        sleepDepth.setBackgroundResource(R.drawable.border);
        NotifyDepth(msg);
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void NotifyDepth(String msg)
    {


        Bitmap profilePicture = BitmapFactory.decodeResource(this.getResources(), R.drawable.sleepy);

        Bitmap bigPicture = BitmapFactory.decodeResource(this.getResources(), R.drawable.sleepyhead);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        builder.setSmallIcon(R.drawable.sleepy)
        .setLargeIcon(profilePicture);

        NotificationCompat.BigPictureStyle style = new NotificationCompat.BigPictureStyle();
        style.bigPicture(bigPicture);
        style.setBigContentTitle("Get up lazy");
        style.setSummaryText(msg);
        style.bigLargeIcon(profilePicture);
        builder.setStyle(style);

        Notification notification = builder.build();
        NotificationManagerCompat.from(this).notify(4, notification);


    }
}

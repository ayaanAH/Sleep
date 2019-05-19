package com.example.sleep;

import android.annotation.TargetApi;
import android.app.Notification;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep_depth);
        weekDaysSleep=findViewById(R.id.weekdays);
        weekEndsSleep=findViewById(R.id.weekends);
        sleepDepth=findViewById(R.id.sleepdepth);


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
            msg="You have enough sleep";
        sleepDepth.setText(msg);
        sleepDepth.setTextColor(Color.RED);
        sleepDepth.setBackgroundResource(R.drawable.border);
        NotifyDepth(msg);
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void NotifyDepth(String msg)
    {


        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.sleepy);

        Bitmap profilePicture = BitmapFactory.decodeResource(
                this.getResources(),
                R.drawable.sleepyhead
        );

        Bitmap bigPicture = BitmapFactory.decodeResource(
                this.getResources(),
                R.drawable.sleepyhead
        );

        NotificationCompat.BigPictureStyle style = new NotificationCompat.BigPictureStyle();
        style.bigPicture(bigPicture);
        style.setBigContentTitle("Get up lazy");
        style.setSummaryText(msg);
        style.bigLargeIcon(profilePicture);
        builder.setStyle(style);

        Notification notification = builder.build();
        NotificationManagerCompat.from(this).notify(0 + 4, notification);


    }
}

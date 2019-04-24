package com.example.sleep;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
        return totalDepth;
    }


    public void checkResults(View view)
    {
        int result;
        String msg="";
        result = checkDepth();
        if (result>0)
            msg="You have slept "+result+"hrs less than recommended";
        else
            msg="You have enough sleep";
        sleepDepth.setText(msg);
        sleepDepth.setTextColor(Color.RED);
        sleepDepth.setBackgroundResource(R.drawable.border);
    }
}

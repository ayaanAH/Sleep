package com.example.sleep;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class emailAssign extends Activity
{

    private static final String CHANNEL_ID = "2";
    EditText fName, lName;
    TextView email;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_assign);
        fName=findViewById(R.id.fname);
        lName=findViewById(R.id.lname);
        email=findViewById(R.id.results);
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


    public void generateEmail(View view)
    {

        String fname, lname, Email;
        fname=fName.getText().toString();
        lname=lName.getText().toString();
        Email="EMAIL : \n\n"+fname+"."+lname+"@gmail.com";
        if( !TextUtils.isEmpty(fName.getText()) && !TextUtils.isEmpty(lName.getText()))
        {
            email.setText(Email);
            email.setBackgroundResource(R.drawable.border);
            email.setTextColor(Color.BLACK);
            NotifyEmail(Email);
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Enter all fields!",Toast.LENGTH_SHORT).show();
        }
    }

    public void NotifyEmail(String msg)
    {
        Bitmap mailIcon = BitmapFactory.decodeResource(getResources(),R.drawable.mailgen);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,CHANNEL_ID)
                .setSmallIcon(R.drawable.mailgen)
                .setLargeIcon(mailIcon)
                .setContentTitle("Your New Email is")
                .setContentText(msg+"Because you must create the notification channel before posting any notifications on Android 8.0 and higher, you should execute this code as soon as your app starts. It's safe to call this repeatedly because creating an existing notification channel performs no operation.")
//                .setStyle(new NotificationCompat.BigTextStyle()
//                        .bigText(msg+"Because you must create the notification channel before posting any notifications on Android 8.0 and higher, you should execute this code as soon as your app starts. It's safe to call this repeatedly because creating an existing notification channel performs no operation."))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        Notification notification = builder.build();
        NotificationManagerCompat.from(this).notify(4, notification);


    }

}

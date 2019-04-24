package com.example.sleep;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class emailAssign extends Activity
{

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
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Enter all fields!",Toast.LENGTH_SHORT).show();
        }
    }
}

package com.example.sleep;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
{

    Button goSleepDepth,goEmailGenerate, goVoteDrink;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goSleepDepth=findViewById(R.id.sleepdepth);
        goEmailGenerate=findViewById(R.id.emailGenerate);
        goVoteDrink=findViewById(R.id.voteDrink);
    }

    public void sleepDepth(View view)
    {
        Intent sleep=new Intent(getApplicationContext(), sleepDepth.class);
        startActivity(sleep);
    }

    public void emailAssign(View view)
    {
        Intent email=new Intent(getApplicationContext(), emailAssign.class);
        startActivity(email);
    }

    public void drinksVote(View view)
    {
        Intent vote=new Intent(getApplicationContext(), VotingSystem.class);
        startActivity(vote);
    }
}

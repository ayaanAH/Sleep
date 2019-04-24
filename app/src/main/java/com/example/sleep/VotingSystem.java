package com.example.sleep;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class VotingSystem extends AppCompatActivity
{
    int pepsiVote=0, cokeVote=0;
    ImageView pepsiImg, cokeImg;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voting_system);
        pepsiImg=findViewById(R.id.pepsi);
        cokeImg=findViewById(R.id.coke);
        result=findViewById(R.id.winner);
    }

    public void votePepsi(View view)
    {
         pepsiVote++;
    }

    public void voteCoke(View view)
    {
        cokeVote++;
    }

    public void showResults(View view)
    {
        String winner;
        if(pepsiVote>cokeVote)
            winner="Winner: PEPSI";
        else if (pepsiVote==cokeVote)
            winner="Its a Draw.";
        else
            winner="Winner: COKE";
        result.setText(winner);
        result.setBackgroundResource(R.drawable.border);
    }
}

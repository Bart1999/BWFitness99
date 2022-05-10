package com.example.bwfitness;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.bwfitness.databinding.ActivityOverallBinding;

public class OverallActivity extends NavDrawerBaseActivity {

    ActivityOverallBinding activityOverallBinding;
    Button view_beginner,view_novice,view_intermediate,view_advanced,view_elite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityOverallBinding = activityOverallBinding.inflate(getLayoutInflater());
        setContentView(activityOverallBinding.getRoot());
        allocateActivityTitle("Overall Workout Advice");

        ActionBar actionBar = getSupportActionBar();

        view_beginner = findViewById(R.id.view_beginner);
        view_novice = findViewById(R.id.view_novice);
        view_intermediate = findViewById(R.id.view_intermediate);
        view_advanced = findViewById(R.id.view_advanced);
        view_elite = findViewById(R.id.view_elite);

        view_beginner =(Button) findViewById(R.id.view_beginner);
        view_beginner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BeginnerActivity();
            }
        });

        view_novice =(Button) findViewById(R.id.view_novice);
        view_novice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NoviceActivity();
            }
        });

        view_intermediate =(Button) findViewById(R.id.view_intermediate);
        view_intermediate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntermediateActivity();
            }
        });

        view_advanced =(Button) findViewById(R.id.view_advanced);
        view_advanced.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdvancedActivity();
            }
        });

        view_elite =(Button) findViewById(R.id.view_elite);
        view_elite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EliteActivity();
            }
        });
    }

    public void BeginnerActivity(){
        Intent b = new Intent(this,BeginnerActivity.class);
        startActivity(b);
    }
    public void NoviceActivity(){
        Intent n = new Intent(this,NoviceActivity.class);
        startActivity(n);
    }
    public void IntermediateActivity(){
        Intent i = new Intent(this,IntermediateActivity.class);
        startActivity(i);
    }
    public void AdvancedActivity(){
        Intent a = new Intent(this,AdvancedActivity.class);
        startActivity(a);
    }
    public void EliteActivity(){
        Intent e = new Intent(this,EliteActivity.class);
        startActivity(e);
    }
}
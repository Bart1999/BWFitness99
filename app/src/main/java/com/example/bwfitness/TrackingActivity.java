package com.example.bwfitness;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.bwfitness.databinding.ActivityTrackingBinding;

public class TrackingActivity extends NavDrawerBaseActivity {

    ActivityTrackingBinding activityTrackingBinding;
    private Button btn_calorie;
    private Button btn_workout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityTrackingBinding = ActivityTrackingBinding.inflate(getLayoutInflater());
        setContentView(activityTrackingBinding.getRoot());
        allocateActivityTitle("Tracking");

        ActionBar actionBar = getSupportActionBar();

        btn_calorie =(Button) findViewById(R.id.btn_calorie);
        btn_calorie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalorieActivity();
            }
        });

        btn_workout =(Button) findViewById(R.id.btn_workout);
        btn_workout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WorkoutActivity();
            }
        });
    }

    public void CalorieActivity(){
        Intent c = new Intent(this,CalorieActivity.class);
        startActivity(c);
    }

    public void WorkoutActivity(){
        Intent w = new Intent(this,WorkoutActivity.class);
        startActivity(w);
    }
}
package com.example.bwfitness;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.bwfitness.databinding.ActivityGymProgressBinding;
import com.example.bwfitness.databinding.ActivityHomePageBinding;

public class GymProgressActivity extends NavDrawerBaseActivity {

    ActivityGymProgressBinding activityGymProgressBinding;
    private Button weight_progress;
    private Button genderbench_progress;
    private Button genderdeadlift_progress;
    private Button gendersquat_progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityGymProgressBinding =ActivityGymProgressBinding.inflate(getLayoutInflater());
        setContentView(activityGymProgressBinding.getRoot());
        allocateActivityTitle("Gym Progress");

        ActionBar actionBar = getSupportActionBar();

        weight_progress =(Button) findViewById(R.id.weight_progress);
        weight_progress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WeightProgressActivity();
            }
        });

        genderbench_progress =(Button) findViewById(R.id.bench_progress);
        genderbench_progress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GenderBenchActivity();
            }
        });

        genderdeadlift_progress =(Button) findViewById(R.id.deadlift_progress);
        genderdeadlift_progress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GenderDeadliftActivity();
            }
        });

        gendersquat_progress =(Button) findViewById(R.id.squat_progress);
        gendersquat_progress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GenderSquatActivity();
            }
        });
    }
    public void WeightProgressActivity(){
        Intent w = new Intent(this,WeightProgressActivity.class);
        startActivity(w);
    }

    public void GenderBenchActivity(){
        Intent b = new Intent(this,GenderBenchActivity.class);
        startActivity(b);
    }

    public void GenderDeadliftActivity(){
        Intent d = new Intent(this,GenderDeadliftActivity.class);
        startActivity(d);
    }

    public void GenderSquatActivity(){
        Intent s = new Intent(this,GenderSquatActivity.class);
        startActivity(s);
    }
}
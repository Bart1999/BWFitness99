package com.example.bwfitness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.bwfitness.databinding.ActivityGenderBenchBinding;
import com.example.bwfitness.databinding.ActivityGenderDeadliftBinding;

public class GenderDeadliftActivity extends NavDrawerBaseActivity {

    ActivityGenderDeadliftBinding activityGenderDeadliftBinding;
    private Button male_deadlift;
    private Button female_deadlift;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityGenderDeadliftBinding = ActivityGenderDeadliftBinding.inflate(getLayoutInflater());
        setContentView(activityGenderDeadliftBinding.getRoot());
        allocateActivityTitle("Deadlift Progress by Gender");

        male_deadlift =(Button) findViewById(R.id.male_deadlift);
        male_deadlift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeadliftProgressActivity();
            }
        });

        female_deadlift =(Button) findViewById(R.id.female_deadlift);
        female_deadlift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FemaleDeadliftProgressActivity();
            }
        });
    }

    public void DeadliftProgressActivity(){
        Intent d = new Intent(this,DeadliftProgressActivity.class);
        startActivity(d);
    }

    public void FemaleDeadliftProgressActivity(){
        Intent f = new Intent(this,FemaleDeadliftProgressActivity.class);
        startActivity(f);
    }
}
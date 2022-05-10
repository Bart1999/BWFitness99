package com.example.bwfitness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.bwfitness.databinding.ActivityGenderBenchBinding;
import com.example.bwfitness.databinding.ActivityGenderSquatBinding;

public class GenderSquatActivity extends NavDrawerBaseActivity {

    ActivityGenderSquatBinding activityGenderSquatBinding;
    private Button male_squat;
    private Button female_squat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityGenderSquatBinding = ActivityGenderSquatBinding.inflate(getLayoutInflater());
        setContentView(activityGenderSquatBinding.getRoot());
        allocateActivityTitle("Squat Progress by Gender");

        male_squat =(Button) findViewById(R.id.male_squat);
        male_squat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SquatProgressActivity();
            }
        });

        female_squat =(Button) findViewById(R.id.female_squat);
        female_squat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FemaleSquatProgressActivity();
            }
        });
    }

    public void SquatProgressActivity(){
        Intent s = new Intent(this,SquatProgressActivity.class);
        startActivity(s);
    }

    public void FemaleSquatProgressActivity(){
        Intent f = new Intent(this,FemaleSquatProgressActivity.class);
        startActivity(f);
    }
}
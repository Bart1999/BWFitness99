package com.example.bwfitness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.bwfitness.databinding.ActivityFemaleDeadliftProgressBinding;
import com.example.bwfitness.databinding.ActivityGenderBenchBinding;

public class GenderBenchActivity extends NavDrawerBaseActivity {

    ActivityGenderBenchBinding activityGenderBenchBinding;
    private Button male_bench;
    private Button female_bench;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityGenderBenchBinding = ActivityGenderBenchBinding.inflate(getLayoutInflater());
        setContentView(activityGenderBenchBinding.getRoot());
        allocateActivityTitle("Bench Progress by Gender");

        male_bench =(Button) findViewById(R.id.male_bench);
        male_bench.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BenchProgressActivity();
            }
        });

        female_bench =(Button) findViewById(R.id.female_bench);
        female_bench.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FemaleBenchProgressActivity();
            }
        });
    }

    public void BenchProgressActivity(){
        Intent b = new Intent(this,BenchProgressActivity.class);
        startActivity(b);
    }

    public void FemaleBenchProgressActivity(){
        Intent f = new Intent(this,FemaleBenchProgressActivity.class);
        startActivity(f);
    }
}
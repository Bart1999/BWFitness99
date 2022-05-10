package com.example.bwfitness;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.bwfitness.databinding.ActivityHomePageBinding;
import com.example.bwfitness.databinding.ActivityOverallNutritionBinding;

public class OverallNutritionActivity extends NavDrawerBaseActivity {

    ActivityOverallNutritionBinding activityOverallNutritionBinding;
    Button view_cut,view_maintain,view_bulk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityOverallNutritionBinding = ActivityOverallNutritionBinding.inflate(getLayoutInflater());
        setContentView(activityOverallNutritionBinding.getRoot());
        allocateActivityTitle("Overall Nutrition Advice");

        ActionBar actionBar = getSupportActionBar();

        view_cut =(Button) findViewById(R.id.view_cut);
        view_cut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CuttingAdviceActivity();
            }
        });

        view_maintain =(Button) findViewById(R.id.view_maintain);
        view_maintain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaintainAdviceActivity();
            }
        });

        view_bulk =(Button) findViewById(R.id.view_bulk);
        view_bulk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BulkingAdviceActivity();
            }
        });
    }
    public void CuttingAdviceActivity(){
        Intent c = new Intent(this,CuttingAdviceActivity.class);
        startActivity(c);
    }
    public void MaintainAdviceActivity(){
        Intent m = new Intent(this,MaintainAdviceActivity.class);
        startActivity(m);
    }
    public void BulkingAdviceActivity(){
        Intent b = new Intent(this,BulkingAdviceActivity.class);
        startActivity(b);
    }
}
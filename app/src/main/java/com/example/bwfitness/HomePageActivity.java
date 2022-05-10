package com.example.bwfitness;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;

import com.example.bwfitness.databinding.ActivityHomePageBinding;


public class HomePageActivity extends NavDrawerBaseActivity {

    ActivityHomePageBinding activityHomePageBinding;
    private Button btn_book;
    private Button btn_track;
    private Button btn_progress;
    private Button btn_settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityHomePageBinding = ActivityHomePageBinding.inflate(getLayoutInflater());
        setContentView(activityHomePageBinding.getRoot());
        allocateActivityTitle("Home");

        ActionBar actionBar = getSupportActionBar();

        btn_book =(Button) findViewById(R.id.btn_book);
        btn_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 BookingActivity();
            }
        });

        btn_track =(Button) findViewById(R.id.btn_track);
        btn_track.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TrackingActivity();
            }
        });

        btn_progress =(Button) findViewById(R.id.btn_progress);
        btn_progress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GymProgressActivity();
            }
        });

        btn_settings =(Button) findViewById(R.id.btn_settings);
        btn_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SettingsActivity();
            }
        });
    }

    public void BookingActivity(){
        Intent b = new Intent(this,BookingActivity.class);
        startActivity(b);
    }
    public void TrackingActivity(){
        Intent t = new Intent(this,TrackingActivity.class);
        startActivity(t);
    }
    public void GymProgressActivity(){
        Intent p = new Intent(this, GymProgressActivity.class);
        startActivity(p);
    }
    public void SettingsActivity(){
        Intent s = new Intent(this,SettingsActivity.class);
        startActivity(s);
    }
}
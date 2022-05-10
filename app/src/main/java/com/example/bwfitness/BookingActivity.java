package com.example.bwfitness;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.bwfitness.databinding.ActivityBookingBinding;

public class BookingActivity extends NavDrawerBaseActivity {

    ActivityBookingBinding activityBookingBinding;
    private Button btn_membership;
    private Button btn_gymslot;
    private Button btn_pt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityBookingBinding = ActivityBookingBinding.inflate(getLayoutInflater());
        setContentView(activityBookingBinding.getRoot());
        allocateActivityTitle("Booking");

        ActionBar actionBar = getSupportActionBar();

        btn_membership =(Button) findViewById(R.id.btn_membership);
        btn_membership.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MembershipActivity();
            }
        });

        btn_gymslot =(Button) findViewById(R.id.btn_gymslot);
        btn_gymslot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GymSlotActivity();
            }
        });

        btn_pt =(Button) findViewById(R.id.btn_pt);
        btn_pt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PTActivity();
            }
        });

    }
    public void MembershipActivity(){
        Intent m = new Intent(this,MembershipActivity.class);
        startActivity(m);
    }

    public void GymSlotActivity(){
        Intent g = new Intent(this,GymSlotActivity.class);
        startActivity(g);
    }

    public void PTActivity(){
        Intent p = new Intent(this,PTActivity.class);
        startActivity(p);
    }
}
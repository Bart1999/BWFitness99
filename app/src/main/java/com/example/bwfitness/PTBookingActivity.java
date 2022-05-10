package com.example.bwfitness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.bwfitness.databinding.ActivityPtactivityBinding;
import com.example.bwfitness.databinding.ActivityPtbookingBinding;

public class PTBookingActivity extends NavDrawerBaseActivity {

    ActivityPtbookingBinding activityPtbookingBinding;
    private Button mike_callis;
    private Button kyle_vance;
    private Button peter_donnington;
    private Button james_reid;
    private Button sarah_ream;
    private Button jessica_ryan;
    private Button kylie_wall;
    private Button chloe_nolan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityPtbookingBinding = ActivityPtbookingBinding.inflate(getLayoutInflater());
        setContentView(activityPtbookingBinding.getRoot());
        allocateActivityTitle("PT Booking");

        mike_callis =(Button) findViewById(R.id.mike_callis);
        mike_callis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MikeCallisActivity();
            }
        });

        kyle_vance =(Button) findViewById(R.id.kyle_vance);
        kyle_vance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KyleVanceActivity();
            }
        });

        peter_donnington =(Button) findViewById(R.id.peter_donnington);
        peter_donnington.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PeterDonningtonActivity();
            }
        });

        james_reid =(Button) findViewById(R.id.james_reid);
        james_reid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JamesReidActivity();
            }
        });

        sarah_ream =(Button) findViewById(R.id.sarah_ream);
        sarah_ream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SarahReamActivity();
            }
        });

        jessica_ryan =(Button) findViewById(R.id.jessica_ryan);
        jessica_ryan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JessicaRyanActivity();
            }
        });

        kylie_wall =(Button) findViewById(R.id.kylie_wall);
        kylie_wall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KylieWallActivity();
            }
        });
        chloe_nolan =(Button) findViewById(R.id.chloe_nolan);
        chloe_nolan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChloeNolanActivity();
            }
        });
    }
    public void MikeCallisActivity(){
        Intent m = new Intent(this,MikeCallisActivity.class);
        startActivity(m);
    }

    public void KyleVanceActivity(){
        Intent k = new Intent(this,KyleVanceActivity.class);
        startActivity(k);
    }

    public void PeterDonningtonActivity(){
        Intent p = new Intent(this,PeterDonningtonActivity.class);
        startActivity(p);
    }

    public void JamesReidActivity(){
        Intent j = new Intent(this,JamesReidActivity.class);
        startActivity(j);
    }

    public void SarahReamActivity(){
        Intent s = new Intent(this,SarahReamActivity.class);
        startActivity(s);
    }

    public void JessicaRyanActivity(){
        Intent j = new Intent(this,JessicaRyanActivity.class);
        startActivity(j);
    }

    public void KylieWallActivity(){
        Intent k = new Intent(this,KylieWallActivity.class);
        startActivity(k);
    }

    public void ChloeNolanActivity(){
        Intent c = new Intent(this,ChloeNolanActivity.class);
        startActivity(c);
    }

}
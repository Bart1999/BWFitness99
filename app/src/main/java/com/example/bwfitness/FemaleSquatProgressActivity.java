package com.example.bwfitness;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatSeekBar;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.bwfitness.databinding.ActivityFemaleDeadliftProgressBinding;
import com.example.bwfitness.databinding.ActivityFemaleSquatProgressBinding;

import java.util.Timer;
import java.util.TimerTask;

public class FemaleSquatProgressActivity extends NavDrawerBaseActivity {

    ActivityFemaleSquatProgressBinding activityFemaleSquatProgressBinding;
    private TextView age3f;
    private TextView weight3f;
    private AppCompatSeekBar seekBar3f;
    private TextView lift_value3f;
    Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityFemaleSquatProgressBinding = ActivityFemaleSquatProgressBinding.inflate(getLayoutInflater());
        setContentView(activityFemaleSquatProgressBinding.getRoot());
        allocateActivityTitle("Female Squat Progress");

        age3f = findViewById(R.id.age3f);
        weight3f = findViewById(R.id.weight3f);
        seekBar3f = findViewById(R.id.seek_bar3f);
        seekBar3f.setOnSeekBarChangeListener(listener3f);
        lift_value3f = findViewById(R.id.lift_value3f);
    }

    private SeekBar.OnSeekBarChangeListener listener3f = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            lift_value3f.setText(String.format("%d kg", progress));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    public void increaseAge3f(View view) {
        if (Integer.parseInt(age3f.getText().toString()) > 0) {
            int age_value3f = Integer.parseInt(age3f.getText().toString()) + 1;
            age3f.setText(String.valueOf(age_value3f));
        }
    }

    public void decreaseAge3f(View view) {
        if (Integer.parseInt(age3f.getText().toString()) > 1) {
            int age_value3f = Integer.parseInt(age3f.getText().toString()) - 1;
            age3f.setText(String.valueOf(age_value3f));
        }
    }

    public void increaseWeight3f(View view) {
        if (Integer.parseInt(weight3f.getText().toString()) > 0) {
            int weight_value3f = Integer.parseInt(weight3f.getText().toString()) + 1;
            weight3f.setText(String.valueOf(weight_value3f));
        }
    }

    public void decreaseWeight3f(View view) {
        if (Integer.parseInt(weight3f.getText().toString()) > 1) {
            int weight_value3f = Integer.parseInt(weight3f.getText().toString()) - 1;
            weight3f.setText(String.valueOf(weight_value3f));
        }
    }

    public void showResult3f(View view){
        int get_age3f = Integer.parseInt(age3f.getText().toString());
        int weight_value3f = Integer.parseInt(weight3f.getText().toString());
        int get_lift3f = seekBar3f.getProgress();

        int squatf = get_lift3f;

        showSquatf(squatf);
    }

    private void showSquatf(int squatf){

        if(squatf > 30 && squatf < 49) {
            showCustomDialog3f(R.drawable.beginner, "Beginner", "You are in the Beginner Strength Class");
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Intent i = new Intent(FemaleSquatProgressActivity.this, BeginnerActivity.class);
                    startActivity(i);
                    finish();
                }
            },2000);
        }else if(squatf > 49 && squatf < 73){
            showCustomDialog3f(R.drawable.novice,"Novice","You are in the Novice Strength Class");
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Intent i = new Intent(FemaleSquatProgressActivity.this, NoviceActivity.class);
                    startActivity(i);
                    finish();
                }
            },2000);
        }else if(squatf > 73 && squatf < 103 ) {
            showCustomDialog3f(R.drawable.intermediate, "Intermediate", "You are in the Intermediate Strength Class");
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Intent i = new Intent(FemaleSquatProgressActivity.this, IntermediateActivity.class);
                    startActivity(i);
                    finish();
                }
            },2000);
        }else if(squatf > 103 && squatf < 136) {
            showCustomDialog3f(R.drawable.advanced, "Advanced", "You are in the Advanced Strength Class");
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Intent i = new Intent(FemaleSquatProgressActivity.this, AdvancedActivity.class);
                    startActivity(i);
                    finish();
                }
            },2000);
        }else{
            showCustomDialog3f(R.drawable.elite, "Elite", "You are in the Elite Strength Class");
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Intent i = new Intent(FemaleSquatProgressActivity.this, EliteActivity.class);
                    startActivity(i);
                    finish();
                }
            },2000);
        }
    }

    private void showCustomDialog3f(int id,String title,String tip) {
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View view = LayoutInflater.from(this).inflate(R.layout.custom_dialog, viewGroup, false);
        AppCompatButton ok = view.findViewById(R.id.ok);
        ImageView imageView = view.findViewById(R.id.image_view);
        TextView result_title = view.findViewById(R.id.result_title);
        TextView tips = view.findViewById(R.id.tips);
        imageView.setImageResource(id);
        result_title.setText(title);
        tips.setText(tip);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }
}
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

import com.example.bwfitness.databinding.ActivityFemaleBenchProgressBinding;
import com.example.bwfitness.databinding.ActivityFemaleDeadliftProgressBinding;
import com.example.bwfitness.databinding.ActivityGymProgressBinding;

import java.util.Timer;
import java.util.TimerTask;

public class FemaleDeadliftProgressActivity extends NavDrawerBaseActivity {

    ActivityFemaleDeadliftProgressBinding activityFemaleDeadliftProgressBinding;
    private TextView age2f;
    private TextView weight2f;
    private AppCompatSeekBar seekBar2f;
    private TextView lift_value2f;
    Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityFemaleDeadliftProgressBinding = ActivityFemaleDeadliftProgressBinding.inflate(getLayoutInflater());
        setContentView(activityFemaleDeadliftProgressBinding.getRoot());
        allocateActivityTitle("Female Deadlift Progress");

        age2f = findViewById(R.id.age2f);
        weight2f = findViewById(R.id.weight2f);
        seekBar2f = findViewById(R.id.seek_bar2f);
        seekBar2f.setOnSeekBarChangeListener(listener2f);
        lift_value2f = findViewById(R.id.lift_value2f);
    }

    private SeekBar.OnSeekBarChangeListener listener2f = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            lift_value2f.setText(String.format("%d kg", progress));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    public void increaseAge2f(View view) {
        if (Integer.parseInt(age2f.getText().toString()) > 0) {
            int age_value2f = Integer.parseInt(age2f.getText().toString()) + 1;
            age2f.setText(String.valueOf(age_value2f));
        }
    }

    public void decreaseAge2f(View view) {
        if (Integer.parseInt(age2f.getText().toString()) > 1) {
            int age_value2f = Integer.parseInt(age2f.getText().toString()) - 1;
            age2f.setText(String.valueOf(age_value2f));
        }
    }

    public void increaseWeight2f(View view) {
        if (Integer.parseInt(weight2f.getText().toString()) > 0) {
            int weight_value2f = Integer.parseInt(weight2f.getText().toString()) + 1;
            weight2f.setText(String.valueOf(weight_value2f));
        }
    }

    public void decreaseWeight2f(View view) {
        if (Integer.parseInt(weight2f.getText().toString()) > 1) {
            int weight_value2f = Integer.parseInt(weight2f.getText().toString()) - 1;
            weight2f.setText(String.valueOf(weight_value2f));
        }
    }

    public void showResult2f(View view){
        int get_age2f = Integer.parseInt(age2f.getText().toString());
        int weight_value2f = Integer.parseInt(weight2f.getText().toString());
        int get_lift2f = seekBar2f.getProgress();

        int deadliftf = get_lift2f;

        showDeadliftf(deadliftf);
    }

    private void showDeadliftf(int deadliftf){

        if(deadliftf > 38 && deadliftf < 60) {
            showCustomDialog2f(R.drawable.beginner, "Beginner", "You are in the Beginner Strength Class");
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Intent i = new Intent(FemaleDeadliftProgressActivity.this, BeginnerActivity.class);
                    startActivity(i);
                    finish();
                }
            },2000);
        }else if(deadliftf > 60 && deadliftf < 88){
            showCustomDialog2f(R.drawable.novice,"Novice","You are in the Novice Strength Class");
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Intent i = new Intent(FemaleDeadliftProgressActivity.this, NoviceActivity.class);
                    startActivity(i);
                    finish();
                }
            },2000);
        }else if(deadliftf > 88 && deadliftf < 121 ) {
            showCustomDialog2f(R.drawable.intermediate, "Intermediate", "You are in the Intermediate Strength Class");
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Intent i = new Intent(FemaleDeadliftProgressActivity.this, IntermediateActivity.class);
                    startActivity(i);
                    finish();
                }
            },2000);
        }else if(deadliftf > 121 && deadliftf < 157) {
            showCustomDialog2f(R.drawable.advanced, "Advanced", "You are in the Advanced Strength Class");
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Intent i = new Intent(FemaleDeadliftProgressActivity.this, AdvancedActivity.class);
                    startActivity(i);
                    finish();
                }
            },2000);
        }else{
            showCustomDialog2f(R.drawable.elite, "Elite", "You are in the Elite Strength Class");
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Intent i = new Intent(FemaleDeadliftProgressActivity.this, EliteActivity.class);
                    startActivity(i);
                    finish();
                }
            },2000);
        }
    }

    private void showCustomDialog2f(int id,String title,String tip) {
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
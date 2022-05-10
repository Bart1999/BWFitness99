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

import com.example.bwfitness.databinding.ActivityGenderSquatBinding;
import com.example.bwfitness.databinding.ActivityMembershipBinding;
import com.example.bwfitness.databinding.ActivitySquatProgressBinding;

import java.util.Timer;
import java.util.TimerTask;

public class SquatProgressActivity extends NavDrawerBaseActivity {

    ActivitySquatProgressBinding activitySquatProgressBinding;
    private TextView age3;
    private TextView weight3;
    private AppCompatSeekBar seekBar3;
    private TextView lift_value3;
    Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySquatProgressBinding = ActivitySquatProgressBinding.inflate(getLayoutInflater());
        setContentView(activitySquatProgressBinding.getRoot());
        allocateActivityTitle("Mens Squat Progress");

        age3 = findViewById(R.id.age3);
        weight3 = findViewById(R.id.weight3);
        seekBar3 = findViewById(R.id.seek_bar3);
        seekBar3.setOnSeekBarChangeListener(listener3);
        lift_value3 = findViewById(R.id.lift_value3);
    }

    private SeekBar.OnSeekBarChangeListener listener3 = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            lift_value3.setText(String.format("%d kg", progress));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    public void increaseAge3(View view) {
        if (Integer.parseInt(age3.getText().toString()) > 0) {
            int age_value3 = Integer.parseInt(age3.getText().toString()) + 1;
            age3.setText(String.valueOf(age_value3));
        }
    }

    public void decreaseAge3(View view) {
        if (Integer.parseInt(age3.getText().toString()) > 1) {
            int age_value3 = Integer.parseInt(age3.getText().toString()) - 1;
            age3.setText(String.valueOf(age_value3));
        }
    }

    public void increaseWeight3(View view) {
        if (Integer.parseInt(weight3.getText().toString()) > 0) {
            int weight_value3 = Integer.parseInt(weight3.getText().toString()) + 1;
            weight3.setText(String.valueOf(weight_value3));
        }
    }

    public void decreaseWeight3(View view) {
        if (Integer.parseInt(weight3.getText().toString()) > 1) {
            int weight_value3 = Integer.parseInt(weight3.getText().toString()) - 1;
            weight3.setText(String.valueOf(weight_value3));
        }
    }

    public void showResult3(View view){
        int get_age3 = Integer.parseInt(age3.getText().toString());
        int weight_value3 = Integer.parseInt(weight3.getText().toString());
        int get_lift3 = seekBar3.getProgress();

        int squat = get_lift3;

        showSquat(squat);
    }

    private void showSquat(int squat){

        if(squat > 63 && squat < 92) {
            showCustomDialog3(R.drawable.beginner, "Beginner", "You are in the Beginner Strength Class");
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Intent i = new Intent(SquatProgressActivity.this, BeginnerActivity.class);
                    startActivity(i);
                    finish();
                }
            },2000);
        }else if(squat > 92 && squat < 129){
            showCustomDialog3(R.drawable.novice,"Novice","You are in the Novice Strength Class");
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Intent i = new Intent(SquatProgressActivity.this, NoviceActivity.class);
                    startActivity(i);
                    finish();
                }
            },2000);
        }else if(squat > 129 && squat < 172 ) {
            showCustomDialog3(R.drawable.intermediate, "Intermediate", "You are in the Intermediate Strength CLass");
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Intent i = new Intent(SquatProgressActivity.this, IntermediateActivity.class);
                    startActivity(i);
                    finish();
                }
            },2000);
        }else if(squat > 172 && squat < 218) {
            showCustomDialog3(R.drawable.advanced, "Advanced", "You are in the Advanced Strength Class");
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Intent i = new Intent(SquatProgressActivity.this, AdvancedActivity.class);
                    startActivity(i);
                    finish();
                }
            },2000);
        }else{
            showCustomDialog3(R.drawable.elite, "Elite", "You are in the Elite Strength Class");
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Intent i = new Intent(SquatProgressActivity.this, EliteActivity.class);
                    startActivity(i);
                    finish();
                }
            },2000);
        }
    }

    private void showCustomDialog3(int id,String title,String tip) {
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
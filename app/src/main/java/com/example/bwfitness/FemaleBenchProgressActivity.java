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
import com.example.bwfitness.databinding.ActivityGymProgressBinding;

import java.util.Timer;
import java.util.TimerTask;

public class FemaleBenchProgressActivity extends NavDrawerBaseActivity {

    ActivityFemaleBenchProgressBinding activityFemaleBenchProgressBinding;
    private TextView age1f;
    private TextView weight1f;
    private AppCompatSeekBar seekBar1f;
    private TextView lift_valuef;
    Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityFemaleBenchProgressBinding = ActivityFemaleBenchProgressBinding.inflate(getLayoutInflater());
        setContentView(activityFemaleBenchProgressBinding.getRoot());
        allocateActivityTitle("Female Bench Press Progress");

        age1f = findViewById(R.id.age1f);
        weight1f = findViewById(R.id.weight1f);
        seekBar1f = findViewById(R.id.seek_bar1f);
        seekBar1f.setOnSeekBarChangeListener(listener1f);
        lift_valuef = findViewById(R.id.lift_valuef);
    }

    private SeekBar.OnSeekBarChangeListener listener1f = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            lift_valuef.setText(String.format("%d kg", progress));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    public void increaseAge1f(View view) {
        if (Integer.parseInt(age1f.getText().toString()) > 0) {
            int age_value1f = Integer.parseInt(age1f.getText().toString()) + 1;
            age1f.setText(String.valueOf(age_value1f));
        }
    }

    public void decreaseAge1f(View view) {
        if (Integer.parseInt(age1f.getText().toString()) > 1) {
            int age_value1f = Integer.parseInt(age1f.getText().toString()) - 1;
            age1f.setText(String.valueOf(age_value1f));
        }
    }

    public void increaseWeight1f(View view) {
        if (Integer.parseInt(weight1f.getText().toString()) > 0) {
            int weight_value1f = Integer.parseInt(weight1f.getText().toString()) + 1;
            weight1f.setText(String.valueOf(weight_value1f));
        }
    }

    public void decreaseWeight1f(View view) {
        if (Integer.parseInt(weight1f.getText().toString()) > 1) {
            int weight_value1f = Integer.parseInt(weight1f.getText().toString()) - 1;
            weight1f.setText(String.valueOf(weight_value1f));
        }
    }

    public void showResult1f(View view){
        int get_age1f = Integer.parseInt(age1f.getText().toString());
        int weight_value1f = Integer.parseInt(weight1f.getText().toString());
        int get_lift1f = seekBar1f.getProgress();

        int benchf = get_lift1f;

        showBenchf(benchf);
    }

    private void showBenchf(int benchf){

        if(benchf > 17 && benchf < 31) {
            showCustomDialog1f(R.drawable.beginner, "Beginner", "You are in the Beginner Strength Class");
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Intent i = new Intent(FemaleBenchProgressActivity.this, BeginnerActivity.class);
                    startActivity(i);
                    finish();
                }
            },2000);
        }else if(benchf > 31 && benchf < 50){
            showCustomDialog1f(R.drawable.novice,"Novice","You are in the Novice Strength Class");
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Intent i = new Intent(FemaleBenchProgressActivity.this, NoviceActivity.class);
                    startActivity(i);
                    finish();
                }
            },2000);
        }else if(benchf > 50 && benchf < 73) {
            showCustomDialog1f(R.drawable.intermediate, "Intermediate", "You are in the Intermediate Strength CLass");
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Intent i = new Intent(FemaleBenchProgressActivity.this, IntermediateActivity.class);
                    startActivity(i);
                    finish();
                }
            },2000);
        }else if(benchf > 73 && benchf < 100) {
            showCustomDialog1f(R.drawable.advanced, "Advanced", "You are in the Advanced Strength Class");
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Intent i = new Intent(FemaleBenchProgressActivity.this, AdvancedActivity.class);
                    startActivity(i);
                    finish();
                }
            },2000);
        }else{
            showCustomDialog1f(R.drawable.elite, "Elite", "You are in the Elite Strength Class");
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Intent i = new Intent(FemaleBenchProgressActivity.this, EliteActivity.class);
                    startActivity(i);
                    finish();
                }
            },2000);
        }
    }

    private void showCustomDialog1f(int id,String title,String tip) {
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
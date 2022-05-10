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

import com.example.bwfitness.databinding.ActivityDeadliftProgressBinding;
import com.example.bwfitness.databinding.ActivityGymProgressBinding;

import java.util.Timer;
import java.util.TimerTask;

public class DeadliftProgressActivity extends NavDrawerBaseActivity {

    ActivityDeadliftProgressBinding activityDeadliftProgressBinding;
    private TextView age2;
    private TextView weight2;
    private AppCompatSeekBar seekBar2;
    private TextView lift_value2;
    Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDeadliftProgressBinding = ActivityDeadliftProgressBinding.inflate(getLayoutInflater());
        setContentView(activityDeadliftProgressBinding.getRoot());
        allocateActivityTitle("Mens Deadlift Progress");

        age2 = findViewById(R.id.age2);
        weight2 = findViewById(R.id.weight2);
        seekBar2 = findViewById(R.id.seek_bar2);
        seekBar2.setOnSeekBarChangeListener(listener2);
        lift_value2 = findViewById(R.id.lift_value2);
    }

    private SeekBar.OnSeekBarChangeListener listener2 = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            lift_value2.setText(String.format("%d kg", progress));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    public void increaseAge2(View view) {
        if (Integer.parseInt(age2.getText().toString()) > 0) {
            int age_value2 = Integer.parseInt(age2.getText().toString()) + 1;
            age2.setText(String.valueOf(age_value2));
        }
    }

    public void decreaseAge2(View view) {
        if (Integer.parseInt(age2.getText().toString()) > 1) {
            int age_value2 = Integer.parseInt(age2.getText().toString()) - 1;
            age2.setText(String.valueOf(age_value2));
        }
    }

    public void increaseWeight2(View view) {
        if (Integer.parseInt(weight2.getText().toString()) > 0) {
            int weight_value2 = Integer.parseInt(weight2.getText().toString()) + 1;
            weight2.setText(String.valueOf(weight_value2));
        }
    }

    public void decreaseWeight2(View view) {
        if (Integer.parseInt(weight2.getText().toString()) > 1) {
            int weight_value2 = Integer.parseInt(weight2.getText().toString()) - 1;
            weight2.setText(String.valueOf(weight_value2));
        }
    }

    public void showResult2(View view){
        int get_age2 = Integer.parseInt(age2.getText().toString());
        int weight_value2 = Integer.parseInt(weight2.getText().toString());
        int get_lift2 = seekBar2.getProgress();

        int deadlift = get_lift2;

        showDeadlift(deadlift);
    }

    private void showDeadlift(int deadlift){

        if(deadlift > 77 && deadlift < 111) {
            showCustomDialog2(R.drawable.beginner, "Beginner", "You are in the Beginner Strength Class");
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Intent i = new Intent(DeadliftProgressActivity.this, BeginnerActivity.class);
                    startActivity(i);
                    finish();
                }
            },2000);
        }else if(deadlift > 111 && deadlift < 152){
            showCustomDialog2(R.drawable.novice,"Novice","You are in the Novice Strength Class");
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Intent i = new Intent(DeadliftProgressActivity.this, NoviceActivity.class);
                    startActivity(i);
                    finish();
                }
            },2000);
        }else if(deadlift > 152 && deadlift < 199 ) {
            showCustomDialog2(R.drawable.intermediate, "Intermediate", "You are in the Intermediate Strength CLass");
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Intent i = new Intent(DeadliftProgressActivity.this, IntermediateActivity.class);
                    startActivity(i);
                    finish();
                }
            },2000);
        }else if(deadlift > 199 && deadlift < 250) {
            showCustomDialog2(R.drawable.advanced, "Advanced", "You are in the Advanced Strength Class");
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Intent i = new Intent(DeadliftProgressActivity.this, AdvancedActivity.class);
                    startActivity(i);
                    finish();
                }
            },2000);
        }else{
            showCustomDialog2(R.drawable.elite, "Elite", "You are in the Elite Strength Class");
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Intent i = new Intent(DeadliftProgressActivity.this, EliteActivity.class);
                    startActivity(i);
                    finish();
                }
            },2000);
        }
    }

    private void showCustomDialog2(int id,String title,String tip) {
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
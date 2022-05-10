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

import com.example.bwfitness.databinding.ActivityBenchProgressBinding;
import com.example.bwfitness.databinding.ActivityGymProgressBinding;

import java.util.Timer;
import java.util.TimerTask;

public class BenchProgressActivity extends NavDrawerBaseActivity {

    ActivityBenchProgressBinding activityBenchProgressBinding;
    private TextView age1;
    private TextView weight1;
    private AppCompatSeekBar seekBar1;
    private TextView lift_value;
    Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityBenchProgressBinding = ActivityBenchProgressBinding.inflate(getLayoutInflater());
        setContentView(activityBenchProgressBinding.getRoot());
        allocateActivityTitle("Mens Bench Press Progress");

        age1 = findViewById(R.id.age1);
        weight1 = findViewById(R.id.weight1);
        seekBar1 = findViewById(R.id.seek_bar1);
        seekBar1.setOnSeekBarChangeListener(listener1);
        lift_value = findViewById(R.id.lift_value);
    }

    private SeekBar.OnSeekBarChangeListener listener1 = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            lift_value.setText(String.format("%d kg", progress));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    public void increaseAge1(View view) {
        if (Integer.parseInt(age1.getText().toString()) > 0) {
            int age_value1 = Integer.parseInt(age1.getText().toString()) + 1;
            age1.setText(String.valueOf(age_value1));
        }
    }

    public void decreaseAge1(View view) {
        if (Integer.parseInt(age1.getText().toString()) > 1) {
            int age_value1 = Integer.parseInt(age1.getText().toString()) - 1;
            age1.setText(String.valueOf(age_value1));
        }
    }

    public void increaseWeight1(View view) {
        if (Integer.parseInt(weight1.getText().toString()) > 0) {
            int weight_value1 = Integer.parseInt(weight1.getText().toString()) + 1;
            weight1.setText(String.valueOf(weight_value1));
        }
    }

    public void decreaseWeight1(View view) {
        if (Integer.parseInt(weight1.getText().toString()) > 1) {
            int weight_value1 = Integer.parseInt(weight1.getText().toString()) - 1;
            weight1.setText(String.valueOf(weight_value1));
        }
    }

    public void showResult1(View view){
        int get_age1 = Integer.parseInt(age1.getText().toString());
        int weight_value1 = Integer.parseInt(weight1.getText().toString());
        int get_lift1 = seekBar1.getProgress();

        int bench = get_lift1;

        showBench(bench);
    }

    private void showBench(int bench){

        if(bench > 46 && bench < 68) {
            showCustomDialog1(R.drawable.beginner, "Beginner", "You are in the Beginner Strength Class");
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Intent i = new Intent(BenchProgressActivity.this, BeginnerActivity.class);
                    startActivity(i);
                    finish();
                }
            },2000);

        }else if(bench > 68 && bench < 97){
            showCustomDialog1(R.drawable.novice,"Novice","You are in the Novice Strength Class");
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Intent i = new Intent(BenchProgressActivity.this, NoviceActivity.class);
                    startActivity(i);
                    finish();
                }
            },2000);
        }else if(bench > 97 && bench < 131) {
            showCustomDialog1(R.drawable.intermediate, "Intermediate", "You are in the Intermediate Strength CLass");
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Intent i = new Intent(BenchProgressActivity.this, IntermediateActivity.class);
                    startActivity(i);
                    finish();
                }
            },2000);
        }else if(bench > 131 && bench < 161) {
            showCustomDialog1(R.drawable.advanced, "Advanced", "You are in the Advanced Strength Class");
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Intent i = new Intent(BenchProgressActivity.this, AdvancedActivity.class);
                    startActivity(i);
                    finish();
                }
            },2000);

        }else{
            showCustomDialog1(R.drawable.elite, "Elite", "You are in the Elite Strength Class");
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Intent i = new Intent(BenchProgressActivity.this, EliteActivity.class);
                    startActivity(i);
                    finish();
                }
            },2000);
        }
    }

    private void showCustomDialog1(int id,String title,String tip) {
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
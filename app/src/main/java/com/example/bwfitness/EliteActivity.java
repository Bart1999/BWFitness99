package com.example.bwfitness;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bwfitness.databinding.ActivityEliteBinding;

public class EliteActivity extends NavDrawerBaseActivity {

    ActivityEliteBinding activityEliteBinding;

    EditText which_exercise, target_weight , current_weight , target_sets , current_sets , target_reps, current_reps;

    Button view_workout , new_session5,overall5;

    DBHelper myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityEliteBinding = activityEliteBinding.inflate(getLayoutInflater());
        setContentView(activityEliteBinding.getRoot());
        allocateActivityTitle("Elite Advice");

        which_exercise = findViewById(R.id.which_exercise);
        target_weight = findViewById(R.id.target_weight);
        current_weight = findViewById(R.id.current_weight);
        target_sets = findViewById(R.id.target_sets);
        current_sets = findViewById(R.id.current_sets);
        target_reps = findViewById(R.id.target_reps);
        current_reps = findViewById(R.id.current_reps);
        view_workout = findViewById(R.id.view_workout);
        new_session5 = findViewById(R.id.new_session5);
        overall5 = findViewById(R.id.overall5);
        myDB = new DBHelper(this);

        new_session5 =(Button) findViewById(R.id.new_session5);
        new_session5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WorkoutActivity();
            }
        });

        overall5 =(Button) findViewById(R.id.overall5);
        overall5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OverallActivity();
            }
        });

        view_workout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDB.viewworkoutdata();
                if(res.getCount()==0){
                    Toast.makeText(EliteActivity.this,"No Entry Found",Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append(" which_exercise :"+res.getString(0)+"\n");
                    buffer.append(" target_weight :"+res.getString(1)+"\n");
                    buffer.append(" current_weight :"+res.getString(2)+"\n");
                    buffer.append(" target_sets :"+res.getString(3)+"\n");
                    buffer.append(" current_sets :"+res.getString(4)+"\n");
                    buffer.append(" target_reps :"+res.getString(5)+"\n");
                    buffer.append(" current_reps :"+res.getString(6)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(EliteActivity.this);
                builder.setCancelable(true);
                builder.setTitle(" Workouttracking ");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }
    public void WorkoutActivity(){
        Intent w = new Intent(this,WorkoutActivity.class);
        startActivity(w);
    }

    public void OverallActivity(){
        Intent o = new Intent(this,OverallActivity.class);
        startActivity(o);
    }
}
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

import com.example.bwfitness.databinding.ActivityAdvancedBinding;

public class AdvancedActivity extends NavDrawerBaseActivity {

    ActivityAdvancedBinding activityAdvancedBinding;

    EditText which_exercise, target_weight , current_weight , target_sets , current_sets , target_reps, current_reps;

    Button view_workout , new_session4 , overall4;

    DBHelper myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAdvancedBinding = activityAdvancedBinding.inflate(getLayoutInflater());
        setContentView(activityAdvancedBinding.getRoot());
        allocateActivityTitle("Advanced Advice");

        which_exercise = findViewById(R.id.which_exercise);
        target_weight = findViewById(R.id.target_weight);
        current_weight = findViewById(R.id.current_weight);
        target_sets = findViewById(R.id.target_sets);
        current_sets = findViewById(R.id.current_sets);
        target_reps = findViewById(R.id.target_reps);
        current_reps = findViewById(R.id.current_reps);
        view_workout = findViewById(R.id.view_workout);
        new_session4 = findViewById(R.id.new_session4);
        overall4 = findViewById(R.id.overall4);
        myDB = new DBHelper(this);

        new_session4 =(Button) findViewById(R.id.new_session4);
        new_session4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WorkoutActivity();
            }
        });

        overall4 =(Button) findViewById(R.id.overall4);
        overall4.setOnClickListener(new View.OnClickListener() {
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
                    Toast.makeText(AdvancedActivity.this,"No Entry Found",Toast.LENGTH_SHORT).show();
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

                AlertDialog.Builder builder = new AlertDialog.Builder(AdvancedActivity.this);
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
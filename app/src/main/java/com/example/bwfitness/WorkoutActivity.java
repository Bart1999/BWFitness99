package com.example.bwfitness;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bwfitness.databinding.ActivityMembershipBinding;
import com.example.bwfitness.databinding.ActivityWorkoutBinding;

public class WorkoutActivity extends NavDrawerBaseActivity {

    ActivityWorkoutBinding activityWorkoutBinding;

    EditText which_exercise, target_weight , current_weight , target_sets , current_sets , target_reps, current_reps;

    Button insert_workout, update_workout , view_workout , delete_workout;

    DBHelper myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityWorkoutBinding = ActivityWorkoutBinding.inflate(getLayoutInflater());
        setContentView(activityWorkoutBinding.getRoot());
        allocateActivityTitle("Workout Tracking");

        ActionBar actionBar = getSupportActionBar();

        which_exercise = findViewById(R.id.which_exercise);
        target_weight = findViewById(R.id.target_weight);
        current_weight = findViewById(R.id.current_weight);
        target_sets = findViewById(R.id.target_sets);
        current_sets = findViewById(R.id.current_sets);
        target_reps = findViewById(R.id.target_reps);
        current_reps = findViewById(R.id.current_reps);

        insert_workout = findViewById(R.id.insert_workout);
        update_workout = findViewById(R.id.update_workout);
        view_workout = findViewById(R.id.view_workout);
        delete_workout = findViewById(R.id.delete_workout);

        myDB = new DBHelper(this);

        insert_workout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String whichexerciseTXT = which_exercise.getText().toString();
                String targetweightTXT = target_weight.getText().toString();
                String currentweightTXT = current_weight.getText().toString();
                String targetsetsTXT = target_sets.getText().toString();
                String currentsetsTXT = current_sets.getText().toString();
                String targetrepsTXT = target_reps.getText().toString();
                String currentrepsTXT = current_reps.getText().toString();


                Boolean checkworkoutdata = myDB.insertworkoutdata( whichexerciseTXT , targetweightTXT , currentweightTXT , targetsetsTXT , currentsetsTXT , targetrepsTXT , currentrepsTXT );
                if(checkworkoutdata==true) {
                    Toast.makeText(WorkoutActivity.this,"New Entry Inserted",Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(WorkoutActivity.this,"Entry Not Inserted",Toast.LENGTH_SHORT).show();
            }
        });

        update_workout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String whichexerciseTXT = which_exercise.getText().toString();
                String targetweightTXT = target_weight.getText().toString();
                String currentweightTXT = current_weight.getText().toString();
                String targetsetsTXT = target_sets.getText().toString();
                String currentsetsTXT = current_sets.getText().toString();
                String targetrepsTXT = target_reps.getText().toString();
                String currentrepsTXT = current_reps.getText().toString();


                Boolean checkupdateworkout = myDB.updateworkoutdata( whichexerciseTXT , targetweightTXT , currentweightTXT , targetsetsTXT , currentsetsTXT , targetrepsTXT , currentrepsTXT );
                if(checkupdateworkout==true) {
                    Toast.makeText(WorkoutActivity.this,"Workout Updated",Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(WorkoutActivity.this,"Workout Did Not Update",Toast.LENGTH_SHORT).show();
            }
        });

        delete_workout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String whichexerciseTXT = which_exercise.getText().toString();

                Boolean checkdeleteworkoutdata = myDB.deleteworkoutdata( whichexerciseTXT );
                if(checkdeleteworkoutdata==true) {
                    Toast.makeText(WorkoutActivity.this,"Entry Deleted",Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(WorkoutActivity.this,"Entry Did Not Delete",Toast.LENGTH_SHORT).show();
            }
        });

        view_workout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDB.viewworkoutdata();
                if(res.getCount()==0){
                    Toast.makeText(WorkoutActivity.this,"No Entry Found",Toast.LENGTH_SHORT).show();
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

                AlertDialog.Builder builder = new AlertDialog.Builder(WorkoutActivity.this);
                builder.setCancelable(true);
                builder.setTitle(" Workouttracking ");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }
    }

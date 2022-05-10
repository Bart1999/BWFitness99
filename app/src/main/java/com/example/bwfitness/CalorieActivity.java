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

import com.example.bwfitness.databinding.ActivityCalorieBinding;
import com.example.bwfitness.databinding.ActivityGymProgressBinding;

public class CalorieActivity extends NavDrawerBaseActivity {

    ActivityCalorieBinding activityCalorieBinding;
    EditText target_calories,current_calories,target_protein,current_protein,target_carbs,
    current_carbs,target_fats,current_fats;

    Button insert_calorie,update_calorie,delete_calorie,view_calorie;

    DBHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityCalorieBinding = ActivityCalorieBinding.inflate(getLayoutInflater());
        setContentView(activityCalorieBinding.getRoot());
        allocateActivityTitle("Calorie Tracking");

        ActionBar actionBar = getSupportActionBar();

        target_calories=findViewById(R.id.target_calories);
        current_calories=findViewById(R.id.current_calories);
        target_protein=findViewById(R.id.target_protein);
        current_protein=findViewById(R.id.current_protein);
        target_carbs=findViewById(R.id.target_carbs);
        current_carbs=findViewById(R.id.current_carbs);
        target_fats=findViewById(R.id.target_fats);
        current_fats=findViewById(R.id.current_fats);
        insert_calorie=findViewById(R.id.insert_calorie);
        update_calorie=findViewById(R.id.update_calorie);
        delete_calorie=findViewById(R.id.delete_calorie);
        view_calorie=findViewById(R.id.view_calorie);
        myDB= new DBHelper(this);

        insert_calorie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String targetcaloriesTXT = target_calories.getText().toString();
                String currentcaloriesTXT = current_calories.getText().toString();
                String targetproteinTXT = target_protein.getText().toString();
                String currentproteinTXT = current_protein.getText().toString();
                String targetcarbsTXT = target_carbs.getText().toString();
                String currentcarbsTXT = current_carbs.getText().toString();
                String targetfatsTXT = target_fats.getText().toString();
                String currentfatsTXT = current_fats.getText().toString();

                Boolean checkinsertcaloriedata = myDB.insertcaloriedata(targetcaloriesTXT,currentcaloriesTXT,targetproteinTXT,currentproteinTXT,targetcarbsTXT,currentcarbsTXT,targetfatsTXT,currentfatsTXT);
                if(checkinsertcaloriedata==true) {
                    Toast.makeText(CalorieActivity.this,"New Entry Inserted",Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(CalorieActivity.this,"Entry Not Inserted",Toast.LENGTH_SHORT).show();
                }
            });

        update_calorie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String targetcaloriesTXT = target_calories.getText().toString();
                String currentcaloriesTXT = current_calories.getText().toString();
                String targetproteinTXT = target_protein.getText().toString();
                String currentproteinTXT = current_protein.getText().toString();
                String targetcarbsTXT = target_carbs.getText().toString();
                String currentcarbsTXT = current_carbs.getText().toString();
                String targetfatsTXT = target_fats.getText().toString();
                String currentfatsTXT = current_fats.getText().toString();

                Boolean checkupdatecaloriedata = myDB.updatecaloriedata(targetcaloriesTXT,currentcaloriesTXT,targetproteinTXT,currentproteinTXT,targetcarbsTXT,currentcarbsTXT,targetfatsTXT,currentfatsTXT);
                if(checkupdatecaloriedata==true) {
                    Toast.makeText(CalorieActivity.this,"New Entry Updated",Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(CalorieActivity.this,"Entry Did Not Update",Toast.LENGTH_SHORT).show();
            }
        });
        delete_calorie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String targetcaloriesTXT = target_calories.getText().toString();
                Boolean checkdeletecaloriedata = myDB.deletecaloriedata(targetcaloriesTXT);
                if(checkdeletecaloriedata==true) {
                    Toast.makeText(CalorieActivity.this,"Entry Deleted",Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(CalorieActivity.this,"Entry Did Not Delete",Toast.LENGTH_SHORT).show();
            }
        });

        view_calorie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDB.viewcaloriedata();
                if(res.getCount()==0){
                    Toast.makeText(CalorieActivity.this,"No Entry Exists",Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("target_calories :"+res.getString(0)+"\n");
                    buffer.append("current_calories :"+res.getString(1)+"\n");
                    buffer.append("target_protein :"+res.getString(2)+"\n");
                    buffer.append("current_protein :"+res.getString(3)+"\n");
                    buffer.append("target_carbs :"+res.getString(4)+"\n");
                    buffer.append("current_carbs :"+res.getString(5)+"\n");
                    buffer.append("target_fats :"+res.getString(6)+"\n");
                    buffer.append("current_fats :"+res.getString(7)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(CalorieActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Calorietracking");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
        }
}
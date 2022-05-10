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

import com.example.bwfitness.databinding.ActivityCalorieBinding;
import com.example.bwfitness.databinding.ActivityCuttingAdviceBinding;

public class CuttingAdviceActivity extends NavDrawerBaseActivity {

    ActivityCuttingAdviceBinding activityCuttingAdviceBinding;

    EditText target_calories,current_calories,target_protein,current_protein,target_carbs,
            current_carbs,target_fats,current_fats;

    Button view_calorie,new_cut;

    DBHelper myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityCuttingAdviceBinding = activityCuttingAdviceBinding.inflate(getLayoutInflater());
        setContentView(activityCuttingAdviceBinding.getRoot());
        allocateActivityTitle("Cutting Advice");

        target_calories=findViewById(R.id.target_calories);
        current_calories=findViewById(R.id.current_calories);
        target_protein=findViewById(R.id.target_protein);
        current_protein=findViewById(R.id.current_protein);
        target_carbs=findViewById(R.id.target_carbs);
        current_carbs=findViewById(R.id.current_carbs);
        target_fats=findViewById(R.id.target_fats);
        current_fats=findViewById(R.id.current_fats);
        view_calorie=findViewById(R.id.view_calorie);
        new_cut=findViewById(R.id.new_cut);
        myDB= new DBHelper(this);

        new_cut =(Button) findViewById(R.id.new_cut);
        new_cut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalorieActivity();
            }
        });

        view_calorie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDB.viewcaloriedata();
                if(res.getCount()==0){
                    Toast.makeText(CuttingAdviceActivity.this,"No Entry Exists",Toast.LENGTH_SHORT).show();
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

                AlertDialog.Builder builder = new AlertDialog.Builder(CuttingAdviceActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Calorietracking");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }
    public void CalorieActivity(){
        Intent c = new Intent(this,CalorieActivity.class);
        startActivity(c);
    }
}
package com.example.bwfitness;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bwfitness.databinding.ActivityJamesReidBinding;
import com.example.bwfitness.databinding.ActivitySarahReamBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class JamesReidActivity extends NavDrawerBaseActivity {

    ActivityJamesReidBinding activityJamesReidBinding;
    Calendar myCalendar4;
    EditText start_pt4, end_pt4;
    String target_performance,pt_length;
    Spinner JamesSpinner1,JamesSpinner2;
    Button btnBookJames,btnViewJames,btnPayJames;
    DBHelper myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityJamesReidBinding = ActivityJamesReidBinding.inflate(getLayoutInflater());
        setContentView(activityJamesReidBinding.getRoot());
        allocateActivityTitle("James Reid Booking");

        Intent i = getIntent();
        target_performance=i.getStringExtra("Fat Loss or Muscle Gain?");
        pt_length=i.getStringExtra("How long do you want to train with this PT?");

        myCalendar4 = Calendar.getInstance();

        start_pt4 = (EditText) findViewById(R.id.start_pt4);
        end_pt4 = (EditText) findViewById(R.id.end_pt4);
        JamesSpinner1 = findViewById(R.id.JamesSpinner1);
        JamesSpinner2 = findViewById(R.id.JamesSpinner2);
        btnBookJames = findViewById(R.id.btnBookJames);
        btnViewJames = findViewById(R.id.btnViewJames);
        btnPayJames = findViewById(R.id.btnPayJames);
        myDB = new DBHelper(this);

        btnPayJames =(Button) findViewById(R.id.btnPayJames);
        btnPayJames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Payment_Activity();
            }
        });

        btnBookJames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String start_pt4TXT = start_pt4.getText().toString();
                String end_pt4TXT = end_pt4.getText().toString();
                String JamesSpinner1TXT = JamesSpinner1.getSelectedItem().toString();
                String JamesSpinner2TXT = JamesSpinner2.getSelectedItem().toString();


                Boolean checkjamesdata = myDB.insertjamesdata( start_pt4TXT , end_pt4TXT , JamesSpinner1TXT , JamesSpinner2TXT );
                if(checkjamesdata==true) {
                    Toast.makeText(JamesReidActivity.this,"Booking Successful",Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(JamesReidActivity.this,"Booking Unsuccessful",Toast.LENGTH_SHORT).show();
            }
        });

        btnViewJames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDB.viewjamesdata();
                if(res.getCount()==0){
                    Toast.makeText(JamesReidActivity.this,"No Entry Found",Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append(" Start Date :"+res.getInt(0)+"\n");
                    buffer.append(" End Date :"+res.getInt(1)+"\n");
                    buffer.append(" Start Time :"+res.getInt(2)+"\n");
                    buffer.append(" End Time :"+res.getInt(3)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(JamesReidActivity.this);
                builder.setCancelable(true);
                builder.setTitle(" James Reid ");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar4.set(Calendar.YEAR,year);
                myCalendar4.set(Calendar.MONTH, month);
                myCalendar4.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                updateLabel4(myCalendar4, start_pt4);
            }
        };

        start_pt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(JamesReidActivity.this,date,myCalendar4
                        .get(Calendar.YEAR),myCalendar4.get(Calendar.MONTH),
                        myCalendar4.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        DatePickerDialog.OnDateSetListener date2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar4.set(Calendar.YEAR,year);
                myCalendar4.set(Calendar.MONTH, month);
                myCalendar4.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                updateLabel4(myCalendar4, end_pt4);
            }
        };

        end_pt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(JamesReidActivity.this,date2,myCalendar4
                        .get(Calendar.YEAR),myCalendar4.get(Calendar.MONTH),
                        myCalendar4.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void updateLabel4(Calendar myCalendar4,EditText start_pt4) {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.UK);

        start_pt4.setText(sdf.format(myCalendar4.getTime()));
    }

    public void Payment_Activity(){
        Intent p = new Intent(this,Payment_Activity.class);
        startActivity(p);
    }
}
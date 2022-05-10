package com.example.bwfitness;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;

import com.example.bwfitness.databinding.ActivityMikeCallisBinding;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MikeCallisActivity extends NavDrawerBaseActivity {

    ActivityMikeCallisBinding activityMikeCallisBinding;
    Calendar myCalendar;
    EditText start_pt, end_pt;
    String target_performance,pt_length;
    Spinner MikeSpinner1,MikeSpinner2;
    Button btnBookMike,btnViewMike,btnPayMike;
    DBHelper myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMikeCallisBinding = ActivityMikeCallisBinding.inflate(getLayoutInflater());
        setContentView(activityMikeCallisBinding.getRoot());
        allocateActivityTitle("Mike Callis Booking");

        Intent i = getIntent();
        target_performance=i.getStringExtra("Fat Loss or Muscle Gain?");
        pt_length=i.getStringExtra("How long do you want to train with this PT?");

        myCalendar = Calendar.getInstance();

        start_pt = (EditText) findViewById(R.id.start_pt);
        end_pt = (EditText) findViewById(R.id.end_pt);
        MikeSpinner1 = findViewById(R.id.MikeSpinner1);
        MikeSpinner2 = findViewById(R.id.MikeSpinner2);
        btnBookMike = findViewById(R.id.btnBookMike);
        btnViewMike = findViewById(R.id.btnViewMike);
        btnPayMike = findViewById(R.id.btnPayMike);
        myDB = new DBHelper(this);

        //btnBookMike.setOnClickListener(new View.OnClickListener() {
            //@Override
            //public void onClick(View v) {
                //Intent i=new Intent(MikeCallisActivity.this,Payment_Activity.class);
                //i.putExtra("Fat Loss or Muscle Gain?",target_performance);
                //i.putExtra("How long do you want to train with this PT?",pt_length);
                //i.putExtra("Start Time", start_pt.getText().toString());
                //i.putExtra("End Time", end_pt.getText().toString());
                //startActivity(i);
            //}
        //});

        btnPayMike =(Button) findViewById(R.id.btnPayMike);
        btnPayMike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Payment_Activity();
            }
        });
        btnBookMike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String start_ptTXT = start_pt.getText().toString();
                String end_ptTXT = end_pt.getText().toString();
                String MikeSpinner1TXT = MikeSpinner1.getSelectedItem().toString();
                String MikeSpinner2TXT = MikeSpinner2.getSelectedItem().toString();


                Boolean checkinsertmikedata = myDB.insertmikedata( start_ptTXT , end_ptTXT , MikeSpinner1TXT , MikeSpinner2TXT );
                if(checkinsertmikedata==true) {
                    Toast.makeText(MikeCallisActivity.this,"Booking Successful",Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(MikeCallisActivity.this,"Booking Unsuccessful",Toast.LENGTH_SHORT).show();
            }
        });

        btnViewMike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDB.viewmikedata();
                if(res.getCount()==0){
                    Toast.makeText(MikeCallisActivity.this,"No Entry Found",Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append(" Start Date :"+res.getInt(0)+"\n");
                    buffer.append(" End Date :"+res.getInt(1)+"\n");
                    buffer.append(" Start Time :"+res.getInt(2)+"\n");
                    buffer.append(" End Time :"+res.getInt(3)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MikeCallisActivity.this);
                builder.setCancelable(true);
                builder.setTitle(" Mike Callis ");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                 myCalendar.set(Calendar.YEAR,year);
                 myCalendar.set(Calendar.MONTH, month);
                 myCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                 updateLabel(myCalendar, start_pt);
            }
        };

        start_pt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(MikeCallisActivity.this,date,myCalendar
                        .get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });



        DatePickerDialog.OnDateSetListener date2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR,year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                updateLabel(myCalendar, end_pt);
            }
        };

        end_pt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(MikeCallisActivity.this,date2,myCalendar
                        .get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }
    private void updateLabel(Calendar myCalendar,EditText start_pt) {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.UK);

        start_pt.setText(sdf.format(myCalendar.getTime()));
    }

    public void Payment_Activity(){
        Intent p = new Intent(this,Payment_Activity.class);
        startActivity(p);
    }
}
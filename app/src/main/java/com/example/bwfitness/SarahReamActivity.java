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

import com.example.bwfitness.databinding.ActivityPtactivityBinding;
import com.example.bwfitness.databinding.ActivitySarahReamBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class SarahReamActivity extends NavDrawerBaseActivity {

    ActivitySarahReamBinding activitySarahReamBinding;
    Calendar myCalendar5;
    EditText start_pt5, end_pt5;
    String target_performance,pt_length;
    Spinner SarahSpinner1,SarahSpinner2;
    Button btnBookSarah,btnViewSarah,btnPaySarah;
    DBHelper myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySarahReamBinding = ActivitySarahReamBinding.inflate(getLayoutInflater());
        setContentView(activitySarahReamBinding.getRoot());
        allocateActivityTitle("Sarah Ream Booking");

        Intent i = getIntent();
        target_performance=i.getStringExtra("Fat Loss or Muscle Gain?");
        pt_length=i.getStringExtra("How long do you want to train with this PT?");

        myCalendar5 = Calendar.getInstance();

        start_pt5 = (EditText) findViewById(R.id.start_pt5);
        end_pt5 = (EditText) findViewById(R.id.end_pt5);
        SarahSpinner1 = findViewById(R.id.SarahSpinner1);
        SarahSpinner2 = findViewById(R.id.SarahSpinner2);
        btnBookSarah = findViewById(R.id.btnBookSarah);
        btnViewSarah = findViewById(R.id.btnViewSarah);
        btnPaySarah = findViewById(R.id.btnPaySarah);
        myDB = new DBHelper(this);

        btnPaySarah =(Button) findViewById(R.id.btnPaySarah);
        btnPaySarah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Payment_Activity();
            }
        });

        btnBookSarah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String start_pt5TXT = start_pt5.getText().toString();
                String end_pt5TXT = end_pt5.getText().toString();
                String SarahSpinner1TXT = SarahSpinner1.getSelectedItem().toString();
                String SarahSpinner2TXT = SarahSpinner2.getSelectedItem().toString();


                Boolean checksarahdata = myDB.insertsarahdata( start_pt5TXT , end_pt5TXT , SarahSpinner1TXT , SarahSpinner2TXT );
                if(checksarahdata==true) {
                    Toast.makeText(SarahReamActivity.this,"Booking Successful",Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(SarahReamActivity.this,"Booking Unsuccessful",Toast.LENGTH_SHORT).show();
            }
        });

        btnViewSarah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDB.viewsarahdata();
                if(res.getCount()==0){
                    Toast.makeText(SarahReamActivity.this,"No Entry Found",Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append(" Start Date :"+res.getInt(0)+"\n");
                    buffer.append(" End Date :"+res.getInt(1)+"\n");
                    buffer.append(" Start Time :"+res.getInt(2)+"\n");
                    buffer.append(" End Time :"+res.getInt(3)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(SarahReamActivity.this);
                builder.setCancelable(true);
                builder.setTitle(" Sarah Ream ");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar5.set(Calendar.YEAR,year);
                myCalendar5.set(Calendar.MONTH, month);
                myCalendar5.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                updateLabel5(myCalendar5, start_pt5);
            }
        };

        start_pt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(SarahReamActivity.this,date,myCalendar5
                        .get(Calendar.YEAR),myCalendar5.get(Calendar.MONTH),
                        myCalendar5.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        DatePickerDialog.OnDateSetListener date2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar5.set(Calendar.YEAR,year);
                myCalendar5.set(Calendar.MONTH, month);
                myCalendar5.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                updateLabel5(myCalendar5, end_pt5);
            }
        };

        end_pt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(SarahReamActivity.this,date2,myCalendar5
                        .get(Calendar.YEAR),myCalendar5.get(Calendar.MONTH),
                        myCalendar5.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void updateLabel5(Calendar myCalendar5,EditText start_pt5) {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.UK);

        start_pt5.setText(sdf.format(myCalendar5.getTime()));
    }

    public void Payment_Activity(){
        Intent p = new Intent(this,Payment_Activity.class);
        startActivity(p);
    }
}
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

import com.example.bwfitness.databinding.ActivityChloeNolanBinding;
import com.example.bwfitness.databinding.ActivityJamesReidBinding;
import com.example.bwfitness.databinding.ActivitySarahReamBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ChloeNolanActivity extends NavDrawerBaseActivity {

    ActivityChloeNolanBinding activityChloeNolanBinding;
    Calendar myCalendar8;
    EditText start_pt8, end_pt8;
    String target_performance,pt_length;
    Spinner ChloeSpinner1,ChloeSpinner2;
    Button btnBookChloe,btnViewChloe,btnPayChloe;
    DBHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityChloeNolanBinding = ActivityChloeNolanBinding.inflate(getLayoutInflater());
        setContentView(activityChloeNolanBinding.getRoot());
        allocateActivityTitle("Chloe Nolan Booking");

        Intent i = getIntent();
        target_performance=i.getStringExtra("Fat Loss or Muscle Gain?");
        pt_length=i.getStringExtra("How long do you want to train with this PT?");

        myCalendar8 = Calendar.getInstance();

        start_pt8 = (EditText) findViewById(R.id.start_pt8);
        end_pt8 = (EditText) findViewById(R.id.end_pt8);
        ChloeSpinner1 = findViewById(R.id.ChloeSpinner1);
        ChloeSpinner2 = findViewById(R.id.ChloeSpinner2);
        btnBookChloe = findViewById(R.id.btnBookChloe);
        btnViewChloe = findViewById(R.id.btnViewChloe);
        btnPayChloe = findViewById(R.id.btnPayChloe);
        myDB = new DBHelper(this);

        btnPayChloe =(Button) findViewById(R.id.btnPayChloe);
        btnPayChloe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Payment_Activity();
            }
        });

        btnBookChloe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String start_pt8TXT = start_pt8.getText().toString();
                String end_pt8TXT = end_pt8.getText().toString();
                String ChloeSpinner1TXT = ChloeSpinner1.getSelectedItem().toString();
                String ChloeSpinner2TXT = ChloeSpinner2.getSelectedItem().toString();


                Boolean checkchloedata = myDB.insertchloedata( start_pt8TXT , end_pt8TXT , ChloeSpinner1TXT , ChloeSpinner2TXT );
                if(checkchloedata==true) {
                    Toast.makeText(ChloeNolanActivity.this,"Booking Successful",Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(ChloeNolanActivity.this,"Booking Unsuccessful",Toast.LENGTH_SHORT).show();
            }
        });

        btnViewChloe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDB.viewchloedata();
                if(res.getCount()==0){
                    Toast.makeText(ChloeNolanActivity.this,"No Entry Found",Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append(" Start Date :"+res.getInt(0)+"\n");
                    buffer.append(" End Date :"+res.getInt(1)+"\n");
                    buffer.append(" Start Time :"+res.getInt(2)+"\n");
                    buffer.append(" End Time :"+res.getInt(3)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(ChloeNolanActivity.this);
                builder.setCancelable(true);
                builder.setTitle(" Chloe Nolan ");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar8.set(Calendar.YEAR,year);
                myCalendar8.set(Calendar.MONTH, month);
                myCalendar8.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                updateLabel8(myCalendar8, start_pt8);
            }
        };

        start_pt8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(ChloeNolanActivity.this,date,myCalendar8
                        .get(Calendar.YEAR),myCalendar8.get(Calendar.MONTH),
                        myCalendar8.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        DatePickerDialog.OnDateSetListener date2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar8.set(Calendar.YEAR,year);
                myCalendar8.set(Calendar.MONTH, month);
                myCalendar8.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                updateLabel8(myCalendar8, end_pt8);
            }
        };

        end_pt8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(ChloeNolanActivity.this,date2,myCalendar8
                        .get(Calendar.YEAR),myCalendar8.get(Calendar.MONTH),
                        myCalendar8.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void updateLabel8(Calendar myCalendar8, EditText start_pt8) {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.UK);

        start_pt8.setText(sdf.format(myCalendar8.getTime()));
    }

    public void Payment_Activity(){
        Intent p = new Intent(this,Payment_Activity.class);
        startActivity(p);
    }
}
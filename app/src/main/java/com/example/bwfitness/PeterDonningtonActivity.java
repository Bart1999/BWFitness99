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

import com.example.bwfitness.databinding.ActivityPeterDonningtonBinding;
import com.example.bwfitness.databinding.ActivitySarahReamBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class PeterDonningtonActivity extends NavDrawerBaseActivity {

    ActivityPeterDonningtonBinding activityPeterDonningtonBinding;
    Calendar myCalendar3;
    EditText start_pt3, end_pt3;
    String target_performance,pt_length;
    Spinner PeterSpinner1,PeterSpinner2;
    Button btnBookPeter,btnViewPeter,btnPayPeter;
    DBHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityPeterDonningtonBinding = ActivityPeterDonningtonBinding.inflate(getLayoutInflater());
        setContentView(activityPeterDonningtonBinding.getRoot());
        allocateActivityTitle("Peter Donnington Booking");

        Intent i = getIntent();
        target_performance=i.getStringExtra("Fat Loss or Muscle Gain?");
        pt_length=i.getStringExtra("How long do you want to train with this PT?");

        myCalendar3 = Calendar.getInstance();

        start_pt3 = (EditText) findViewById(R.id.start_pt3);
        end_pt3 = (EditText) findViewById(R.id.end_pt3);
        PeterSpinner1 = findViewById(R.id.PeterSpinner1);
        PeterSpinner2 = findViewById(R.id.PeterSpinner2);
        btnBookPeter = findViewById(R.id.btnBookPeter);
        btnViewPeter = findViewById(R.id.btnViewPeter);
        btnPayPeter = findViewById(R.id.btnPayPeter);
        myDB = new DBHelper(this);

        btnPayPeter =(Button) findViewById(R.id.btnPayPeter);
        btnPayPeter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Payment_Activity();
            }
        });

        btnBookPeter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String start_pt3TXT = start_pt3.getText().toString();
                String end_pt3TXT = end_pt3.getText().toString();
                String PeterSpinner1TXT = PeterSpinner1.getSelectedItem().toString();
                String PeterSpinner2TXT = PeterSpinner2.getSelectedItem().toString();


                Boolean checkpeterdata = myDB.insertpeterdata( start_pt3TXT , end_pt3TXT , PeterSpinner1TXT , PeterSpinner2TXT );
                if(checkpeterdata==true) {
                    Toast.makeText(PeterDonningtonActivity.this,"Booking Successful",Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(PeterDonningtonActivity.this,"Booking Unsuccessful",Toast.LENGTH_SHORT).show();
            }
        });

        btnViewPeter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDB.viewpeterdata();
                if(res.getCount()==0){
                    Toast.makeText(PeterDonningtonActivity.this,"No Entry Found",Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append(" Start Date :"+res.getInt(0)+"\n");
                    buffer.append(" End Date :"+res.getInt(1)+"\n");
                    buffer.append(" Start Time :"+res.getInt(2)+"\n");
                    buffer.append(" End Time :"+res.getInt(3)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(PeterDonningtonActivity.this);
                builder.setCancelable(true);
                builder.setTitle(" Peter Donnington ");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar3.set(Calendar.YEAR,year);
                myCalendar3.set(Calendar.MONTH, month);
                myCalendar3.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                updateLabel3(myCalendar3, start_pt3);
            }
        };

        start_pt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(PeterDonningtonActivity.this,date,myCalendar3
                        .get(Calendar.YEAR),myCalendar3.get(Calendar.MONTH),
                        myCalendar3.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        DatePickerDialog.OnDateSetListener date2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar3.set(Calendar.YEAR,year);
                myCalendar3.set(Calendar.MONTH, month);
                myCalendar3.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                updateLabel3(myCalendar3, end_pt3);
            }
        };

        end_pt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(PeterDonningtonActivity.this,date2,myCalendar3
                        .get(Calendar.YEAR),myCalendar3.get(Calendar.MONTH),
                        myCalendar3.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void updateLabel3(Calendar myCalendar3,EditText start_pt3) {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.UK);

        start_pt3.setText(sdf.format(myCalendar3.getTime()));
    }

    public void Payment_Activity(){
        Intent p = new Intent(this,Payment_Activity.class);
        startActivity(p);
    }
}
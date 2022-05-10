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

import com.example.bwfitness.databinding.ActivityKyleVanceBinding;
import com.example.bwfitness.databinding.ActivityKylieWallBinding;
import com.example.bwfitness.databinding.ActivitySarahReamBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class KyleVanceActivity extends NavDrawerBaseActivity{

    ActivityKyleVanceBinding activityKyleVanceBinding;
    Calendar myCalendar2;
    EditText start_pt2, end_pt2;
    String target_performance,pt_length;
    Spinner KyleSpinner1,KyleSpinner2;
    Button btnBookKyle,btnViewKyle,btnPayKyle;
    DBHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityKyleVanceBinding = activityKyleVanceBinding.inflate(getLayoutInflater());
        setContentView(activityKyleVanceBinding.getRoot());
        allocateActivityTitle("Kyle Vance Booking");

        Intent i = getIntent();
        target_performance=i.getStringExtra("Fat Loss or Muscle Gain?");
        pt_length=i.getStringExtra("How long do you want to train with this PT?");

        myCalendar2 = Calendar.getInstance();

        start_pt2 = (EditText) findViewById(R.id.start_pt2);
        end_pt2 = (EditText) findViewById(R.id.end_pt2);
        KyleSpinner1 = findViewById(R.id.KyleSpinner1);
        KyleSpinner2 = findViewById(R.id.KyleSpinner2);
        btnBookKyle = findViewById(R.id.btnBookKyle);
        btnViewKyle = findViewById(R.id.btnViewKyle);
        btnPayKyle = findViewById(R.id.btnPayKyle);
        myDB = new DBHelper(this);

        btnPayKyle =(Button) findViewById(R.id.btnPayKyle);
        btnPayKyle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Payment_Activity();
            }
        });

        btnBookKyle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String start_pt2TXT = start_pt2.getText().toString();
                String end_pt2TXT = end_pt2.getText().toString();
                String KyleSpinner1TXT = KyleSpinner1.getSelectedItem().toString();
                String KyleSpinner2TXT = KyleSpinner2.getSelectedItem().toString();


                Boolean checkkyledata = myDB.insertkyledata( start_pt2TXT , end_pt2TXT , KyleSpinner1TXT , KyleSpinner2TXT );
                if(checkkyledata==true) {
                    Toast.makeText(KyleVanceActivity.this,"Booking Successful",Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(KyleVanceActivity.this,"Booking Unsuccessful",Toast.LENGTH_SHORT).show();
            }
        });

        btnViewKyle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDB.viewkyledata();
                if(res.getCount()==0){
                    Toast.makeText(KyleVanceActivity.this,"No Entry Found",Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append(" Start Date :"+res.getInt(0)+"\n");
                    buffer.append(" End Date :"+res.getInt(1)+"\n");
                    buffer.append(" Start Time :"+res.getInt(2)+"\n");
                    buffer.append(" End Time :"+res.getInt(3)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(KyleVanceActivity.this);
                builder.setCancelable(true);
                builder.setTitle(" Kyle Vance ");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar2.set(Calendar.YEAR,year);
                myCalendar2.set(Calendar.MONTH, month);
                myCalendar2.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                updateLabel2(myCalendar2, start_pt2);
            }
        };

        start_pt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(KyleVanceActivity.this,date,myCalendar2
                        .get(Calendar.YEAR),myCalendar2.get(Calendar.MONTH),
                        myCalendar2.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        DatePickerDialog.OnDateSetListener date2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar2.set(Calendar.YEAR,year);
                myCalendar2.set(Calendar.MONTH, month);
                myCalendar2.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                updateLabel2(myCalendar2, end_pt2);
            }
        };

        end_pt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(KyleVanceActivity.this,date2,myCalendar2
                        .get(Calendar.YEAR),myCalendar2.get(Calendar.MONTH),
                        myCalendar2.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void updateLabel2(Calendar myCalendar2,EditText start_pt2) {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.UK);

        start_pt2.setText(sdf.format(myCalendar2.getTime()));
    }
    public void Payment_Activity(){
        Intent p = new Intent(this,Payment_Activity.class);
        startActivity(p);
    }
}
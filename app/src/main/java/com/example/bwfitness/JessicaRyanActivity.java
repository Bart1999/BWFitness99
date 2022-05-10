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

import com.example.bwfitness.databinding.ActivityJessicaRyanBinding;
import com.example.bwfitness.databinding.ActivitySarahReamBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class JessicaRyanActivity extends NavDrawerBaseActivity {

    ActivityJessicaRyanBinding activityJessicaRyanBinding;
    Calendar myCalendar6;
    EditText start_pt6, end_pt6;
    String target_performance,pt_length;
    Spinner JessicaSpinner1,JessicaSpinner2;
    Button btnBookJessica,btnViewJessica,btnPayJessica;
    DBHelper myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityJessicaRyanBinding = ActivityJessicaRyanBinding.inflate(getLayoutInflater());
        setContentView(activityJessicaRyanBinding.getRoot());
        allocateActivityTitle("Jessica Ryan Booking");

        Intent i = getIntent();
        target_performance=i.getStringExtra("Fat Loss or Muscle Gain?");
        pt_length=i.getStringExtra("How long do you want to train with this PT?");

        myCalendar6 = Calendar.getInstance();

        start_pt6 = (EditText) findViewById(R.id.start_pt6);
        end_pt6 = (EditText) findViewById(R.id.end_pt6);
        JessicaSpinner1 = findViewById(R.id.JessicaSpinner1);
        JessicaSpinner2 = findViewById(R.id.JessicaSpinner2);
        btnBookJessica = findViewById(R.id.btnBookJessica);
        btnViewJessica = findViewById(R.id.btnViewJessica);
        btnPayJessica = findViewById(R.id.btnPayJessica);
        myDB = new DBHelper(this);

        btnPayJessica =(Button) findViewById(R.id.btnPayJessica);
        btnPayJessica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Payment_Activity();
            }
        });

        btnBookJessica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String start_pt6TXT = start_pt6.getText().toString();
                String end_pt6TXT = end_pt6.getText().toString();
                String JessicaSpinner1TXT = JessicaSpinner1.getSelectedItem().toString();
                String JessicaSpinner2TXT = JessicaSpinner2.getSelectedItem().toString();


                Boolean checkjessicadata = myDB.insertjessicadata( start_pt6TXT , end_pt6TXT , JessicaSpinner1TXT , JessicaSpinner2TXT );
                if(checkjessicadata==true) {
                    Toast.makeText(JessicaRyanActivity.this,"Booking Successful",Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(JessicaRyanActivity.this,"Booking Unsuccessful",Toast.LENGTH_SHORT).show();
            }
        });

        btnViewJessica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDB.viewjessicadata();
                if(res.getCount()==0){
                    Toast.makeText(JessicaRyanActivity.this,"No Entry Found",Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append(" Start Date :"+res.getInt(0)+"\n");
                    buffer.append(" End Date :"+res.getInt(1)+"\n");
                    buffer.append(" Start Time :"+res.getInt(2)+"\n");
                    buffer.append(" End Time :"+res.getInt(3)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(JessicaRyanActivity.this);
                builder.setCancelable(true);
                builder.setTitle(" Jessica Ryan ");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar6.set(Calendar.YEAR,year);
                myCalendar6.set(Calendar.MONTH, month);
                myCalendar6.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                updateLabel6(myCalendar6, start_pt6);
            }
        };

        start_pt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(JessicaRyanActivity.this,date,myCalendar6
                        .get(Calendar.YEAR),myCalendar6.get(Calendar.MONTH),
                        myCalendar6.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        DatePickerDialog.OnDateSetListener date2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar6.set(Calendar.YEAR,year);
                myCalendar6.set(Calendar.MONTH, month);
                myCalendar6.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                updateLabel6(myCalendar6, end_pt6);
            }
        };

        end_pt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(JessicaRyanActivity.this,date2,myCalendar6
                        .get(Calendar.YEAR),myCalendar6.get(Calendar.MONTH),
                        myCalendar6.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void updateLabel6(Calendar myCalendar6, EditText start_pt6) {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.UK);

        start_pt6.setText(sdf.format(myCalendar6.getTime()));
    }

    public void Payment_Activity(){
        Intent p = new Intent(this,Payment_Activity.class);
        startActivity(p);
    }
}
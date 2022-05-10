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

import com.example.bwfitness.databinding.ActivityKylieWallBinding;
import com.example.bwfitness.databinding.ActivitySarahReamBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class KylieWallActivity extends NavDrawerBaseActivity {

    ActivityKylieWallBinding activityKylieWallBinding;
    Calendar myCalendar7;
    EditText start_pt7, end_pt7;
    String target_performance,pt_length;
    Spinner KylieSpinner1,KylieSpinner2;
    Button btnBookKylie,btnViewKylie,btnPayKylie;
    DBHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityKylieWallBinding = ActivityKylieWallBinding.inflate(getLayoutInflater());
        setContentView(activityKylieWallBinding.getRoot());
        allocateActivityTitle("Kylie Wall Booking");

        Intent i = getIntent();
        target_performance=i.getStringExtra("Fat Loss or Muscle Gain?");
        pt_length=i.getStringExtra("How long do you want to train with this PT?");

        myCalendar7 = Calendar.getInstance();

        start_pt7 = (EditText) findViewById(R.id.start_pt7);
        end_pt7 = (EditText) findViewById(R.id.end_pt7);
        KylieSpinner1 = findViewById(R.id.KylieSpinner1);
        KylieSpinner2 = findViewById(R.id.KylieSpinner2);
        btnBookKylie = findViewById(R.id.btnBookKylie);
        btnViewKylie = findViewById(R.id.btnViewKylie);
        btnPayKylie = findViewById(R.id.btnPayKylie);
        myDB = new DBHelper(this);

        btnPayKylie =(Button) findViewById(R.id.btnPayKylie);
        btnPayKylie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Payment_Activity();
            }
        });

        btnBookKylie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String start_pt7TXT = start_pt7.getText().toString();
                String end_pt7TXT = end_pt7.getText().toString();
                String KylieSpinner1TXT = KylieSpinner1.getSelectedItem().toString();
                String KylieSpinner2TXT = KylieSpinner2.getSelectedItem().toString();


                Boolean checkkyliedata = myDB.insertkyliedata( start_pt7TXT , end_pt7TXT , KylieSpinner1TXT , KylieSpinner2TXT );
                if(checkkyliedata==true) {
                    Toast.makeText(KylieWallActivity.this,"Booking Successful",Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(KylieWallActivity.this,"Booking Unsuccessful",Toast.LENGTH_SHORT).show();
            }
        });

        btnViewKylie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDB.viewkyliedata();
                if(res.getCount()==0){
                    Toast.makeText(KylieWallActivity.this,"No Entry Found",Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append(" Start Date :"+res.getInt(0)+"\n");
                    buffer.append(" End Date :"+res.getInt(1)+"\n");
                    buffer.append(" Start Time :"+res.getInt(2)+"\n");
                    buffer.append(" End Time :"+res.getInt(3)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(KylieWallActivity.this);
                builder.setCancelable(true);
                builder.setTitle(" Kylie Wall ");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar7.set(Calendar.YEAR,year);
                myCalendar7.set(Calendar.MONTH, month);
                myCalendar7.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                updateLabel7(myCalendar7, start_pt7);
            }
        };

        start_pt7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(KylieWallActivity.this,date,myCalendar7
                        .get(Calendar.YEAR),myCalendar7.get(Calendar.MONTH),
                        myCalendar7.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        DatePickerDialog.OnDateSetListener date2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar7.set(Calendar.YEAR,year);
                myCalendar7.set(Calendar.MONTH, month);
                myCalendar7.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                updateLabel7(myCalendar7, end_pt7);
            }
        };

        end_pt7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(KylieWallActivity.this,date2,myCalendar7
                        .get(Calendar.YEAR),myCalendar7.get(Calendar.MONTH),
                        myCalendar7.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void updateLabel7(Calendar myCalendar7, EditText start_pt7) {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.UK);

        start_pt7.setText(sdf.format(myCalendar7.getTime()));
    }

    public void Payment_Activity(){
        Intent p = new Intent(this,Payment_Activity.class);
        startActivity(p);
    }
}
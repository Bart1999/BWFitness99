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

import com.example.bwfitness.databinding.ActivityGenderBenchBinding;
import com.example.bwfitness.databinding.ActivityGymSlotBinding;

public class GymSlotActivity extends NavDrawerBaseActivity {

    ActivityGymSlotBinding activityGymSlotBinding;
    EditText which_day,start_time,end_time;

    Button insert_gymslot,update_gymslot,view_gymslot,delete_gymslot;

    DBHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityGymSlotBinding = ActivityGymSlotBinding.inflate(getLayoutInflater());
        setContentView(activityGymSlotBinding.getRoot());
        allocateActivityTitle("Gym Slot Booking");

        ActionBar actionBar = getSupportActionBar();

        which_day=findViewById(R.id.which_day);
        start_time=findViewById(R.id.start_time);
        end_time=findViewById(R.id.end_time);

        insert_gymslot=findViewById(R.id.insert_gymslot);
        update_gymslot=findViewById(R.id.update_gymslot);
        view_gymslot=findViewById(R.id.view_gymslot);
        delete_gymslot=findViewById(R.id.delete_gymslot);

        myDB = new DBHelper(this);

        insert_gymslot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String whichdayTXT = which_day.getText().toString();
                String starttimeTXT = start_time.getText().toString();
                String endtimeTXT = end_time.getText().toString();


                Boolean checkslotdata = myDB.insertgymslotdata( whichdayTXT , starttimeTXT , endtimeTXT );
                if(checkslotdata==true) {
                    Toast.makeText(GymSlotActivity.this,"New Entry Inserted",Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(GymSlotActivity.this,"Entry Not Inserted",Toast.LENGTH_SHORT).show();
            }
        });

        update_gymslot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String whichdayTXT = which_day.getText().toString();
                String starttimeTXT = start_time.getText().toString();
                String endtimeTXT = end_time.getText().toString();

                Boolean checkupdategymslotdata = myDB.updategymslotdata( whichdayTXT , starttimeTXT , endtimeTXT );
                if(checkupdategymslotdata==true) {
                    Toast.makeText(GymSlotActivity.this,"Entry Updated",Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(GymSlotActivity.this,"Entry Did Not Update",Toast.LENGTH_SHORT).show();
            }
        });

        delete_gymslot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String whichdayTXT = which_day.getText().toString();


                Boolean checkdeletegymslotdata = myDB.deletegymslotdata( whichdayTXT );
                if(checkdeletegymslotdata==true) {
                    Toast.makeText(GymSlotActivity.this,"Entry Deleted",Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(GymSlotActivity.this,"Entry Did Not Delete",Toast.LENGTH_SHORT).show();
            }
        });

        view_gymslot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDB.viewgymslotdata();
                if(res.getCount()==0){
                    Toast.makeText(GymSlotActivity.this,"No Entry Found",Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append(" which_day :"+res.getString(0)+"\n");
                    buffer.append(" start_time :"+res.getString(1)+"\n");
                    buffer.append(" end_time :"+res.getString(2)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(GymSlotActivity.this);
                builder.setCancelable(true);
                builder.setTitle(" Gymslot ");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }
}
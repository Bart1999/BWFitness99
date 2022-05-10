package com.example.bwfitness;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.bwfitness.adapter.PersonalTrainerAdapter;
import com.example.bwfitness.databinding.ActivityMembershipBinding;
import com.example.bwfitness.databinding.ActivityPtactivityBinding;
import com.example.bwfitness.model.PersonalTrainer;

import java.util.ArrayList;
import java.util.List;

public class PTActivity extends NavDrawerBaseActivity implements RecyclerViewInterface{

    ActivityPtactivityBinding activityPtactivityBinding;
    EditText target_performance,pt_length,pt_payment;

    Button insert_pt,update_pt,view_pt,delete_pt;

    DBHelper myDB;

    RecyclerView trainer_recycler;

    PersonalTrainerAdapter personalTrainerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityPtactivityBinding = ActivityPtactivityBinding.inflate(getLayoutInflater());
        setContentView(activityPtactivityBinding.getRoot());
        allocateActivityTitle("PT Booking");

        ActionBar actionBar = getSupportActionBar();

        target_performance=findViewById(R.id.target_performance);
        pt_length=findViewById(R.id.pt_length);
        pt_payment=findViewById(R.id.pt_payment);

        insert_pt=findViewById(R.id.insert_pt);
        update_pt=findViewById(R.id.update_pt);
        delete_pt=findViewById(R.id.delete_pt);
        view_pt=findViewById(R.id.view_pt);

        myDB = new DBHelper(this);

        insert_pt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String target_performanceTXT = target_performance.getText().toString();
                String pt_lengthTXT = pt_length.getText().toString();
                String pt_paymentTXT = pt_payment.getText().toString();


                Boolean checktrainerdata = myDB.inserttrainerdata( target_performanceTXT , pt_lengthTXT , pt_paymentTXT );
                if(checktrainerdata==true) {
                    Toast.makeText(PTActivity.this,"New Entry Inserted",Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(PTActivity.this,"Entry Not Inserted",Toast.LENGTH_SHORT).show();
            }
        });

        update_pt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String targetperformanceTXT = target_performance.getText().toString();
                String pt_lengthTXT = pt_length.getText().toString();
                String pt_paymentTXT = pt_payment.getText().toString();


                Boolean checkupdatetrainerdata = myDB.updatetrainerdata( targetperformanceTXT , pt_lengthTXT , pt_paymentTXT );
                if(checkupdatetrainerdata==true) {
                    Toast.makeText(PTActivity.this,"Entry Has Been Updated",Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(PTActivity.this,"Entry Did Not Update",Toast.LENGTH_SHORT).show();
            }
        });

        delete_pt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String targetperformanceTXT = target_performance.getText().toString();

                Boolean checkdeletetrainerdata = myDB.deletetrainerdata( targetperformanceTXT );
                if(checkdeletetrainerdata==true) {
                    Toast.makeText(PTActivity.this,"Entry Has Been Deleted",Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(PTActivity.this,"Entry Did Not Delete",Toast.LENGTH_SHORT).show();
            }
        });

        view_pt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDB.viewtrainerdata();
                if(res.getCount()==0){
                    Toast.makeText(PTActivity.this,"No Entry Found",Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append(" target_performance :"+res.getString(0)+"\n");
                    buffer.append(" pt_length :"+res.getString(1)+"\n");
                    buffer.append(" pt_payment :"+res.getString(2)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(PTActivity.this);
                builder.setCancelable(true);
                builder.setTitle(" PersonalTrainer ");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
        List<PersonalTrainer> personalTrainerList = new ArrayList<>();

        personalTrainerList.add(new PersonalTrainer("Mike Callis","€50/hour",R.drawable.pt_p1));
        personalTrainerList.add(new PersonalTrainer("Kyle Vance","€30/hour",R.drawable.pt_pt2));
        personalTrainerList.add(new PersonalTrainer("Peter Donnington","€40/hour", R.drawable.pt_p3));
        personalTrainerList.add(new PersonalTrainer("James Reid","€35/hour",R.drawable.pt_p4));
        personalTrainerList.add(new PersonalTrainer("Sarah Ream","€55/hour",R.drawable.pt_p5));
        personalTrainerList.add(new PersonalTrainer("Jessica Ryan","€40/hour",R.drawable.pt_p6));
        personalTrainerList.add(new PersonalTrainer("Kylie Wall","€40/hour",R.drawable.pt_p7));
        personalTrainerList.add(new PersonalTrainer("Chloe Nolan","€45/hour",R.drawable.pt_p8));

        setTrainerRecycler(personalTrainerList);
    }



    private void setTrainerRecycler(List<PersonalTrainer> personalTrainerList){

        trainer_recycler=findViewById(R.id.trainer_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        trainer_recycler.setLayoutManager(layoutManager);
        personalTrainerAdapter=new PersonalTrainerAdapter(this,personalTrainerList, this);
        trainer_recycler.setAdapter(personalTrainerAdapter);
    }

    @Override
    public void onItemClick(int position) {
        Intent i = new Intent(PTActivity.this, PTBookingActivity.class);
        startActivity(i);
    }
}
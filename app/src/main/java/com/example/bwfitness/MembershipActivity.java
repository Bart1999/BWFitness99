package com.example.bwfitness;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bwfitness.databinding.ActivityGenderBenchBinding;
import com.example.bwfitness.databinding.ActivityMembershipBinding;

public class MembershipActivity extends NavDrawerBaseActivity {

    ActivityMembershipBinding activityMembershipBinding;
    EditText membership_length, payment_method;

    Button insert_membership , update_membership , view_membership , delete_membership ;

    DBHelper myDB;

    private Button pay_membership;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMembershipBinding = ActivityMembershipBinding.inflate(getLayoutInflater());
        setContentView(activityMembershipBinding.getRoot());
        allocateActivityTitle("Gym Membership Booking");

        ActionBar actionBar = getSupportActionBar();

        pay_membership =(Button) findViewById(R.id.pay_membership);
        pay_membership.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Payment_Activity();
            }
        });

        membership_length=findViewById(R.id.membership_length);
        payment_method=findViewById(R.id.payment_method);

        insert_membership=findViewById(R.id.insert_membership);
        update_membership=findViewById(R.id.update_membership);
        delete_membership=findViewById(R.id.delete_membership);
        view_membership=findViewById(R.id.view_membership);

        myDB = new DBHelper(this);

        insert_membership.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String membershiplengthTXT = membership_length.getText().toString();
                String paymentmethodTXT = payment_method.getText().toString();


                Boolean checkmembershipdata = myDB.insertmembershipdata( membershiplengthTXT , paymentmethodTXT );
                if(checkmembershipdata==true) {
                    Toast.makeText(MembershipActivity.this,"New Entry Inserted",Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(MembershipActivity.this,"Entry Not Inserted",Toast.LENGTH_SHORT).show();
            }
        });

        update_membership.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String membershiplengthTXT = membership_length.getText().toString();
                String paymentmethodTXT = payment_method.getText().toString();


                Boolean checkupdatemembershipdata = myDB.updatemembershipdata( membershiplengthTXT , paymentmethodTXT );
                if(checkupdatemembershipdata==true) {
                    Toast.makeText(MembershipActivity.this,"Entry Has Been Updated",Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(MembershipActivity.this,"Entry Did Not Update",Toast.LENGTH_SHORT).show();
            }
        });

        delete_membership.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String membershiplengthTXT = membership_length.getText().toString();

                Boolean checkdeletemembershipdata = myDB.deletemembershipdata( membershiplengthTXT );
                if(checkdeletemembershipdata==true) {
                    Toast.makeText(MembershipActivity.this,"Entry Has Been Deleted",Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(MembershipActivity.this,"Entry Did Not Delete",Toast.LENGTH_SHORT).show();
            }
        });

        view_membership.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDB.viewmembershipdata();
                if(res.getCount()==0){
                    Toast.makeText(MembershipActivity.this,"No Entry Found",Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append(" membership_length :"+res.getString(0)+"\n");
                    buffer.append(" payment_method :"+res.getString(1)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MembershipActivity.this);
                builder.setCancelable(true);
                builder.setTitle(" Membership ");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }

    public void Payment_Activity(){
        Intent p = new Intent(this, Payment_Activity.class);
        startActivity(p);
    }
}
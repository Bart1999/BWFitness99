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

import com.example.bwfitness.databinding.ActivitySettingsBinding;

public class SettingsActivity extends NavDrawerBaseActivity {

    ActivitySettingsBinding activitySettingsBinding;
    EditText username,password,repassword,email,name,home,phonenumber;
    Button viewusername,updateuser,viewpassword,updatepassword,insertemail,viewemail,update_email,
    insertname,viewname,updatename,inserthome,viewhome,updatehome,insertnumber,viewnumber,updatenumber;
    DBHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySettingsBinding =ActivitySettingsBinding.inflate(getLayoutInflater());
        setContentView(activitySettingsBinding.getRoot());
        allocateActivityTitle("Settings");

        ActionBar actionBar = getSupportActionBar();

        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        email=findViewById(R.id.email);
        name=findViewById(R.id.name);
        home=findViewById(R.id.home);
        phonenumber=findViewById(R.id.phonenumber);
        viewusername=findViewById(R.id.viewusername);
        updateuser=findViewById(R.id.updateuser);
        viewpassword=findViewById(R.id.viewpassword);
        insertemail=findViewById(R.id.insertemail);
        viewemail=findViewById(R.id.viewemail);
        update_email=findViewById(R.id.update_email);
        insertname=findViewById(R.id.insertname);
        viewname=findViewById(R.id.viewname);
        updatename=findViewById(R.id.updatename);
        inserthome=findViewById(R.id.inserthome);
        viewhome=findViewById(R.id.viewhome);
        updatehome=findViewById(R.id.updatehome);
        insertnumber=findViewById(R.id.insertnumber);
        viewnumber=findViewById(R.id.viewnumber);
        updatenumber=findViewById(R.id.updatenumber);
        repassword=findViewById(R.id.repassword);
        myDB = new DBHelper(this);

        viewusername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDB.viewdata();
                if(res.getCount()==0){
                    Toast.makeText(SettingsActivity.this,"No Entry Found",Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append(" username :"+res.getString(0)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(SettingsActivity.this);
                builder.setCancelable(true);
                builder.setTitle(" Username ");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

        updateuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernameTXT = username.getText().toString();
                String passwordTXT = password.getText().toString();

                Boolean checkupdateusernamedata = myDB.insertData( usernameTXT, passwordTXT );
                if(checkupdateusernamedata==true) {
                    Toast.makeText(SettingsActivity.this,"Username and Password Updated",Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(SettingsActivity.this,"Username and Password Not Updated",Toast.LENGTH_SHORT).show();
            }
        });

        viewpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDB.viewdata();
                if(res.getCount()==0){
                    Toast.makeText(SettingsActivity.this,"No Entry Found",Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append(" password :"+res.getString(1)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(SettingsActivity.this);
                builder.setCancelable(true);
                builder.setTitle(" Password ");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });


        insertemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailTXT = email.getText().toString();

                Boolean checkemaildata = myDB.insertemaildata( emailTXT );
                if(checkemaildata==true) {
                    Toast.makeText(SettingsActivity.this,"Email Inserted",Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(SettingsActivity.this,"Email Not Inserted",Toast.LENGTH_SHORT).show();
            }
        });

        update_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailTXT = email.getText().toString();

                Boolean checkupdateemaildata = myDB.insertemaildata( emailTXT );
                if(checkupdateemaildata==true) {
                    Toast.makeText(SettingsActivity.this,"Email Has Been Updated",Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(SettingsActivity.this,"Email Did Not Update",Toast.LENGTH_SHORT).show();
            }
        });

        viewemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDB.viewsettingsdata();
                if(res.getCount()==0){
                    Toast.makeText(SettingsActivity.this,"No Entry Found",Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append(" email :"+res.getString(0)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(SettingsActivity.this);
                builder.setCancelable(true);
                builder.setTitle(" Email ");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

        insertname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();

                Boolean checknamedata = myDB.insertnamedata( nameTXT );
                if(checknamedata==true) {
                    Toast.makeText(SettingsActivity.this,"Full Name Inserted",Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(SettingsActivity.this,"Full Name Not Inserted",Toast.LENGTH_SHORT).show();
            }
        });

        updatename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String emailTXT = email.getText().toString();
                String nameTXT = name.getText().toString();
                //String homeTXT = home.getText().toString();
                //String phonenumberTXT = phonenumber.getText().toString();

                Boolean checkupdatenamedata = myDB.insertnamedata( nameTXT );
                if(checkupdatenamedata==true) {
                    Toast.makeText(SettingsActivity.this,"Full Name Has Been Updated",Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(SettingsActivity.this,"Full Name Did Not Update",Toast.LENGTH_SHORT).show();
            }
        });

        viewname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDB.viewsettingsdata();
                if(res.getCount()==0){
                    Toast.makeText(SettingsActivity.this,"No Entry Found",Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append(" Full Name :"+res.getString(1)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(SettingsActivity.this);
                builder.setCancelable(true);
                builder.setTitle(" Full Name ");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

        inserthome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String homeTXT = home.getText().toString();

                Boolean checkhomedata = myDB.inserthomedata( homeTXT );
                if(checkhomedata==true) {
                    Toast.makeText(SettingsActivity.this,"Home Address Inserted",Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(SettingsActivity.this,"Home Address Not Inserted",Toast.LENGTH_SHORT).show();
            }
        });

        updatehome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String homeTXT = home.getText().toString();

                Boolean checkupdatehomedata = myDB.inserthomedata( homeTXT );
                if(checkupdatehomedata==false) {
                    Toast.makeText(SettingsActivity.this,"Home Address Has Been Updated",Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(SettingsActivity.this,"Home Address Did Not Update",Toast.LENGTH_SHORT).show();
            }
        });

        viewhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDB.viewsettingsdata();
                if(res.getCount()==0){
                    Toast.makeText(SettingsActivity.this,"No Entry Found",Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append(" Home Address :"+res.getString(2)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(SettingsActivity.this);
                builder.setCancelable(true);
                builder.setTitle(" Home Address ");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

        insertnumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String numberTXT = phonenumber.getText().toString();

                Boolean checknumberdata = myDB.insertphonenumberdata( numberTXT );
                if(checknumberdata==true) {
                    Toast.makeText(SettingsActivity.this,"Phone Number Inserted",Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(SettingsActivity.this,"Phone Number Not Inserted",Toast.LENGTH_SHORT).show();
            }
        });

        updatenumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String phonenumberTXT = phonenumber.getText().toString();

                Boolean checkupdatenumberdata = myDB.insertphonenumberdata( phonenumberTXT );
                if(checkupdatenumberdata==false) {
                    Toast.makeText(SettingsActivity.this,"Phone Number Has Been Updated",Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(SettingsActivity.this,"Phone Number Did Not Update",Toast.LENGTH_SHORT).show();
            }
        });

        viewnumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDB.viewsettingsdata();
                if(res.getCount()==0){
                    Toast.makeText(SettingsActivity.this,"No Entry Found",Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append(" Phone Number :"+res.getString(3)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(SettingsActivity.this);
                builder.setCancelable(true);
                builder.setTitle(" Phone Number ");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }

    }

package com.example.bwfitness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username,password,repassword;
    Button btnSignUp,btnSignIn;
    DBHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        repassword = (EditText)findViewById(R.id.repassword);

        btnSignUp = (Button) findViewById(R.id.signup);
        btnSignIn = (Button) findViewById(R.id.signin);

        myDB = new DBHelper(this);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if(user.equals("") || pass.equals("") || repass.equals(""))
                {
                    Toast.makeText(MainActivity.this,"Fill all the fields.",Toast.LENGTH_SHORT).show();
                }
                else
                    {
                    if(pass.equals(repass))
                    {
                       Boolean usercheckResult = myDB.checkusername(user);
                       if(usercheckResult == false)
                       {
                           Boolean regResult = myDB.insertData(user,pass);
                           if(regResult == true){
                               Toast.makeText(MainActivity.this,"Registration Successful",Toast.LENGTH_SHORT).show();
                               Intent intent = new Intent(getApplicationContext(),SignUpActivity.class);
                               startActivity(intent);
                           }
                           else
                           {
                               Toast.makeText(MainActivity.this,"Registration Failed",Toast.LENGTH_SHORT).show();
                           }
                       }
                       else
                       {
                           Toast.makeText(MainActivity.this,"User Already Exists.\n Please Sign In",Toast.LENGTH_SHORT).show();
                       }
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this,"Password not Matching",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SignUpActivity.class);
                startActivity(intent);
            }
        });
    }
}
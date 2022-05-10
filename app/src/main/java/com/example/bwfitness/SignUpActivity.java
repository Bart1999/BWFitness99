package com.example.bwfitness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    EditText username,password;
    Button btnLogin;

    DBHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        username = (EditText) findViewById(R.id.usernameLogin);
        password = (EditText) findViewById(R.id.passwordLogin);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        myDB = new DBHelper(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if(user.equals("") || pass.equals("")){
                    Toast.makeText(SignUpActivity.this,"Please Enter Your credentials.",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Boolean result = myDB.checkusernamePassword(user,pass);
                    if(result==true){
                        Intent intent = new Intent(getApplicationContext(),HomePageActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(SignUpActivity.this,"Invalid Credentials, Please Try Again. ",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
package com.example.bwfitness;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.bwfitness.databinding.ActivityPaymentBinding;
import android.text.InputType;
import android.widget.Toast;
import com.braintreepayments.cardform.view.CardForm;

public class Payment_Activity extends NavDrawerBaseActivity {

    ActivityPaymentBinding activityPaymentBinding;
    CardForm cardForm;
    Button buy;
    AlertDialog.Builder alertBuilder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityPaymentBinding = ActivityPaymentBinding.inflate(getLayoutInflater());
        setContentView(activityPaymentBinding.getRoot());
        allocateActivityTitle("Payment");

        ActionBar actionBar = getSupportActionBar();

        cardForm = findViewById(R.id.card_form);
        buy = findViewById(R.id.btnBuy);
        cardForm.cardRequired(true)
                .expirationRequired(true)
                .cvvRequired(true)
                .postalCodeRequired(true)
                .mobileNumberRequired(true)
                .mobileNumberExplanation("Text Message is required on this number")
                .setup(Payment_Activity.this);
        cardForm.getCvvEditText().setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cardForm.isValid()){
                    alertBuilder = new AlertDialog.Builder(Payment_Activity.this);
                    alertBuilder.setTitle("Confirm before purchase");
                    alertBuilder.setMessage("Card number:" + cardForm.getCardNumber()+"\n"+
                            "Card expiry date:" + cardForm.getExpirationDateEditText().getText().toString()+"\n"+
                            "Card CVV:" + cardForm.getCvv()+"\n"+
                            "Phone number:" + cardForm.getMobileNumber());
                    alertBuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            Toast.makeText(Payment_Activity.this,"Thank you for your purchase",Toast.LENGTH_LONG).show();
                        }
                    });
                    alertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    AlertDialog alertDialog = alertBuilder.create();
                    alertDialog.show();
                }
                else{
                    Toast.makeText(Payment_Activity.this,"Please complete the form",Toast.LENGTH_LONG).show();
                }
            }
        });


    }

}
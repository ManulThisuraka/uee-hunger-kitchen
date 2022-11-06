package com.example.HungerKitchen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;

public class AddFundsSecond extends AppCompatActivity {

    EditText Famount, FcardNum, FexpDate, Fcvv;
    Button FaddFund;

    FundRecord fundobj;

    DatabaseReference dbRef;

    String Fdname,Fcontact,Fadd,Fnic,Fpayt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_funds2);

        Famount = findViewById(R.id.AFinputAmount);
        FcardNum = findViewById(R.id.AFinputCard);
        FexpDate = findViewById(R.id.AFexpectDate);
        Fcvv = findViewById(R.id.AFcvv);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Fdname = extras.getString("dname");
            Fcontact = extras.getString("contact");
            Fadd = extras.getString("address");
            Fnic = extras.getString("nic");
            Fpayt = extras.getString("paytype");
            //The key argument here must match that used in the other activity
        }

        fundobj = new FundRecord();

        ImageButton btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goBack = new Intent(AddFundsSecond.this, AddFundsFirst.class);
                startActivity(goBack);
            }
        });
    }
}
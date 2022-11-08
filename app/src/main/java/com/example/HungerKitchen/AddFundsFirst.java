package com.example.HungerKitchen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class AddFundsFirst extends AppCompatActivity {

    EditText FdonorName, FcontactNo, Faddress, FnicNo, FpayType;
    Button Fnext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_funds1);

        FdonorName = findViewById(R.id.AFdonorname);
        FcontactNo = findViewById(R.id.AFcontact);
        Faddress = findViewById(R.id.AFaddress);
        FnicNo = findViewById(R.id.AFnic);
        FpayType = findViewById(R.id.AFtype);

        Fnext = findViewById(R.id.AFnext_pay);

        //ImageButton btnBack = findViewById(R.id.btnBack);

        //String Fpayt = FpayType.getText().toString();

//        btnBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent goBack = new Intent(AddFundsFirst.this, HomeDashboard.class);
//                startActivity(goBack);
//            }
//        });

        Fnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Fdname = FdonorName.getText().toString();
                String Fcontact = FcontactNo.getText().toString();
                String Fadd = Faddress.getText().toString();
                String Fnic = FnicNo.getText().toString();
                String Ftype = FpayType.getText().toString();

                Intent i = new Intent(AddFundsFirst.this, AddFundsSecond.class);
                i.putExtra("dname",Fdname);
                i.putExtra("contact",Fcontact);
                i.putExtra("address",Fadd);
                i.putExtra("nic",Fnic);
                i.putExtra("paytype",Ftype);
                startActivity(i);
            }
        });
    }
}
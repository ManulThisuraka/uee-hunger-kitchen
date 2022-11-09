package com.example.HungerKitchen;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

        FaddFund = findViewById(R.id.Ffund_pay);

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

    public void insertPayment(View view) {

        //String uid = FirebaseAuth.getInstance().getUid();
        //dbRef = FirebaseDatabase.getInstance().getReference().child("Tasks").child(uid);
        dbRef = FirebaseDatabase.getInstance().getReference().child("CashDonations");

        if (TextUtils.isEmpty(Famount.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Please enter the the amount", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(FcardNum.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Please enter the card number", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(FexpDate.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Please enter the expire date of card", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(Fcvv.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Please enter the CVV", Toast.LENGTH_SHORT).show();
        } else {

//            fundobj.setName(Fdname.trim());
//            fundobj.setNumber(Fcontact.trim());
//            fundobj.setAddress(Fadd.trim());
//            fundobj.setNIC(Fnic.trim());
//            fundobj.setPaymentType(Fpayt.trim());
//            fundobj.setAmount(Famount.getText().toString().trim());
//            fundobj.setCardNumber(FcardNum.getText().toString().trim());
//            fundobj.setExDate(FexpDate.getText().toString().trim());
//            fundobj.setCVV(Fcvv.getText().toString().trim());
//            DatabaseReference newrefI = dbRef.push();
//            newrefI.setValue(fundobj);

            Toast.makeText(getApplicationContext(), "Your Payment Submitted Successfully", Toast.LENGTH_SHORT).show();
            Intent intent2 = new Intent(this,EditFunds.class);
            intent2.putExtra("dname",Fdname);
            intent2.putExtra("contact",Fcontact);
            intent2.putExtra("address",Fadd);
            intent2.putExtra("nic",Fnic);
            intent2.putExtra("paytype",Fpayt);
            intent2.putExtra("cardnum",FcardNum.getText().toString());
            intent2.putExtra("amount",Famount.getText().toString());
            intent2.putExtra("expiery",FexpDate.getText().toString());
            intent2.putExtra("cvv",Fcvv.getText().toString());
            startActivity(intent2);
        }
    }
}
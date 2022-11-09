package com.example.HungerKitchen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditFunds extends AppCompatActivity {

    EditText EFdname, EFcontact, EFnic, EFpaytype, EFexpiery, EFamount, EFcardnum, EFcvv;
    Button EFbtnUpdate,EFbtnOk;

    FundRecord EFobj;
    DatabaseReference dbRef;

    String Fdname, Fcontact, Fnic, Fpaytype, Fexpiery, Famount, Fcardnum, Fcvv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_funds);

        EFdname = findViewById(R.id.EFdonorname);
        EFcontact = findViewById(R.id.EFcontact);
        EFnic = findViewById(R.id.EFnic);
        EFpaytype = findViewById(R.id.EFpaytype);
        EFexpiery = findViewById(R.id.EFexpdate);
        EFamount= findViewById(R.id.EFamount);
        EFcardnum = findViewById(R.id.EFcardno);
        EFcvv = findViewById(R.id.EFcvv);

        EFbtnOk = findViewById(R.id.EFbtnok);
        EFbtnUpdate = findViewById(R.id.EFbtnupdate);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Fdname = extras.getString("dname");
            Fcontact = extras.getString("contact");
            Fnic = extras.getString("nic");
            Fpaytype = extras.getString("paytype");
            Fexpiery = extras.getString("expiery");
            Famount = extras.getString("amount");
            Fcardnum = extras.getString("cardnum");
            Fcvv = extras.getString("cvv");
            //The key argument here must match that used in the other activity
        }

        EFdname.setText(Fdname);
        EFcontact.setText(Fcontact);
        EFnic.setText(Fnic);
        EFpaytype.setText(Fpaytype);
        EFexpiery.setText(Fexpiery);
        EFamount.setText(Famount);
        EFcardnum.setText(Fcardnum);
        EFcvv.setText(Fcvv);

        EFobj = new FundRecord();

        ImageButton btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goBack = new Intent(EditFunds.this, AddFundsSecond.class);
                startActivity(goBack);
            }
        });

        EFbtnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("CashDonations");

                EFobj.setName(EFdname.getText().toString().trim());
                EFobj.setNumber(EFcontact.getText().toString().trim());
                EFobj.setNIC(EFpaytype.getText().toString().trim());
                EFobj.setPaymentType(EFexpiery.getText().toString().trim());
                EFobj.setAmount(EFcardnum.getText().toString().trim());
                EFobj.setCardNumber(EFcvv.getText().toString().trim());
                EFobj.setExDate(EFcardnum.getText().toString().trim());
                EFobj.setCVV(EFcvv.getText().toString().trim());

                DatabaseReference newrefF = dbRef.push();
                newrefF.setValue(EFobj);
                Toast.makeText(getApplicationContext(), "Yor Request Updated Successfully", Toast.LENGTH_SHORT).show();
            }
        });
        // Connect OK button
        EFbtnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home = new Intent(EditFunds.this, HomeDashboard.class);
                startActivity(home);
            }
        });
    }
}
package com.example.HungerKitchen;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditInventory extends AppCompatActivity {
    EditText EIdname,EIcontact,EInic,EIaddress,EItypeInv,EIqtyInv,EIexpDate;
    Button EIbtnUpdate,EIbtnOk;

    InventoryRecord IRobj;

    DatabaseReference dbRef;

    String Sdname,Scontact,SInic,Saddress,StypeInv,SqtyInv,SexpDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_inventory);

        EIdname = findViewById(R.id.EIdonorname);
        EIcontact = findViewById(R.id.EIcontact);
        EInic = findViewById(R.id.EInic);
        EIaddress = findViewById(R.id.EIaddress);
        EItypeInv = findViewById(R.id.EIinvtype);
        EIqtyInv = findViewById(R.id.EIinputQty);
        EIexpDate = findViewById(R.id.EIexpectDate);

        EIbtnOk = findViewById(R.id.EIbtnok);
        EIbtnUpdate = findViewById(R.id.EIbtnupdate);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Sdname = extras.getString("dname");
            Scontact = extras.getString("contact");
            SInic = extras.getString("nic");
            Saddress = extras.getString("address");
            StypeInv = extras.getString("typeInv");
            SqtyInv = extras.getString("qtyInv");
            SexpDate = extras.getString("expDate");

            //The key argument here must match that used in the other activity
        }

        EIdname.setText(Sdname);
        EIcontact.setText(Scontact);
        EInic.setText(SInic);
        EIaddress.setText(Saddress);
        EItypeInv.setText(StypeInv);
        EIqtyInv.setText(SqtyInv);
        EIexpDate.setText(SexpDate);

        IRobj = new InventoryRecord();

        ImageButton btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goBack = new Intent(EditInventory.this, AddInventoryFirst.class);
                startActivity(goBack);
            }
        });

        EIbtnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("InventoryItems");

                IRobj.setDonorName(EIdname.getText().toString().trim());
                IRobj.setContactNo(EIcontact.getText().toString().trim());
                IRobj.setNicNo(EInic.getText().toString().trim());
                IRobj.setdAddress(EIaddress.getText().toString().trim());
                IRobj.setInvType(EItypeInv.getText().toString().trim());
                IRobj.setInvQty(EIqtyInv.getText().toString().trim());
                IRobj.setExpDate(EIexpDate.getText().toString().trim());
                DatabaseReference newrefI = dbRef.push();
                newrefI.setValue(IRobj);
                Toast.makeText(getApplicationContext(), "Yor Request Updated Successfully", Toast.LENGTH_LONG).show();
            }
        });

        EIbtnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home = new Intent(EditInventory.this, HomeDashboard.class);
                startActivity(home);
            }
        });
    }

    }
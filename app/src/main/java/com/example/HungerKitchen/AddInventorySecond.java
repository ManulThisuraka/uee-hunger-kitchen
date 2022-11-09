package com.example.HungerKitchen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddInventorySecond extends AppCompatActivity {
    EditText IqtyInv, IexpDate;
    Button IaddInv;
    CheckBox unprocessed, spice, dry;
    InventoryRecord invobj;

    DatabaseReference dbRef;

    String Idname, Icontact, Inic, Iaddress, joined;
    List<String> Iquantity = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_inventory2);


        IqtyInv = findViewById(R.id.AIinputQty);
        IexpDate = findViewById(R.id.AIexpectDate);
        unprocessed = findViewById(R.id.checkUnprocessed);
        spice = findViewById(R.id.checkSpice);
        dry = findViewById(R.id.checkDry);

        IaddInv = findViewById(R.id.AIadd_inv);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Idname = extras.getString("dname");
            Icontact = extras.getString("contact");
            Inic = extras.getString("nic");
            Iaddress = extras.getString("address");
            //The key argument here must match that used in the other activity
        }

        invobj = new InventoryRecord();

        ImageButton btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goBack = new Intent(AddInventorySecond.this, AddInventoryFirst.class);
                startActivity(goBack);
            }
        });

        IaddInv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (unprocessed.isChecked()) {
                    Iquantity.add("Unprocessed food");
                }
                if (spice.isChecked()) {
                    Iquantity.add("Spice");
                }
                if (dry.isChecked()) {
                    Iquantity.add("Dry foods");
                } if(!unprocessed.isChecked() && !spice.isChecked() && !dry.isChecked() ) {
                    throw new RuntimeException("Empty check box", new Throwable(String.valueOf(Iquantity)));
                }

                joined = String.join(",", Iquantity);
                insertInventory(view);
            }

        });
    }

    public void insertInventory(View view) {

        //String uid = FirebaseAuth.getInstance().getUid();
        //dbRef = FirebaseDatabase.getInstance().getReference().child("Tasks").child(uid);
        dbRef = FirebaseDatabase.getInstance().getReference().child("InventoryItems");

        if (TextUtils.isEmpty(IqtyInv.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Please enter the quantities", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(IexpDate.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Please enter the date that you are deliver", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent1 = new Intent(this, EditInventory.class);
            intent1.putExtra("dname", Idname);
            intent1.putExtra("contact", Icontact);
            intent1.putExtra("nic", Inic);
            intent1.putExtra("address", Iaddress);
            intent1.putExtra("qtyInv", IqtyInv.getText().toString());
            intent1.putExtra("typeInv", joined);
            intent1.putExtra("expDate", IexpDate.getText().toString());
            Toast.makeText(AddInventorySecond.this, "Yor Request Submitted Successfully", Toast.LENGTH_LONG).show();
            startActivity(intent1);
        }
    }
}
package com.example.HungerKitchen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddInventorySecond extends AppCompatActivity {
    EditText  IqtyInv, IexpDate;
    Button IaddInv;

    InventoryRecord invobj;

    DatabaseReference dbRef;

    String Idname,Icontact,Inic,Iaddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_inventory2);


        IqtyInv = findViewById(R.id.AIinputQty);
        IexpDate = findViewById(R.id.AIexpectDate);

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

//            invobj.setDonorName(Idname.trim());
//            invobj.setContactNo(Icontact.trim());
//            invobj.setNicNo(Inic.trim());
//            invobj.setdAddress(Iaddress.trim());
//            invobj.setInvType(ItypeInv.getText().toString().trim());
//            invobj.setInvQty(IqtyInv.getText().toString().trim());
//            invobj.setExpDate(IexpDate.getText().toString().trim());
//            DatabaseReference newrefI = dbRef.push();
//            newrefI.setValue(invobj);

            Intent intent1 = new Intent(this,EditInventory.class);
            intent1.putExtra("dname",Idname);
            intent1.putExtra("contact",Icontact);
            intent1.putExtra("nic",Inic);
            intent1.putExtra("address",Iaddress);
            intent1.putExtra("qtyInv",IqtyInv.getText().toString());
            intent1.putExtra("expDate",IexpDate.getText().toString());
            Toast.makeText(AddInventorySecond.this, "Yor Request Submitted Successfully", Toast.LENGTH_LONG).show();
            startActivity(intent1);
        }
    }
}
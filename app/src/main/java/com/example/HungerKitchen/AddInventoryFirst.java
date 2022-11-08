package com.example.HungerKitchen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.firebase.database.DatabaseReference;

public class AddInventoryFirst extends AppCompatActivity {

    EditText IdonorName, IcontactNo, InicNo, Iaddress;
    Button Inext;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_inventory1);

        IdonorName = findViewById(R.id.AIdonorname);
        IcontactNo = findViewById(R.id.AIcontact);
        InicNo = findViewById(R.id.AInic);
        Iaddress = findViewById(R.id.AIaddress);

        Inext = findViewById(R.id.AIbtnnext);

        //ImageButton btnBack = findViewById(R.id.btnBack);


//        btnBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent goBack = new Intent(AddInventoryFirst.this, HomeDashboard.class);
//                startActivity(goBack);
//            }
//        });

        Inext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Idname = IdonorName.getText().toString();
                String Icontact = IcontactNo.getText().toString();
                String Inic = InicNo.getText().toString();
                String Idaddress = Iaddress.getText().toString();

                Intent i = new Intent(AddInventoryFirst.this, AddInventorySecond.class);
                i.putExtra("dname",Idname);
                i.putExtra("contact",Icontact);
                i.putExtra("nic",Inic);
                i.putExtra("address",Idaddress);
                startActivity(i);
            }
        });
    }
}
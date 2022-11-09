package com.example.HungerKitchen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddVolunteering extends AppCompatActivity {

    EditText Vname,Vdate,Vskills;
    Task taskobj;

    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_volunteering);

        Vname = findViewById(R.id.ETname);
        Vdate = findViewById(R.id.ETdate);
        Vskills = findViewById(R.id.ETskills);

        taskobj = new Task();
    }
    public void insertTask(View view){

        //String uid = FirebaseAuth.getInstance().getUid();
        //dbRef = FirebaseDatabase.getInstance().getReference().child("Tasks").child(uid);
        dbRef = FirebaseDatabase.getInstance().getReference().child("Tasks");

        if (TextUtils.isEmpty(Vname.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Please enter your name", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(Vdate.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Please enter the date that you are available", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(Vskills.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Please enter the the skills you have", Toast.LENGTH_SHORT).show();
        } else {

            taskobj.setName(Vname.getText().toString().trim());
            taskobj.setDate(Vdate.getText().toString().trim());
            taskobj.setSkills(Vskills.getText().toString().trim());
            taskobj.setRole("Not Assigned");
            taskobj.setMessage("");
            DatabaseReference newref = dbRef.push();
            newref.setValue(taskobj);
            Toast.makeText(getApplicationContext(), "Yor Request Submitted Successfully", Toast.LENGTH_SHORT).show();
            //Intent intent1 = new Intent(this,Addpayment.class);
            //startActivity(intent1);
        }
    }
}
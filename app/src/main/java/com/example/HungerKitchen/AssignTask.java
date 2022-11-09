package com.example.HungerKitchen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class AssignTask extends AppCompatActivity {

    EditText VTask, VMessage;
    Task taskobj;
    DatabaseReference dbRef;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_task);

        VTask = findViewById(R.id.ETtask);
        VMessage = findViewById(R.id.ETdetails);

        taskobj = new Task();
        key = getIntent().getStringExtra("key");

        dbRef = FirebaseDatabase.getInstance().getReference().child("Tasks");

        dbRef.child(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@androidx.annotation.NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                    VTask.setText(dataSnapshot.child("role").getValue().toString());
                    VMessage.setText(dataSnapshot.child("message").getValue().toString());
                } else
                    Toast.makeText(getApplicationContext(), "No Source to Display", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
        public void UpdateTask(View view) {

            dbRef = FirebaseDatabase.getInstance().getReference().child("Tasks");
            dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.hasChild(key)){
                        try{

                            Map<String, Object> map = new HashMap<>();
                            map.put("role",  VTask.getText().toString());
                            map.put("message",VMessage.getText().toString());

//                            taskobj.setRole(VTask.getText().toString().trim());
//                            taskobj.setMessage(VMessage.getText().toString().trim());

                            dbRef = FirebaseDatabase.getInstance().getReference().child("Tasks").child(key);
                            //dbRef.setValue(taskobj);
                            dbRef.updateChildren(map);
                            Toast.makeText(getApplicationContext(),"Assigned Task Successfully", Toast.LENGTH_SHORT).show();

                            Intent intent1 = new Intent(AssignTask.this,VolunteerManagement.class);
                            startActivity(intent1);

                        }catch (NumberFormatException e){
                            Toast.makeText(getApplicationContext(),"Invalid Contact Number", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(),"Error", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

}
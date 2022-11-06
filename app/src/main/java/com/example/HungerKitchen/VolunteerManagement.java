package com.example.HungerKitchen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class VolunteerManagement extends AppCompatActivity {

    private RecyclerView recyclerView;
    VolunteerManagementAdapter adapter; // Create Object of the Adapter class
    DatabaseReference databaseReference; // Create object of the

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_management);

        //String uid = FirebaseAuth.getInstance().getUid();
        //databaseReference = FirebaseDatabase.getInstance().getReference().child("Tasks").child(uid);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Tasks");

        Log.d("abc",databaseReference.toString());

        recyclerView = findViewById(R.id.VMrecycler);

        // To display the Recycler view linearly
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // It is a class provide by the FirebaseUI to make a
        // query in the database to fetch appropriate data
        FirebaseRecyclerOptions<Task> options = new FirebaseRecyclerOptions.Builder<Task>().setQuery(databaseReference, Task.class).build();
        // Connecting object of required Adapter class to
        // the Adapter class itself
        adapter = new VolunteerManagementAdapter(options);
        // Connecting Adapter class with the Recycler view*/
        recyclerView.setAdapter(adapter);
    }
    // Function to tell the app to start getting
    // data from database on starting of the activity
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    // Function to tell the app to stop getting
    // data from database on stopping of the activity
    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
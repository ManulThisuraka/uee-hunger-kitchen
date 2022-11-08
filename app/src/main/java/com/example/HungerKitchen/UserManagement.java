package com.example.HungerKitchen;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class UserManagement extends AppCompatActivity {

    private RecyclerView recyclerView;
    UserManagementAdapter adapter; // Create Object of the Adapter class
    DatabaseReference databaseReference; // Create object of the

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_management);

        //databaseReference = FirebaseDatabase.getInstance().getReference().child("Tasks").child(uid);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("users");

        recyclerView = findViewById(R.id.VMrecycler);

        // To display the Recycler view linearly
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // It is a class provide by the FirebaseUI to make a
        // query in the database to fetch appropriate data
        FirebaseRecyclerOptions<User> options = new FirebaseRecyclerOptions.Builder<User>().setQuery(databaseReference, User.class).build();
        // Connecting object of required Adapter class to
        // the Adapter class itself
        adapter = new UserManagementAdapter(options);
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
package com.example.HungerKitchen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InventoryManagement extends AppCompatActivity {

    private RecyclerView recyclerView;
    InventoryManagementAdapter adapter; // Create Object of the Adapter class
    DatabaseReference databaseReference; // Create object of the

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_management);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("InventoryItems");

        Log.d("abc",databaseReference.toString());

        recyclerView = findViewById(R.id.IMrecycler);

        // To display the Recycler view linearly
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // It is a class provide by the FirebaseUI to make a
        // query in the database to fetch appropriate data
        FirebaseRecyclerOptions<InventoryRecord> options = new FirebaseRecyclerOptions.Builder<InventoryRecord>().setQuery(databaseReference, InventoryRecord.class).build();

        // Connecting object of required Adapter class to
        // the Adapter class itself
        adapter = new InventoryManagementAdapter(options);

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
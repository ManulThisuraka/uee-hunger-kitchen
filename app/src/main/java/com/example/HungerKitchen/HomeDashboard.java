package com.example.HungerKitchen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class HomeDashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_dashboard);

        ImageButton btnProfile = findViewById(R.id.btnProfile);
        ImageButton btnService = findViewById(R.id.btnService);
        ImageButton btnFunds = findViewById(R.id.btnFunds);
        ImageButton btnInventory = findViewById(R.id.btnInventory);

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeDashboard.this, UserProfile.class);
                startActivity(intent);
            }
        });

        btnService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeDashboard.this, AddVolunteering.class);
                startActivity(intent);
            }
        });

        btnFunds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeDashboard.this, AddFundsFirst.class);
                startActivity(intent);
            }
        });

        btnInventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeDashboard.this, AddInventoryFirst.class);
                startActivity(intent);
            }
        });


    }
}
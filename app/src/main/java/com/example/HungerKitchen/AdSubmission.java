//package com.example.HungerKitchen;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.text.Editable;
//import android.text.TextUtils;
//import android.text.TextWatcher;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//
//public class AdSubmission extends AppCompatActivity {
//
//    EditText Vbody, Vname, Vdate, Vcategory;
//    TextView Vcount;
//    Advertisement adobj;
//
//    DatabaseReference dbRef;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_ad_submission);
//
//        ImageView lefticon = findViewById(R.id.menu);
//        ImageView righticon = findViewById(R.id.noti);
//
//        lefticon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(AdSubmission.this, profile.class);
//                startActivity(intent);
//            }
//        });
//
//        righticon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(AdSubmission.this, profile.class);
//                startActivity(intent);
//            }
//        });
//
//        Vbody = findViewById(R.id.body_c);
//        Vname = findViewById(R.id.newspaper_c);
//        Vdate = findViewById(R.id.date_c);
//        Vcategory = findViewById(R.id.category_c);
//        Vcount = findViewById(R.id.count_c);
//
//        adobj = new Advertisement();
//
//        Vbody.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                String text1 = Vbody.getText().toString();
//                text1 = text1.replace("\n", " ");
//                String[] textArray = text1.split(" ");
//                Vcount.setText(textArray.length + "");
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });
//    }
//
//    // Create Advertisements
//    public void insertAd(View view) {
//
//        String uid = FirebaseAuth.getInstance().getUid();
//        dbRef = FirebaseDatabase.getInstance().getReference().child("Advertisements").child(uid);
//
//        if (TextUtils.isEmpty(Vname.getText().toString())) {
//            Toast.makeText(getApplicationContext(), "Please enter the newspaper name", Toast.LENGTH_SHORT).show();
//        } else if (TextUtils.isEmpty(Vdate.getText().toString())) {
//            Toast.makeText(getApplicationContext(), "Please enter the date to be published", Toast.LENGTH_SHORT).show();
//        } else if (TextUtils.isEmpty(Vcategory.getText().toString())) {
//            Toast.makeText(getApplicationContext(), "Please enter the category", Toast.LENGTH_SHORT).show();
//        } else if (TextUtils.isEmpty(Vbody.getText().toString())) {
//            Toast.makeText(getApplicationContext(), "Please type the advertisement", Toast.LENGTH_SHORT).show();
//        } else {
//
//            adobj.setNewspaper(Vname.getText().toString().trim());
//            adobj.setPdate(Vdate.getText().toString().trim());
//            adobj.setCategory(Vcategory.getText().toString().trim());
//            adobj.setBody(Vbody.getText().toString().trim());
//            adobj.setWordcount(Integer.parseInt(Vcount.getText().toString().trim()));
//            adobj.setStatus("Pending");
//            DatabaseReference newref = dbRef.push();
//            newref.setValue(adobj);
//            Toast.makeText(getApplicationContext(), "Ad Submitted Successfully", Toast.LENGTH_SHORT).show();
//            Intent intent1 = new Intent(this, AddFundsFirst.class);
//            startActivity(intent1);
//        }
//    }
//}
package com.example.HungerKitchen;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {
    EditText mFirstName, mLastName, mEmail, mPassword, mPhone, mConfirmPassword;
    ProgressBar progressBar;
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    FirebaseDatabase rootnode;
    DatabaseReference reference;
    String userID;
    Button signup;

    String email, password, firstname, lastname, phone, password2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFirstName = findViewById(R.id.firstName);
        mLastName = findViewById(R.id.lastName);
        mEmail = findViewById(R.id.Email);
        mPassword = findViewById(R.id.password);
        mConfirmPassword = findViewById(R.id.password2);
        mPhone = findViewById(R.id.phone);
        fstore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);
        signup = findViewById(R.id.mSignUpBtn);

        //Sign in button

        //Sign up button
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //realtime database
                rootnode = FirebaseDatabase.getInstance();
                reference = rootnode.getReference("users");

                //get values
                email = mEmail.getText().toString().trim();
                password = mPassword.getText().toString().trim();
                firstname = mFirstName.getText().toString();
                lastname = mLastName.getText().toString();
                phone = mPhone.getText().toString();
                password2 = mConfirmPassword.getText().toString().trim();

                //Validation process
                if (TextUtils.isEmpty(email)) {
                    mEmail.setError("Email is required . ");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    mPassword.setError("Password is needed . ");
                    return;
                }

                if (password.length() < 6) {
                    mPassword.setError("Password must be >= 6 characters");
                    return;
                }


                if (phone.length() != 10) {
                    mPhone.setError("Enter a valid phone number");
                    return;
                }
                if (!password.equals(password2)) {
                    mPassword.setError("Password should be match with confirm password");
                }

                progressBar.setVisibility(View.VISIBLE);

                //register user in firebase firestore
                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            userID = Objects.requireNonNull(fAuth.getCurrentUser()).getUid();
                            UserHelperClass helperClass = new UserHelperClass(firstname, lastname, email, phone, password);
                            reference.child(userID).setValue(helperClass);
                            DocumentReference documentReference = fstore.collection("users").document(userID);
                            Map<String, Object> user = new HashMap<>();
                            user.put("fname", firstname);
                            user.put("lname", lastname);
                            user.put("email", email);
                            user.put("phone", phone);
                            Toast.makeText(RegisterActivity.this, "User Created", Toast.LENGTH_SHORT).show();
                            //CLI comments

                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Log.d(TAG, "onSuccess: user profile is created for " + userID);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "onFailure: " + e.toString());
                                }
                            });
                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        } else {
                            Toast.makeText(RegisterActivity.this, "Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });

    }
}
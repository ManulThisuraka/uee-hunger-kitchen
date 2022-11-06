package com.example.HungerKitchen;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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

public class RegisterActivity extends AppCompatActivity {
    EditText mFirstName,mLastName,mUserName, mEmail,mPassword,mPhone;
    ProgressBar progressBar;
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    FirebaseDatabase rootnode;
    DatabaseReference reference;
    String userID;
    TextView signin;
    Button signup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFirstName = findViewById(R.id.firstName);
        mLastName = findViewById(R.id.lastName);
        mUserName = findViewById(R.id.userName);
        mEmail = findViewById(R.id.Email);
        mPassword = findViewById(R.id.password);
        mPhone = findViewById(R.id.phone);
        fstore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);

        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

        //Sign in button

        signin = (TextView)findViewById(R.id.mSignInTxt);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent3);
            }
        });

        //Sign up button
        signup = findViewById(R.id.mSignUpBtn);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //realtime database
                rootnode = FirebaseDatabase.getInstance();
                reference = rootnode.getReference("users");

                //get values
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                String firstname = mFirstName.getText().toString();
                String lastname = mLastName.getText().toString();
                String phone = mPhone.getText().toString();
                String username = mUserName.getText().toString();

                UserHelperClass helperClass = new UserHelperClass(firstname,lastname,email,phone,username,password);
                reference.child(username).setValue(helperClass);

                //Validation process
                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email is required . ");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    mPassword.setError("Password is needed . ");
                    return;
                }

                if(password.length()<6){
                    mPassword.setError("Password must be >= 6 characters");
                    return;
                }


                if (phone.length() != 10){
                    mPhone.setError("Enter a valid phone number");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                //register user in firebase firestore
                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this,"User Created", Toast.LENGTH_SHORT).show();
                            userID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fstore.collection("users").document(userID);
                            Map<String,Object> user = new HashMap<>();
                            user.put("fname", firstname);
                            user.put("lname", lastname);
                            user.put("email", email);
                            user.put("phone", phone);
                            user.put("username", username);

                            //CLI comments

                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Log.d(TAG,"onSuccess: user profile is created for "+ userID );
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "onFailure: "+ e.toString());
                                }
                            });
                            startActivity(new Intent(getApplicationContext(),profile.class));
                        }else {
                            Toast.makeText(RegisterActivity.this,"Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });

    }
}
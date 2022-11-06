package com.example.HungerKitchen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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

public class LoginActivity extends AppCompatActivity {

    EditText mEmail,mPassword;
    ProgressBar progressBar;
    FirebaseAuth fAuth;

    TextView registertxt, forgotpasswordtxt;
    Button loginbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmail = findViewById(R.id.Email);
        mPassword = findViewById(R.id.password);
        progressBar = findViewById(R.id.progressBar2);
        fAuth = FirebaseAuth.getInstance();
        forgotpasswordtxt = findViewById(R.id.forgotPasswordTxt);

        //Sign up button
        registertxt = (TextView)findViewById(R.id.mRegisterTxt);
        registertxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("abc","clicked");
                Intent intent1 = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent1);
            }
        });

        //Sign in button

        loginbtn = findViewById(R.id.mLoginBtn);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

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

                progressBar.setVisibility(View.VISIBLE);

                // authenticate the user

                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (mEmail.getText().toString().equals("admin99@gmail.com") && mPassword.getText().toString().equals("12345678")){
                            Toast.makeText(LoginActivity.this, "Admin logged in..", Toast.LENGTH_SHORT).show();
                            //startActivity(new Intent(getApplicationContext(),AdminDashboardActivity.class));
                        }else{
                            if(task.isSuccessful()){
                                Toast.makeText(LoginActivity.this,"Logged in successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),profile.class));
                            }else {
                                Toast.makeText(LoginActivity.this,"Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                        /*if(task.isSuccessful()){
                            Toast.makeText(LoginActivity.this,"Logged in successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),profile.class));
                        }else {
                            Toast.makeText(LoginActivity.this,"Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }*/
                    }
                });
            }
        });

        //Forgot password text--reset password
        forgotpasswordtxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText resetMail = new EditText(view.getContext());
                final AlertDialog.Builder passwordresetdialog = new AlertDialog.Builder(view.getContext());
                passwordresetdialog.setTitle("Reset Password ?");
                passwordresetdialog.setMessage("Enter your email to receive reset link");
                passwordresetdialog.setView(resetMail);

                passwordresetdialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //extract email and send the reset link
                        String mail = resetMail.getText().toString();
                        fAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(LoginActivity.this,"Reset link sent to your email", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(LoginActivity.this,"Error! Reset link not sent" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

                passwordresetdialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                       //close the dialog
                    }
                });
                passwordresetdialog.create().show();
            }
        });

    }
}
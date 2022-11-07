package com.example.HungerKitchen;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class UserProfile extends AppCompatActivity {

    EditText mfirstName, mlastName, mEmail, mphone, mpassword, mpassword2;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore fStore;
    FirebaseUser user;
    Button updateBtn, deleteBtn;
    FirebaseDatabase rootnode;
    FirebaseFirestore fstore;
    DatabaseReference reference;

    public static final String TAG = "TAG";
    String currentUserEmail, currentUserPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);


        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        ImageButton btnBack = findViewById(R.id.btnBack);
        updateBtn = findViewById(R.id.btnUpdate);
        deleteBtn = findViewById(R.id.mDeleteBtn);

        mfirstName = findViewById(R.id.firstName);
        mlastName = findViewById(R.id.lastName);
        mEmail = findViewById(R.id.Email);
        mphone = findViewById(R.id.phone);
        mpassword = findViewById(R.id.password);
        mpassword2 = findViewById(R.id.password2);

        firebaseAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        user = firebaseAuth.getCurrentUser();

        String uid = Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid();
        mDatabase.child("users").child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("USER DATA", dataSnapshot.toString());
                String fname = (String) dataSnapshot.child("firstname").getValue();
                String lname = (String) dataSnapshot.child("lastname").getValue();
                String password = (String) dataSnapshot.child("password").getValue();
                String phone = (String) dataSnapshot.child("phone").getValue();
                String email = (String) dataSnapshot.child("email").getValue();

                mfirstName.setText(fname);
                mlastName.setText(lname);
                mEmail.setText(email);
                mphone.setText(phone);
                mpassword.setText(password);
                mpassword2.setText(password);

                currentUserEmail = fname;
                currentUserPassword = password;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goBack = new Intent(UserProfile.this, HomeDashboard.class);
                startActivity(goBack);
            }
        });
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nFname = mfirstName.getText().toString();
                String nLname = mlastName.getText().toString();
                String nemail = mEmail.getText().toString();
                String nphone = mphone.getText().toString();
                String npass1 = mpassword.getText().toString();
                String npass2 = mpassword2.getText().toString();

                if (TextUtils.isEmpty(nemail)) {
                    mEmail.setError("Email is required . ");
                    return;
                }
                if (TextUtils.isEmpty(npass1)) {
                    mpassword.setError("Password is needed . ");
                    return;
                }

                if (npass1.length() < 6) {
                    mpassword.setError("Password must be >= 6 characters");
                    return;
                }

                if (nphone.length() != 10) {
                    mphone.setError("Enter a valid phone number");
                    return;
                }
                if (!npass1.equals(npass2)) {
                    mpassword.setError("Password should be match with confirm password");
                }

                FirebaseUser AuthUser = FirebaseAuth.getInstance().getCurrentUser();
                if (AuthUser != null) {
                    String name = AuthUser.getProviderData().toString();
                    String email = AuthUser.getEmail();
                    String pass = AuthUser.getProviderData().toString();
                    Log.d("USER DATA **********", name);
                    Log.d("USER DATA EMAIL **********", pass);
                }

                UserHelperClass helperClass = new UserHelperClass(nFname, nLname, nemail, nphone, npass1);
                rootnode = FirebaseDatabase.getInstance();
                reference = rootnode.getReference("users");
                reference.child(uid).setValue(helperClass);
                DocumentReference documentReference = fstore.collection("users").document(uid);
                Map<String, Object> user = new HashMap<>();
                user.put("fname", nFname);
                user.put("lname", nLname);
                user.put("email", nemail);
                user.put("phone", nphone);
                Toast.makeText(UserProfile.this, "User Updated", Toast.LENGTH_SHORT).show();
                //CLI comments
                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    }
                });
                startActivity(new Intent(getApplicationContext(), HomeDashboard.class));
            }


        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(UserProfile.this);
                builder.setTitle("Are You Sure ?");
                builder.setMessage("Deleted data can't be Undo.");
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String uid = FirebaseAuth.getInstance().getUid();
                        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        assert uid != null;
                        FirebaseDatabase.getInstance().getReference().child("users")
                                .child(uid).removeValue();
                        assert user != null;
                        Log.d("LOGGED USER *****", String.valueOf(user));
                        AuthCredential credential = EmailAuthProvider.getCredential(mEmail.getText().toString(), mpassword.getText().toString());
                        user.reauthenticate(credential)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        user.delete()
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()) {
                                                            Log.d("TAG", "User account deleted.");
                                                            FirebaseAuth.getInstance().signOut();
                                                            Intent goBack = new Intent(UserProfile.this, LoginActivity.class);
                                                            startActivity(goBack);
                                                        }
                                                    }
                                                });
                                    }
                                });

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(UserProfile.this, "Cancelled", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();


            }
        });
    }
}
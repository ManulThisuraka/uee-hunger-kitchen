//package com.example.HungerKitchen;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AlertDialog;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.firestore.DocumentReference;
//import com.google.firebase.firestore.FirebaseFirestore;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class EditProfile extends AppCompatActivity {
//
//    public static final String TAG = "TAG";
//    EditText profilefirstname, profilelastname, profileusername, profileemail, profilephone;
//    FirebaseAuth fAuth;
//    FirebaseFirestore fStore;
//    FirebaseUser user;
//    Button saveBtn, deleteBtn;
//    TextView editphoto;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_edit_profile);
//
//        Intent data = getIntent();
//        String firstname = data.getStringExtra("firstname");
//        String lastname = data.getStringExtra("lastname");
//        String username = data.getStringExtra("username");
//        String email = data.getStringExtra("email");
//        String phone = data.getStringExtra("phone");
//
//        fAuth = FirebaseAuth.getInstance();
//        fStore = FirebaseFirestore.getInstance();
//        user = fAuth.getCurrentUser();
//
//        profilefirstname = findViewById(R.id.editTextTextPersonName2);
//        profilelastname = findViewById(R.id.editTextTextPersonName3);
//        profileusername = findViewById(R.id.editTextTextPersonName4);
//        profileemail = findViewById(R.id.editTextTextEmailAddress);
//        profilephone = findViewById(R.id.editTextTextPassword2);
//        saveBtn = findViewById(R.id.mSaveBtn);
//        deleteBtn = findViewById(R.id.mDeleteBtn);
//
//
//        profilefirstname.setText(firstname);
//        profilelastname.setText(lastname);
//        profileusername.setText(username);
//        profileemail.setText(email);
//        profilephone.setText(phone);
//
//
//        Log.d(TAG,"onCreate: " + firstname + " " + lastname + " " + username + " " + email + " " + phone);
//
//        editphoto = (TextView)findViewById(R.id.mEditPhotoTxt);
//        editphoto.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent12 = new Intent(EditProfile.this,AddPhoto.class);
//                startActivity(intent12);
//            }
//        });
//
//        saveBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (profilefirstname.getText().toString().isEmpty()
//                        || profilelastname.getText().toString().isEmpty()
//                        || profileusername.getText().toString().isEmpty()
//                        || profileemail.getText().toString().isEmpty()
//                        || profilephone.getText().toString().isEmpty()
//                ){
//                    Toast.makeText(EditProfile.this, "One or many fields are empty", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                String email = profileemail.getText().toString();
//                user.updateEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void unused) {
//                        DocumentReference docref = fStore.collection("users").document(user.getUid());
//                        Map<String,Object> edited = new HashMap<>();
//                        edited.put("email", email);
//                        edited.put("fname",profilefirstname.getText().toString());
//                        edited.put("lname", profilelastname.getText().toString());
//                        edited.put("phone", profilephone.getText().toString());
//                        edited.put("username", profileusername.getText().toString());
//                        docref.update(edited).addOnSuccessListener(new OnSuccessListener<Void>() {
//                            @Override
//                            public void onSuccess(Void unused) {
//                                Toast.makeText(EditProfile.this,"Profile updated",Toast.LENGTH_SHORT).show();
//                                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
//                                finish();
//                            }
//                        });
//
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(EditProfile.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//        });
//
//
//
//        deleteBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                DocumentReference docref = fStore.collection("users").document(user.getUid());
//                AlertDialog.Builder builder = new AlertDialog.Builder(EditProfile.this);
//                builder.setTitle("Are you sure ?");
//                builder.setMessage("Deletion is permanent");
//
//                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        deleteuser();
//                    }
//                    private void deleteuser() {
//                        fStore.collection("users").document(user.getUid()).delete()
//                                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                                    @Override
//                                    public void onComplete(@NonNull Task<Void> task) {
//                                        if (task.isSuccessful()){
//                                            Toast.makeText(EditProfile.this,"User deleted",Toast.LENGTH_SHORT).show();
//                                            finish();
//                                            startActivity(new Intent(EditProfile.this,LoginActivity.class));
//                                        }
//                                    }
//                                });
//                    }
//                });
//                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//
//                    }
//                });
//
//                AlertDialog ad = builder.create();
//                ad.show();
//
//            }
//        });
//
//
//    }
//
//
//}
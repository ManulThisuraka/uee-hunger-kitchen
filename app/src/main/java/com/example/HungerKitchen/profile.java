//package com.example.HungerKitchen;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//
//import androidx.appcompat.app.AlertDialog;
//import androidx.appcompat.app.AppCompatActivity;
//
//
//import android.app.Activity;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.net.Uri;
//import android.os.Bundle;
//import android.provider.MediaStore;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.firestore.DocumentReference;
//import com.google.firebase.firestore.DocumentSnapshot;
//import com.google.firebase.firestore.EventListener;
//import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.firestore.FirebaseFirestoreException;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.StorageReference;
//import com.google.firebase.storage.UploadTask;
//import com.squareup.picasso.Picasso;
//
//public class profile extends AppCompatActivity {
//
//    private static final int GALLERY_INTENT_CODE = 1023;
//    TextView firstname,lastname,email,username,phone;
//    FirebaseAuth fAuth;
//    FirebaseFirestore fstore;
//    String userID;
//    FirebaseUser user;
//    ImageView profileImage;
//    StorageReference storageReference;
//
//    Button editprofile, changeProfileImage;
//    Button feedback;
//    Button changepassword;
//    Button newsdetails;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_profile);
//
//        firstname = findViewById(R.id.firstName);
//        lastname = findViewById(R.id.lastName);
//        email = findViewById(R.id.Email);
//        username = findViewById(R.id.userName);
//        changepassword = findViewById(R.id.changepasswordBtn);
//        phone = findViewById(R.id.phonenumber);
//
//        profileImage = findViewById(R.id.imageView);
//        changeProfileImage = findViewById(R.id.changeprofileimage);
//
//        //firebase connection
//        fAuth = FirebaseAuth.getInstance();
//        fstore = FirebaseFirestore.getInstance();
//        storageReference = FirebaseStorage.getInstance().getReference();
//
//        //Calling firebase
//        StorageReference profileref = storageReference.child("users/"+fAuth.getCurrentUser().getUid()+"/profile.jpg");
//        profileref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//            @Override
//            public void onSuccess(Uri uri) {
//                Picasso.get().load(uri).into(profileImage);
//            }
//        });
//
//        userID = fAuth.getCurrentUser().getUid();
//        user = fAuth.getCurrentUser();
//
//        //Concatenation
//        DocumentReference documentReference = fstore.collection("users").document(userID);
//        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
//            @Override
//            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
//                if (value.exists()){
//                    firstname.setText(value.getString("fname"));
//                    lastname.setText(value.getString("lname"));
//                    email.setText(value.getString("email"));
//                    username.setText(value.getString("username"));
//                    phone.setText(value.getString("phone"));
//                }else{
//                    Log.d("tag","onEvent: Document does not exist");
//                }
//            }
//        });
//
//        //Password change
//        changepassword.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                final EditText resetPassword = new EditText(view.getContext());
//                final AlertDialog.Builder passwordresetdialog = new AlertDialog.Builder(view.getContext());
//                passwordresetdialog.setTitle("Reset Password ?");
//                passwordresetdialog.setMessage("Enter new password (>6 characters long)");
//                passwordresetdialog.setView(resetPassword);
//
//                passwordresetdialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        //extract email and send the reset link
//                        String newPassword = resetPassword.getText().toString();
//                        user.updatePassword(newPassword).addOnSuccessListener(new OnSuccessListener<Void>() {
//                            @Override
//                            public void onSuccess(Void unused) {
//                                Toast.makeText(profile.this,"Password reset successfully.", Toast.LENGTH_SHORT).show();
//
//                            }
//                        }).addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                Toast.makeText(profile.this,"Password reset failed", Toast.LENGTH_SHORT).show();
//
//                            }
//                        });
//
//                    }
//                });
//
//                passwordresetdialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        //close the dialog
//
//                    }
//                });
//
//                passwordresetdialog.create().show();
//            }
//        });
//
//        //Change Profile image
//        changeProfileImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //open gallery
//                Intent openGalleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                startActivityForResult(openGalleryIntent,1000);
//            }
//        });
//
//
//        newsdetails = findViewById(R.id.mViewNewsBtn);
//        newsdetails.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                    Intent intent = new Intent(profile.this, NewspaperPanelActivity.class);
//                    startActivity(intent);
//            }
//        });
//
//
//
//        editprofile = findViewById(R.id.mEditProfileBtn);
//        editprofile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(profile.this,EditProfile.class);
//                i.putExtra("firstname",firstname.getText().toString());
//                i.putExtra("lastname",lastname.getText().toString());
//                i.putExtra("username",username.getText().toString());
//                i.putExtra("email",email.getText().toString());
//                i.putExtra("phone",phone.getText().toString());
//                startActivity(i);
//            }
//        });
//
//        //Give feedback
//        feedback = (Button)findViewById(R.id.mGiveFeedbackBtn);
//        feedback.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent7 = new Intent(profile.this,Editfeedback.class);
//                startActivity(intent7);
//            }
//        });
//
//       // viewnews = findViewById(R.id.mViewNewsBtn);
//        //viewnews.setOnClickListener(new View.OnClickListener() {
//          //  @Override
//            //public void onClick(View view) {
//                //Intent intent14 = new Intent(profile.this,sidebar.class);
//               // startActivity(intent14);
//           // }
//      //  });
//
//
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == 1000){
//            if (resultCode == Activity.RESULT_OK){
//                Uri imageUri = data.getData();
//                //profileImage.setImageURI(imageUri);
//
//                uploadImageToFirebase(imageUri);
//
//            }
//        }
//
//    }
//
//    //Upload images to Firebase storage
//    private void uploadImageToFirebase(Uri imageUri) {
//        //upload image to firebase storage
//        final StorageReference fileRef = storageReference.child("users/"+fAuth.getCurrentUser().getUid()+"/profile.jpg");
//        fileRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                    @Override
//                    public void onSuccess(Uri uri) {Picasso.get().load(uri).into(profileImage); }
//                });
//
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(profile.this,"Failed", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//    //Logout from account
//    public void logout(View view) {
//        FirebaseAuth.getInstance().signOut(); //logout
//        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
//        finish();
//    }
//
//    public void addNews(View view){
//        Intent intent = new Intent(this, AdSubmission.class);
//        startActivity(intent);
//    }
//    public void MyAds(View view){
//        Intent intent = new Intent(this, AdsCustomer.class);
//        startActivity(intent);
//    }
//}
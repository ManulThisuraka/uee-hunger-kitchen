//package com.example.HungerKitchen;
//
//import android.annotation.SuppressLint;
//import android.app.AlertDialog;
//import android.content.DialogInterface;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.firebase.ui.database.FirebaseRecyclerAdapter;
//import com.firebase.ui.database.FirebaseRecyclerOptions;
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.auth.UserInfo;
//import com.google.firebase.database.FirebaseDatabase;
//import com.orhanobut.dialogplus.DialogPlus;
//import com.orhanobut.dialogplus.ViewHolder;
//
//import java.util.HashMap;
//import java.util.Map;
//
//// FirebaseRecyclerAdapter is a class provided by
//// FirebaseUI. it provides functions to bind, adapt and show
//// database contents in a Recycler View
//class NewspaperAdpter extends FirebaseRecyclerAdapter<AddNews, NewspaperAdpter.newsViewholder> {
//
//    public NewspaperAdpter(
//            @NonNull FirebaseRecyclerOptions<AddNews> options) {
//        super(options);
//    }
//
////     Function to bind the view in Card view(here
////     "person.xml") iwth data in
////     model class(here "person.class")
//
//
//    @Override
//    protected void
//    onBindViewHolder(@NonNull newsViewholder holder, @SuppressLint("RecyclerView") final int position, @NonNull AddNews model) {
//
//
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//
//        Log.d("abc",user.toString());
//        if (user != null) {
//            Log.d("qwer","User found null");
//            String uid = FirebaseAuth.getInstance().getUid();
//            for (UserInfo profile : user.getProviderData()) {
//                String providerId = profile.getProviderId();
//
//
//                holder.newsName.setText(model.getNewsName());
//                holder.date.setText(model.getDate());
//                holder.articleName.setText((model.getArticleName()));
//                Log.d("holder",providerId.toString());
//
//            }
//        }else{
//            Log.d("qwe","User was null");
//        }
////
////        -----------------------EDIT---------------------------------------
//
//        holder.idbtnEdit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                final DialogPlus dialogPlus = DialogPlus.newDialog(v.getContext())
//                        .setContentHolder(new ViewHolder(R.layout.activity_edit_news_list))
//                        .setExpanded(true, 1400)
//                        .create();
//
//                dialogPlus.show();
//
//                View view = dialogPlus.getHolderView();
//
//                EditText newsName = view.findViewById(R.id.inputName2);
//                EditText date = view.findViewById(R.id.inputDate2);
//                EditText articleName  = view.findViewById(R.id.inputArticles2);
//
//
//                Button btnUpdate = view.findViewById(R.id.butSubmit2);
//
//                newsName.setText(model.getNewsName());
//                date.setText(model.getDate());
//                articleName.setText(model.getArticleName());
//
//                dialogPlus.show();
//
//                btnUpdate.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                        Map<String, Object> map = new HashMap<>();
//                        map.put("newsName",  newsName.getText().toString());
//                        map.put("date",date.getText().toString());
//                        map.put("articleName", articleName.getText().toString());
//
//.child(getRef(position).getKey())
//                        DatabaseReference newref;
//                        String uid = FirebaseAuth.getInstance().getUid();
//                        Log.d("abcc", getRef(position).getKey());
//                        Log.d("map", map.toString());
//
//                        FirebaseDatabase.getInstance().getReference().child("News")
//                                .child(uid).child(getRef(position).getKey()).updateChildren(map)
//                                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                                    @Override
//                                    public void onSuccess(Void unused) {
//                                        Toast.makeText(holder.newsName.getContext(), "Data Updated Successfully.", Toast.LENGTH_SHORT).show();
//                                        dialogPlus.dismiss();
//
//
//                                    }
//                                })
//                                .addOnFailureListener(new OnFailureListener() {
//                                    @Override
//                                    public void onFailure(@NonNull Exception e) {
//                                        Toast.makeText(holder.newsName.getContext(), "Error While Updating.", Toast.LENGTH_SHORT).show();
//                                        dialogPlus.dismiss();
//
//                                    }
//                                });
//
//
//                    }
//                });
//
//
//            }
//        });
//
////        -----------------------DELETE---------------------------------------
//
//        holder.idbtnDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(holder.newsName.getContext());
//                builder.setTitle("Are You Sure ?");
//                builder.setMessage("Deleted data can't be Undo.");
//
//                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        String uid = FirebaseAuth.getInstance().getUid();
//                        FirebaseDatabase.getInstance().getReference().child("News")
//                                .child(uid).child(getRef(position).getKey()).removeValue();
//
//
//                    }
//                });
//                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        Toast.makeText(holder.newsName.getContext(), "Cancelled", Toast.LENGTH_SHORT).show();
//
//
//                    }
//                });
//                builder.show();
//
//
//
//            }
//        });
//    }
//
////
////     Function to tell the class about the Card view (here
////     "person.xml")in
////     which the data will be shown
//    @NonNull
//    @Override
//    public newsViewholder
//    onCreateViewHolder(@NonNull ViewGroup AddNews,
//                       int viewType) {
//        View view = LayoutInflater.from(AddNews.getContext()).inflate(R.layout.activity_person, AddNews, false);
//        return new NewspaperAdpter.newsViewholder(view);
//    }
//
////     Sub Class to create references of the views in Card
////     view (here "person.xml")
//    class newsViewholder extends RecyclerView.ViewHolder {
//        TextView newsName, date, articleName;
//        Button idbtnEdit, idbtnDelete;
//
//        public newsViewholder(@NonNull View itemView) {
//            super(itemView);
//
//            newsName = itemView.findViewById(R.id.inputName_1);
//            date = itemView.findViewById(R.id.inputDate_1);
//            articleName = itemView.findViewById(R.id.inputArticles_1);
//
//            idbtnEdit = (Button) itemView.findViewById(R.id.idbtnEdit);
//            idbtnDelete = (Button) itemView.findViewById(R.id.idbtnDelete);
//        }
//    }
//}

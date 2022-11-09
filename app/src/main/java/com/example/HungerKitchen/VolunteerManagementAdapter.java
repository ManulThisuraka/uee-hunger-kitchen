package com.example.HungerKitchen;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

// FirebaseRecyclerAdapter is a class provided by
// FirebaseUI. it provides functions to bind, adapt and show
// database contents in a Recycler View
class VolunteerManagementAdapter extends FirebaseRecyclerAdapter<Task, VolunteerManagementAdapter.taskViewholder> {

    public VolunteerManagementAdapter(
            @NonNull FirebaseRecyclerOptions<Task> options) {
        super(options);
    }

    // Function to bind the view in Card view(here
    // "person.xml") iwth data in
    // model class(here "person.class")


    @Override
    protected void
    onBindViewHolder(@NonNull taskViewholder holder, @SuppressLint("RecyclerView") final int position, @NonNull Task model) {


        //FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        //Log.d("abc",user.toString());
        //if (user != null) {
            //Log.d("qwer","User found null");
            //String uid = FirebaseAuth.getInstance().getUid();
           //for (UserInfo profile : user.getProviderData()) {
                //String providerId = profile.getProviderId();
                final String key = getRef(position).getKey();

                holder.Cname.setText(model.getName());
                holder.Cdate.setText(model.getDate());
                holder.Crole.setText(model.getRole());
                holder.Cskills.setText(model.getSkills());
                //Log.d("holder",providerId.toString());

          // }
        //}else{
            //Log.d("qwe","User was null");
        //}
        holder.btnAssign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),AssignTask.class);
                intent.putExtra("key",key);
                v.getContext().startActivity(intent);
            }
        });
    }

    // Function to tell the class about the Card view (here
    // "person.xml")in
    // which the data will be shown
    @NonNull
    @Override
    public taskViewholder
    onCreateViewHolder(@NonNull ViewGroup AddNews,
                       int viewType) {
        View view = LayoutInflater.from(AddNews.getContext()).inflate(R.layout.activity_volunteer_management_box, AddNews, false);
        return new VolunteerManagementAdapter.taskViewholder(view);
    }

    // Sub Class to create references of the views in Card
    // view (here "person.xml")
    class taskViewholder extends RecyclerView.ViewHolder {
        TextView Cname, Cdate, Crole, Cskills;
        Button btnAssign;

        public taskViewholder(@NonNull View itemView) {
            super(itemView);

            Cname = itemView.findViewById(R.id.TVname);
            Cdate = itemView.findViewById(R.id.TVdate);
            Crole = itemView.findViewById(R.id.TVrole);
            Cskills = itemView.findViewById(R.id.TVskills);

            btnAssign = (Button) itemView.findViewById(R.id.BTNassign);
        }
    }
}

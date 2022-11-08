package com.example.HungerKitchen;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

// FirebaseRecyclerAdapter is a class provided by
// FirebaseUI. it provides functions to bind, adapt and show
// database contents in a Recycler View
class UserManagementAdapter extends FirebaseRecyclerAdapter<User, UserManagementAdapter.taskViewholder> {

    public UserManagementAdapter(@NonNull FirebaseRecyclerOptions<User> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull taskViewholder holder, @SuppressLint("RecyclerView") int position, @NonNull User model) {
        holder.fname.setText(model.getFirstname());
        holder.lname.setText(model.getLastname());
        holder.email.setText(model.getEmail());
        holder.phone.setText(model.getPhone());

        // Delete
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.fname.getContext());
                builder.setTitle("Are You Sure ?");
                builder.setMessage("Deleted data can't be Undo.");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("users").child(Objects.requireNonNull(getRef(position).getKey())).removeValue();

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(holder.fname.getContext(), "Cancelled", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });
    }

    // Function to tell the class about the Card view (here
    // "person.xml")in
    // which the data will be shown
    @NonNull
    @Override
    public taskViewholder onCreateViewHolder(@NonNull ViewGroup AddNews, int viewType) {
        View view = LayoutInflater.from(AddNews.getContext()).inflate(R.layout.activity_user_management_card_view, AddNews, false);
        return new UserManagementAdapter.taskViewholder(view);
    }

    // Sub Class to create references of the views in Card
    // view (here "person.xml")
    class taskViewholder extends RecyclerView.ViewHolder {
        TextView fname, lname, email, phone;
        Button btnDelete;

        public taskViewholder(@NonNull View itemView) {
            super(itemView);

            fname = itemView.findViewById(R.id.txtFname);
            lname = itemView.findViewById(R.id.txtLname);
            email = itemView.findViewById(R.id.txtEmail);
            phone = itemView.findViewById(R.id.txtPhone);

            btnDelete = (Button) itemView.findViewById(R.id.btnDelete);
        }
    }
}

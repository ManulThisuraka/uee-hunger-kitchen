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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

// FirebaseRecyclerAdapter is a class provided by
// FirebaseUI. it provides functions to bind, adapt and show
// database contents in a Recycler View
class FundManagementAdapter extends FirebaseRecyclerAdapter<FundRecord, FundManagementAdapter.fundViewholder> {

    public FundManagementAdapter(
            @NonNull FirebaseRecyclerOptions<FundRecord> options) {
        super(options);
    }

    // Function to bind the view in Card view(here
    // "person.xml") iwth data in
    // model class(here "person.class")

    @Override
    protected void
    onBindViewHolder(@NonNull fundViewholder holder, @SuppressLint("RecyclerView") final int position, @NonNull FundRecord model) {

        holder.Fname.setText(model.getName());
        holder.Fnumber.setText(model.getNumber());
        holder.Fnic.setText(model.getNIC());
        holder.Fpaytype.setText(model.getPaymentType());
        holder.Famount.setText(model.getAmount());

        // Delete
        holder.btnFDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.Fname.getContext());
                builder.setTitle("Are You Sure ?");
                builder.setMessage("Deleted data can't be Undo.");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String uid = FirebaseAuth.getInstance().getUid();
                        FirebaseDatabase.getInstance().getReference().child("CashDonations")
                                .child(getRef(position).getKey()).removeValue();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(holder.Fname.getContext(), "Cancelled", Toast.LENGTH_SHORT).show();
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
    public fundViewholder
    onCreateViewHolder(@NonNull ViewGroup AddFunds,
                       int viewType) {
        View view = LayoutInflater.from(AddFunds.getContext()).inflate(R.layout.activity_fund_management_box, AddFunds, false);
        return new fundViewholder(view);
    }

    // Sub Class to create references of the views in Card
    // view (here "person.xml")
    class fundViewholder extends RecyclerView.ViewHolder {
        TextView Fname, Fnumber, Fnic, Fpaytype, Famount;
        Button btnFDelete;

        public fundViewholder(@NonNull View itemView) {
            super(itemView);

            Fname = itemView.findViewById(R.id.AFdonorname1);
            Fnumber = itemView.findViewById(R.id.AFcontact1);
            Fnic = itemView.findViewById(R.id.AFnic1);
            Fpaytype = itemView.findViewById(R.id.AFtype1);
            Famount = itemView.findViewById(R.id.AFinputAmount1);

            btnFDelete = (Button) itemView.findViewById(R.id.BTFDelete);
        }
    }
}


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
class InventoryManagementAdapter extends FirebaseRecyclerAdapter<InventoryRecord, InventoryManagementAdapter.inventoryViewholder> {

    public InventoryManagementAdapter(
            @NonNull FirebaseRecyclerOptions<InventoryRecord> options) {
        super(options);
    }

    // Function to bind the view in Card view(here
    // "person.xml") iwth data in
    // model class(here "person.class")


    @Override
    protected void
    onBindViewHolder(@NonNull inventoryViewholder holder, @SuppressLint("RecyclerView") final int position, @NonNull InventoryRecord model) {

                holder.Iname.setText(model.getDonorName());
                holder.Icontact.setText(model.getContactNo());
                holder.Invtype.setText(model.getInvType());
                holder.Iquantity.setText(model.getInvQty());
                holder.Iexpectdate.setText(model.getExpDate());

        // Delete
        holder.btnIDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.Iname.getContext());
                builder.setTitle("Are You Sure ?");
                builder.setMessage("Deleted data can't be Undo.");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String uid = FirebaseAuth.getInstance().getUid();
                        FirebaseDatabase.getInstance().getReference().child("InventoryItems")
                                .child(getRef(position).getKey()).removeValue();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(holder.Iname.getContext(), "Cancelled", Toast.LENGTH_SHORT).show();
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
    public inventoryViewholder
    onCreateViewHolder(@NonNull ViewGroup AddInventory,
                       int viewType) {
        View view = LayoutInflater.from(AddInventory.getContext()).inflate(R.layout.activity_inventory_management_box, AddInventory, false);
        return new inventoryViewholder(view);
    }

    // Sub Class to create references of the views in Card
    // view (here "person.xml")
    class inventoryViewholder extends RecyclerView.ViewHolder {
        TextView Iname, Icontact, Invtype, Iquantity, Iexpectdate;
        Button btnIDelete;

        public inventoryViewholder(@NonNull View itemView) {
            super(itemView);

            Iname = itemView.findViewById(R.id.INVname);
            Icontact = itemView.findViewById(R.id.INVcontact);
            Invtype = itemView.findViewById(R.id.INVtype);
            Iquantity = itemView.findViewById(R.id.INVquantity);
            Iexpectdate = itemView.findViewById(R.id.INVexpectdate);

            btnIDelete = (Button) itemView.findViewById(R.id.BTNIassign);
        }
    }
}

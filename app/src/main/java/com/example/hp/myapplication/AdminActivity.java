package com.example.hp.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hp.myapplication.Model.Request;
import com.example.hp.myapplication.ViewHolder.OrderViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.FirebaseDatabase;

public class AdminActivity extends AppCompatActivity {
    FirebaseRecyclerAdapter<Request, OrderViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        RecyclerView recyclerView = findViewById(R.id.order_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FirebaseRecyclerAdapter<Request, OrderViewHolder>(Request.class, R.layout.item_order,
                OrderViewHolder.class, FirebaseDatabase.getInstance().getReference("Requests")) {
            @Override
            protected void populateViewHolder(OrderViewHolder viewHolder, Request model, int position) {
                viewHolder.name.setText(model.getName());
                viewHolder.address.setText(model.getAddress());
                viewHolder.phone_no.setText(model.getPhone());
                viewHolder.total_price.setText(model.getTotal());


                viewHolder.setItemClickListener((view, position1, isLongClick) -> {
                    if (view.getId() == R.id.done) {
                        FirebaseDatabase.getInstance().getReference("Requests").child(adapter.getRef(position1).getKey()).removeValue();
                    } else {
                        //start activity
                        Intent foodDetails = new Intent(AdminActivity.this, OrderDetails.class);
                        foodDetails.putExtra("OrderId", adapter.getRef(position1).getKey());
                        startActivity(foodDetails);
                    }
                });

            }
        };
        Log.d("TAG", "" + adapter.getItemCount());
        recyclerView.setAdapter(adapter);
    }
}
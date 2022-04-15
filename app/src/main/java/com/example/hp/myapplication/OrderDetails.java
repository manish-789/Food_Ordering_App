package com.example.hp.myapplication;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hp.myapplication.Model.FoodOrder;
import com.example.hp.myapplication.ViewHolder.OrderFoodViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OrderDetails extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference foodList;
    String orderId = "";
    FirebaseRecyclerAdapter<FoodOrder, OrderFoodViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);

        //Firebase

        database = FirebaseDatabase.getInstance();
        foodList = database.getReference("Requests");
        recyclerView = findViewById(R.id.recycler_food);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //get intent here
        if (getIntent() != null)
            orderId = getIntent().getStringExtra("OrderId");
        Log.d("TAG", orderId);
        if (!orderId.isEmpty()) {
            loadListFood(orderId);
        }
    }

    private void loadListFood(String orderId) {
        adapter = new FirebaseRecyclerAdapter<FoodOrder, OrderFoodViewHolder>(FoodOrder.class, R.layout.order_food_item,
                OrderFoodViewHolder.class, foodList.child(orderId).child("foods").orderByValue())
                //is like select query
        {
            @Override
            protected void populateViewHolder(OrderFoodViewHolder viewHolder, FoodOrder model, int position) {
                viewHolder.productName.setText(model.getProductName());
                viewHolder.discount.setText(model.getDiscount());
                viewHolder.price.setText(model.getPrice());
                viewHolder.quantity.setText(model.getQuantity());
                viewHolder.totalPrice.setText(String.valueOf((Integer.parseInt(model.getPrice()) - Integer.parseInt(model.getDiscount())) * Integer.parseInt(model.getQuantity())));
            }
        };
        recyclerView.setAdapter(adapter);
    }
}

package com.example.hp.myapplication.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.hp.myapplication.R;

public class OrderFoodViewHolder extends RecyclerView.ViewHolder {

    public TextView productName, price, discount, quantity, totalPrice;

    public OrderFoodViewHolder(View itemView) {
        super(itemView);
        productName = itemView.findViewById(R.id.productName);
        price = itemView.findViewById(R.id.price);
        discount = itemView.findViewById(R.id.discount);
        quantity = itemView.findViewById(R.id.quantity);
        totalPrice = itemView.findViewById(R.id.total_price);
    }
}

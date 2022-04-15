package com.example.hp.myapplication.ViewHolder;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.hp.myapplication.Interface.ItemClickListener;
import com.example.hp.myapplication.R;

public class OrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView name, address, total_price, phone_no;
    public ImageButton done;

    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public OrderViewHolder(View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.name);
        address = itemView.findViewById(R.id.address);
        total_price = itemView.findViewById(R.id.total_price);
        phone_no = itemView.findViewById(R.id.phone_no);
        done = itemView.findViewById(R.id.done);
        done.setOnClickListener(this);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getBindingAdapterPosition(),false);
    }
}

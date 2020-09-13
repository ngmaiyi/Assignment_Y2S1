package com.example.assignment_y2s1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    private Context mContext;
    private ArrayList<Products> productsList;


    public ItemAdapter(Context mContext, ArrayList<Products> productsList) {
        this.mContext = mContext;
        this.productsList = productsList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_row, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.purchasedItems.setText(productsList.get(position).getName());
        holder.price.setText(productsList.get(position).getPrice());
        holder.quantity.setText(productsList.get(position).getQuantity());

    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView purchasedItems, quantity, price;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            purchasedItems = itemView.findViewById(R.id.purchased_items_txt);
            quantity = itemView.findViewById(R.id.quantity_txt);
            price = itemView.findViewById(R.id.price_txt);
        }
    }
}

package com.example.assignment_y2s1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.ViewHolder> {

    private static final String Tag = "RecyclerView";
    private Context mContext;
    private ArrayList<Details> detailList;



    public DetailAdapter(Context mContext, ArrayList<Details> detailsList) {
        this.mContext = mContext;
        this.detailList = detailsList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.details_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        
        holder.name.setText(detailList.get(position).getName());
        holder.price.setText(detailList.get(position).getPrice());
        holder.quantity.setText(detailList.get(position).getQuantity());

    }

    @Override
    public int getItemCount() {
        return detailList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView name, price, quantity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.textName);
            price = itemView.findViewById(R.id.textPrice);
            quantity = itemView.findViewById(R.id.textQuantity);

        }
    }
}

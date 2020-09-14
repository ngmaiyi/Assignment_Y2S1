package com.example.assignment_y2s1;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView NameProd, PriceProd;
    String prodID;
    public ImageView ImageProd;
    public ItemClickListener listener;
    public  ViewHolder(View itemView)
    {
        super(itemView);
        ImageProd = (ImageView) itemView.findViewById(R.id.rImageView);
        NameProd = (TextView) itemView.findViewById(R.id.name);
        PriceProd = (TextView) itemView.findViewById(R.id.price);

    }

    public void setItemClickListener(ItemClickListener listener)
    {
        this.listener=listener;
    }

    @Override
    public void onClick(View view)
    {
        listener.onClick(view,getAdapterPosition(),false);
    }

}

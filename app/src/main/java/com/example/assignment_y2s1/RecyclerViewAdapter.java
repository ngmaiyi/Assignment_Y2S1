package com.example.assignment_y2s1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class RecyclerViewAdapter {
    private Context mContext;
    private AddressAdapter mAddressAdapter;

    public void setAdapter(RecyclerView recyclerView, Context context, List<Address> addressList, List<String> keys) {
        mContext = context;
        mAddressAdapter = new AddressAdapter(addressList, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mAddressAdapter);
    }

    class AddressItemView extends RecyclerView.ViewHolder {
        private TextView mAddress, mPostcode, mCity;

        private String key;

        public AddressItemView(ViewGroup parent) {
            super(LayoutInflater.from(mContext).inflate(R.layout.address_list_item, parent, false));

            mAddress = (TextView) itemView.findViewById(R.id.txtAddress);
            mPostcode = (TextView) itemView.findViewById(R.id.txtPostcode);
            mCity = (TextView) itemView.findViewById(R.id.txtCity);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, AddressDetailsActivity.class);
                    intent.putExtra("key", key);
                    intent.putExtra("address", mAddress.getText().toString());
                    intent.putExtra("postcode", mPostcode.getText().toString());
                    intent.putExtra("city", mCity.getText().toString());

                    mContext.startActivity(intent);
                }
            });
        }
        public void bind(Address address, String key) {
            mAddress.setText(address.getAddress());
            mPostcode.setText(address.getPostcode());
            mCity.setText(address.getCity());
            this.key = key;
        }
    }
    class AddressAdapter extends RecyclerView.Adapter<AddressItemView>{
        private List<Address> mAddressList;
        private List<String> mKeys;

        public AddressAdapter(List<Address> mAddressList, List<String> mKeys) {
            this.mAddressList = mAddressList;
            this.mKeys = mKeys;
        }


        @NonNull
        @Override
        public AddressItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new AddressItemView(parent);
        }


        @Override
        public void onBindViewHolder(@NonNull AddressItemView holder, int position) {
            holder.bind(mAddressList.get(position), mKeys.get(position));
        }


        @Override
        public int getItemCount() {
            return mAddressList.size();
        }
    }
}

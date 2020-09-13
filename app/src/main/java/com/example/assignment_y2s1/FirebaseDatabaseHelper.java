package com.example.assignment_y2s1;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelper {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRefAddress;
    private List<Address> addressList = new ArrayList<>();


    public interface DataStatus {
        void DataIsLoaded(List<Address> addressList, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }


    public FirebaseDatabaseHelper() {
        mDatabase = FirebaseDatabase.getInstance();
        mRefAddress = mDatabase.getReference("location2");
    }

    public void readAddress(final DataStatus dataStatus) {
        mRefAddress.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                addressList.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNode : dataSnapshot.getChildren()){
                    //for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    Address address = keyNode.getValue(Address.class);

//                    Address address = new Address();
//                    address.setAddress(snapshot.child("address").getValue().toString());
//                    address.setPostcode(snapshot.child("postcode").getValue().toString());
//                    address.setCity(snapshot.child("city").getValue().toString());

                    addressList.add(address);
                }
                dataStatus.DataIsLoaded(addressList, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void addAddress(Address address, final DataStatus dataStatus) {
        String key = mRefAddress.push().getKey();
        mRefAddress.child(key).setValue(address)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsInserted();
                    }
                });
    }

    public void updateAddress(String key, Address address, final DataStatus dataStatus) {
        mRefAddress.child(key).setValue(address)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsUpdated();
                    }
                });
    }

    public void deleteAddress(String key, final DataStatus dataStatus) {
        mRefAddress.child(key).setValue(null)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsDeleted();
                    }
                });
    }
}

package com.example.assignment_y2s1;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class PurchasedHistory extends AppCompatActivity {
    RecyclerView recyclerView;

    private DatabaseReference myRef;

    private ArrayList<Products> productsList;
    private ItemAdapter itemAdapter;

    private TextView prodPrice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchased_history);

        prodPrice = findViewById(R.id.price_txt);

        recyclerView = findViewById(R.id.recyclerview);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);



        myRef = FirebaseDatabase.getInstance().getReference("Cart List");

        productsList = new ArrayList<>();

        ClearAll();

        GetData();
    }

    private void GetData() {
        Query query = myRef.child("Product");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ClearAll();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Products products = new Products();

//                products.setImageUrl(dataSnapshot.child("image").getValue().toString());
                    products.setName(dataSnapshot.child("Name").getValue().toString());
                    products.setPrice("RM" + dataSnapshot.child("Price").getValue().toString());


                    productsList.add(products);
                }

                itemAdapter = new ItemAdapter(getApplicationContext(), productsList);
                recyclerView.setAdapter(itemAdapter);
                itemAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private void ClearAll() {
        if (productsList != null) {
            productsList.clear();

            if (itemAdapter != null) {
                itemAdapter.notifyDataSetChanged();
            }
        }

        productsList = new ArrayList<>();
    }
}
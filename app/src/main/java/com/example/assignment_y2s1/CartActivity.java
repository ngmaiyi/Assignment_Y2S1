package com.example.assignment_y2s1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    private DatabaseReference myRef;

    private ArrayList<Products> productsList;
    private RecyclerAdapter recyclerAdapter;
    String prodID="";
    private TextView priceText;
    ImageView imageview;
    private TextView prodPrice;
    private Double total_amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_main);

        total_amount = 0.0;

        priceText = findViewById(R.id.price_text);

        prodPrice = findViewById(R.id.product_price);

        recyclerView = findViewById(R.id.recyclerview);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        imageview=findViewById(R.id.imageview);

        myRef = FirebaseDatabase.getInstance().getReference("Cart List");

        productsList = new ArrayList<>();

        ClearAll();

        GetData();
    }
//    private void getWomenData(String prodID)
//    {
//        //set reference to get the data inside "Data" in firebase
//        DatabaseReference ref =FirebaseDatabase.getInstance().getReference().child("Women");
//
//        ref.child(prodID).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if(dataSnapshot.exists())
//                {
//                    Member member = dataSnapshot.getValue(Member.class);
//                    productname.setText(member.getNameProd());
//                    prodPrice.setText(String.valueOf(member.getPriceProd()));
////                    total_amount += Double.parseDouble(String.valueOf(dataSnapshot.child("Price").getValue()));
////                    priceText.setText("RM" + String.valueOf(total_amount));
//                    Picasso.get().load(member.getImageProd()).into(imageview);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }
//
//    private void getMenData(String prodID)
//    {
//        DatabaseReference ref =FirebaseDatabase.getInstance().getReference().child("Men");
//
//        ref.child(prodID).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if(dataSnapshot.exists())
//                {
//                    Member member = dataSnapshot.getValue(Member.class);
//                    productname.setText(member.getNameProd());
//                    prodPrice.setText(String.valueOf(member.getPriceProd()));
////                    total_amount += Double.parseDouble(String.valueOf(dataSnapshot.child("Price").getValue()));
////                    priceText.setText("RM" + String.valueOf(total_amount));
//                    Picasso.get().load(member.getImageProd()).into(imageview);
//                }
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }
//
//    private void getKidData(String prodID)
//    {
//        DatabaseReference ref =FirebaseDatabase.getInstance().getReference().child("Kids");
//
//        ref.child(prodID).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if(dataSnapshot.exists())
//                {
//                    Member member = dataSnapshot.getValue(Member.class);
//                    productname.setText(member.getNameProd());
//                    prodPrice.setText(String.valueOf(member.getPriceProd()));
////                    total_amount += Double.parseDouble(String.valueOf(dataSnapshot.child("Price").getValue()));
////                    priceText.setText("RM" + String.valueOf(total_amount));
//                    Picasso.get().load(member.getImageProd()).into(imageview);
//                }
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }
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

                total_amount += Double.parseDouble(String.valueOf(dataSnapshot.child("Price").getValue()));

                productsList.add(products);
            }

            recyclerAdapter = new RecyclerAdapter(getApplicationContext(), productsList);
            recyclerView.setAdapter(recyclerAdapter);
            recyclerAdapter.notifyDataSetChanged();
            priceText.setText("RM" + String.valueOf(total_amount));
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    });

}

    private void ClearAll() {
        if (productsList != null) {
            productsList.clear();

            if (recyclerAdapter != null) {
                recyclerAdapter.notifyDataSetChanged();
            }
        }

        productsList = new ArrayList<>();
    }


    public void CheckoutActivity(View view) {
        Intent intent = new Intent(this, CheckoutActivity.class);
        startActivity(intent);
    }
}
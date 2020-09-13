package com.example.assignment_y2s1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class AddToCart extends AppCompatActivity {
    TextView name;
    TextView price;
    ImageView image;
    TextView quantity;
    Button btnless;
    Button btnadd;
    Button btnaddtocart;
    String prodID = "";
    int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart);
        prodID = getIntent().getStringExtra("prodID");
        name = findViewById(R.id.nameProd);
        price = findViewById(R.id.priceProd);
        image = findViewById(R.id.ImageView);
        quantity=findViewById(R.id.quantity);
        btnless=findViewById(R.id.btnLess);
        btnadd=findViewById(R.id.btnAdd);
        btnaddtocart=findViewById(R.id.buttonAddCart);

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ++count;
                if (quantity != null)
                    quantity.setText(Integer.toString(count));
            }
        });

        btnless.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                --count;
                if (quantity != null)
                    quantity.setText(Integer.toString(count));
            }
        });

        getWomenData(prodID);
        getMenData(prodID);
        getKidsData(prodID);
        btnaddtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddProductToCart();
            }
        });

    }

    private void getWomenData(String prodID)
    {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Data");

        ref.child(prodID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    Member member = dataSnapshot.getValue(Member.class);
                    name.setText(member.getNameProd());
                    price.setText("RM" + member.getPriceProd());
                    Picasso.get().load(member.getImageProd()).into(image);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void getMenData(String prodID)
    {
        DatabaseReference ref =FirebaseDatabase.getInstance().getReference().child("Men");

        ref.child(prodID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    Member member = dataSnapshot.getValue(Member.class);
                    name.setText(member.getNameProd());
                    price.setText("RM" + member.getPriceProd());
                    Picasso.get().load(member.getImageProd()).into(image);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void getKidsData(String prodID)
    {
        DatabaseReference ref =FirebaseDatabase.getInstance().getReference().child("Men");

        ref.child(prodID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    Member member = dataSnapshot.getValue(Member.class);
                    name.setText(member.getNameProd());
                    price.setText("RM" + member.getPriceProd());
                    Picasso.get().load(member.getImageProd()).into(image);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void AddProductToCart()
    {
        String date,time;
        Calendar Date = Calendar.getInstance();
        SimpleDateFormat DateFormat = new SimpleDateFormat("dd/MM/yyyy");
        date = DateFormat.format(Date.getTime());
        SimpleDateFormat TimeFormat = new SimpleDateFormat("a HH:mm:ss");
        time = TimeFormat.format(Date.getTime());

        final DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Cart List");
        final HashMap<String,Object> map = new HashMap<>();
        map.put("prodID",prodID);
        map.put("NameProd1",name.getText().toString());
        map.put("PriceProd1",price.getText().toString());
        map.put("Quantity Order",quantity.getText());
        map.put("Order Date",date);
        map.put("Order Time",time);


        ref.child("Product")
                .child(prodID)
                .updateChildren(map)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(AddToCart.this,"Added",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(AddToCart.this,ProductDetailsActivity.class);
                            startActivity(intent);
                        }
                    }
                });


    }
}
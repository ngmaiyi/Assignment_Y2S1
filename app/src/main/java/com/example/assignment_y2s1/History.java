package com.example.assignment_y2s1;

import android.os.Bundle;

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

public class History extends AppCompatActivity {
    RecyclerView recyclerView;

    private DatabaseReference myRef;

    private ArrayList<Details> detailsList;
    private DetailAdapter detailAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        recyclerView = findViewById(R.id.recycleView2);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        myRef = FirebaseDatabase.getInstance().getReference("Cart List");

        detailsList = new ArrayList<>();

        GetDataFromFirebase();

        ClearAll();

    }

    private void GetDataFromFirebase(){

        Query query = myRef.child("Product");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ClearAll();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Details details = new Details();

//                products.setImageUrl(dataSnapshot.child("image").getValue().toString());
                    details.setName(dataSnapshot.child("Name").getValue().toString());
                    details.setPrice("RM" + dataSnapshot.child("Price").getValue().toString());
                    details.setQuantity(dataSnapshot.child("Quantity Order").getValue().toString());


                    detailsList.add(details);
                }

                detailAdapter = new DetailAdapter(getApplicationContext(), detailsList);
                recyclerView.setAdapter(detailAdapter);
                detailAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void ClearAll(){
        if (detailsList != null){
            detailsList.clear();

            if (detailAdapter != null) {
                detailAdapter.notifyDataSetChanged();
            }
        }

        detailsList = new ArrayList<>();
    }

}
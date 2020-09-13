package com.example.assignment_y2s1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class cust_main extends AppCompatActivity {

    private Button button;
    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cust_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_address);
        new FirebaseDatabaseHelper().readAddress(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<Address> addressList, List<String> keys) {
                findViewById(R.id.loading).setVisibility(View.GONE);
                new RecyclerViewAdapter().setAdapter(mRecyclerView, cust_main.this,
                        addressList, keys);
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });

        button = (Button) findViewById(R.id.addBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cust_main.this, NewAddressActivity.class);
                startActivity(intent);
            }
        });
    }
}
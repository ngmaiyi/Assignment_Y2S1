package com.example.assignment_y2s1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class NewAddressActivity extends AppCompatActivity {

    private EditText mTxtAddress, mTxtPostcode, mTxtCity;
    private Button addBtn, backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_address);
        mTxtAddress = (EditText) findViewById(R.id.add_address);
        mTxtPostcode = (EditText) findViewById(R.id.add_postcode);
        mTxtCity = (EditText) findViewById(R.id.add_city);

        addBtn = (Button) findViewById(R.id.btnAdd);
        backBtn = (Button) findViewById(R.id.btnBack);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Address address = new Address();
                address.setAddress(mTxtAddress.getText().toString());
                address.setPostcode(mTxtPostcode.getText().toString());
                address.setCity(mTxtCity.getText().toString());
                new FirebaseDatabaseHelper().addAddress(address, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Address> addressList, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {
                        Toast.makeText(NewAddressActivity.this, "The address has been added successfully."
                                , Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void DataIsUpdated() {

                    }

                    @Override
                    public void DataIsDeleted() {

                    }
                });
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); return;
            }
        });
    }
}
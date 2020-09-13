package com.example.assignment_y2s1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class AddressDetailsActivity extends AppCompatActivity {

    private EditText mTxtAddress, mTxtPostcode, mTxtCity;
    private Button mUpdateBtn, mDelBtn, mBackBtn;

    private String key, address, postcode, city;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_details);

        key = getIntent().getStringExtra("key");
        address = getIntent().getStringExtra("address");
        postcode = getIntent().getStringExtra("postcode");
        city = getIntent().getStringExtra("city");

        mTxtAddress = (EditText) findViewById(R.id.edit_address);
        mTxtPostcode = (EditText) findViewById(R.id.edit_postcode);
        mTxtCity = (EditText) findViewById(R.id.edit_city);

        mUpdateBtn = (Button) findViewById(R.id.btnUpdate);
        mDelBtn = (Button) findViewById(R.id.btnDelete);
        mBackBtn = (Button) findViewById(R.id.btnBack);


        mUpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Address address = new Address();
                address.setAddress(mTxtAddress.getText().toString());
                address.setPostcode(mTxtPostcode.getText().toString());
                address.setCity(mTxtCity.getText().toString());

                new FirebaseDatabaseHelper().updateAddress(key, address, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Address> addressList, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {
                        Toast.makeText(AddressDetailsActivity.this, "Address has been updated.",
                                Toast.LENGTH_LONG).show();
                        finish(); return;
                    }

                    @Override
                    public void DataIsDeleted() {

                    }
                });
            }
        });

        mDelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new FirebaseDatabaseHelper().deleteAddress(key, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Address> addressList, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {

                    }

                    @Override
                    public void DataIsDeleted() {
                        Toast.makeText(AddressDetailsActivity.this, "The address has been deleted",
                                Toast.LENGTH_LONG).show();
                        finish(); return;
                    }
                });
            }
        });
        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); return;
            }
        });
    }



}
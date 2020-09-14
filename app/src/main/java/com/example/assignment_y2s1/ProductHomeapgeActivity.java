package com.example.assignment_y2s1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProductHomeapgeActivity extends AppCompatActivity {
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_homeapge);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.action_account);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_product:
                        startActivity(new Intent(getApplicationContext()
                                , ProductHomeapgeActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.action_cart:
                        startActivity(new Intent(getApplicationContext()
                                , CartActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.action_account:
                        startActivity(new Intent(getApplicationContext()
                                , accountPage.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }

    public void womenProd(View view){
        button=(Button) findViewById(R.id.btnWomen);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProductHomeapgeActivity.this,WomenProdActivity.class));
            }
        });
    }

    public void menProd(View view){
        button=(Button) findViewById(R.id.btnMen);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProductHomeapgeActivity.this,MenProdActivity.class));
            }
        });
    }

    public void kidProd(View view){

        button=(Button) findViewById(R.id.btnKid);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProductHomeapgeActivity.this,KidProdActivity.class));
            }
        });
    }
}
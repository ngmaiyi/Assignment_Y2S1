package com.example.assignment_y2s1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProductHomeapgeActivity extends AppCompatActivity {
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_homeapge);
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
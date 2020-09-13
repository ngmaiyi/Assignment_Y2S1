package com.example.assignment_y2s1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class ProductDetailsActivity extends AppCompatActivity {
    EditText edittext;
    TextView name;
    TextView price;
    Button buttonAddtocart;
    Button button1;
    ImageView image;
    Button btnComment;
    FirebaseDatabase database;
    DatabaseReference ref;
    //    int prod =0;
    int count=0;
    Member member;
    String prodID = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        //get prodID for setting the content
        prodID = getIntent().getStringExtra("prodID");

        // set variable id
        name = findViewById(R.id.name2);
        price = findViewById(R.id.price2);
        image = findViewById(R.id.rImageView2);
        buttonAddtocart = findViewById(R.id.btnAddtoCart);
        btnComment=findViewById(R.id.btnSend);
        button1 = findViewById(R.id.btnCheckOut);

        //Set id for edit text
        edittext = findViewById(R.id.txtComment);


        //click to open add to cart activity
        buttonAddtocart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(ProductDetailsActivity.this, AddToCart.class);
                intent.putExtra("prodID",prodID);
                startActivity(intent);
            }

        });

        //function call for get prodID for setting content
        CheckOut();
        getWomenData(prodID);
        getMenData(prodID);
        getKidData(prodID);
    }

    private void CheckOut() {

        //open check Out activity
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductDetailsActivity.this, CheckoutActivity.class);
                intent.putExtra("prodID",prodID);
                startActivity(intent);
            }
        });
    }

    private void getWomenData(String prodID)
    {
        //set reference to get the data inside "Data" in firebase
        DatabaseReference ref =FirebaseDatabase.getInstance().getReference().child("Women");

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

    private void getKidData(String prodID)
    {
        DatabaseReference ref =FirebaseDatabase.getInstance().getReference().child("Kids");

        ref.child(prodID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    Member member = dataSnapshot.getValue(Member.class);
                    name.setText(member.getNameProd());
                    price.setText("RM"+ member.getPriceProd());
                    Picasso.get().load(member.getImageProd()).into(image);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void showToast(View view) {
        //set member as new member to store the new data
        member=new Member();

        //data path is "Comment"
        ref=database.getInstance().getReference().child("Comment");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    //count is the number value in the "Comment"
                    count=(int) dataSnapshot.getChildrenCount();
                }
                else{
                    ///
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //if button clicked
        btnComment.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                //set the data in database
                member.setNameProd(name.getText().toString());
                member.setComment(edittext.getText().toString());
                //if button clicked count will +1 as new data
                ref.child(String.valueOf(count + 1)).setValue(member);
                //show toast
                Toast toast = Toast.makeText(ProductDetailsActivity.this, R.string.send_message, Toast.LENGTH_SHORT);
                toast.show();
                //set the edit text as empty
                edittext.setText("");
            }
        });


    }
}
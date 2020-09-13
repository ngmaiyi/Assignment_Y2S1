package com.example.assignment_y2s1;


import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class ContactUs extends AppCompatActivity {
    EditText editTextTextPersonName, editTextTextEmailAddress, editTextTextMultiLine;
    Button buttonSend;
    Feedback feedback;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        editTextTextPersonName = (EditText) findViewById(R.id.editTextTextPersonName);
        editTextTextEmailAddress = (EditText) findViewById(R.id.editTextTextEmailAddress);
        editTextTextMultiLine = (EditText) findViewById(R.id.editTextTextMultiLine);
        feedback = new Feedback();
        buttonSend = (Button) findViewById(R.id.buttonSend);

        myRef = FirebaseDatabase.getInstance().getReference().child("Contact Us");


        buttonSend.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                feedback.setName(editTextTextPersonName.getText().toString().trim());
                feedback.setEmail(editTextTextEmailAddress.getText().toString().trim());
                feedback.setMessage(editTextTextMultiLine.getText().toString().trim());
                myRef.setValue(feedback);
                Toast.makeText(ContactUs.this, "Your Message was Sent!", Toast.LENGTH_SHORT).show();
            }
//
//                final HashMap<String,Object> map = new HashMap<>();
//
//                map.put("Name",editTextTextPersonName.getText().toString());
//                map.put("Email",editTextTextEmailAddress.getText().toString());
//                map.put("Feedback",editTextTextMultiLine.getText().toString());
//
//                ref.child("Contact Us")
//
//
//                        .addOnCompleteListener(new OnCompleteListener<Void>() {
//                            @Override
//                            public void onComplete(@NonNull Task<Void> task) {
//                                if (task.isSuccessful())
//                                {
//                                    Toast.makeText(AddToCart.this,"Added",Toast.LENGTH_SHORT).show();
//                                    Intent intent = new Intent(AddToCart.this,CartActivity.class);
//                                    intent.putExtra("prodID",prodID);
//                                    startActivity(intent);
//                                }
//                            }
//                        });
//            }

//                final String name = editTextTextPersonName.getText().toString();
//                final String email = editTextTextEmailAddress.getText().toString();
//                final String message = editTextTextMultiLine.getText().toString();
//
//                DatabaseReference child_name = myRef.child("Name");
//                child_name.setValue(name);
//                if (name.isEmpty()) {
//                    editTextTextPersonName.setError("This is a required field!");
//                    buttonSend.setEnabled(false);
//                }
//                else {
//
//                    editTextTextPersonName.setError(null);
//                    buttonSend.setEnabled(true);
//                }
//
//                DatabaseReference child_email = myRef.child("Email");
//                child_email.setValue(email);
//                if (email.isEmpty()) {
//                    editTextTextEmailAddress.setError("This is a required field!");
//                    buttonSend.setEnabled(false);
//                }
//                else {
//                    editTextTextEmailAddress.setError(null);
//                    buttonSend.setEnabled(true);
//                }
//
//                DatabaseReference child_message = myRef.child("Message");
//                child_message.setValue(message);
//                if (email.isEmpty()) {
//                    editTextTextMultiLine.setError("This is a required field!");
//                    buttonSend.setEnabled(false);
//                }
//                else {
//                    editTextTextMultiLine.setError(null);
//                    buttonSend.setEnabled(true);
//                }
//                Toast.makeText(ContactUs.this, "Your Message was Sent!", Toast.LENGTH_SHORT).show();
//            }
        });

    }


}
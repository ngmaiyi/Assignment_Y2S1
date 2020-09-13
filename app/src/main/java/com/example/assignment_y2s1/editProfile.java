package com.example.assignment_y2s1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.FirebaseDatabase;


public class editProfile extends AppCompatActivity {

    FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private EditText etEmail, etName, etPass;

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        etEmail = (EditText) findViewById(R.id.txtEmail);
        etPass = (EditText) findViewById(R.id.txtPass);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    Intent intent = new Intent(editProfile.this, MainActivity.class);
                    intent.putExtra("goToActivityUsers", true);
                    startActivity(intent);
                    finish();
                } else {
                    etEmail.setText(user.getEmail());
                }
            }

        };

    }

    public void setUserEmail(View view) {
        String newEmail = etEmail.getText().toString();
        if(TextUtils.isEmpty(newEmail))
            return;

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        user.updateEmail(newEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful())
                    Toast.makeText(editProfile.this, "email updated", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setUserPassword(View view) {
        FirebaseUser user = mAuth.getCurrentUser();
        String newPass = etPass.getText().toString();
        if(TextUtils.isEmpty(newPass))
            return;

        user.updatePassword(newPass)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                            Toast.makeText(editProfile.this, "password updated", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
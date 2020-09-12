package com.example.assignment_y2s1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class register_page extends AppCompatActivity {
    private EditText textInputEmail;
    private EditText textInputUsername;
    private EditText textInputPassword;
    private EditText textInputConfirmPassword;
    private ProgressBar progressBar;
    Button mConfirm;
    FirebaseAuth fAuth;

    Button btn_confirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        textInputEmail           = findViewById(R.id.email_txt);
        textInputUsername        = findViewById(R.id.username_txt);
        textInputPassword        = findViewById(R.id.password_txt);
        textInputConfirmPassword = findViewById(R.id.comfirm_pass_txt);
        progressBar              = findViewById(R.id.progressBar_register);
        fAuth                    = FirebaseAuth.getInstance();
        mConfirm                 = findViewById(R.id.btn_confirm);
//        btn_confirm = (Button) findViewById(R.id.btn_confirm);
//        btn_confirm.setOnClickListener(new View.OnClickListener(){
//        @Override
//        public void onClick(View v){
//            openactivity_register_successfully_page();
//        }

//    }); ----------------------------------------------------------------
        mConfirm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String username       = textInputUsername.getText().toString().trim();
                final String email    = textInputEmail.getText().toString().trim();
                String password       = textInputPassword.getText().toString().trim();
                String confirmPasswordInput = textInputConfirmPassword.getText().toString();

                if(!username.isEmpty()|!email.isEmpty()) {

                }
                else
                {
                    textInputUsername.setError("Username is Required");
                    textInputEmail.setError("Email is Required");
                    textInputPassword.setError("Password is required.");
                    textInputConfirmPassword.setError("Confirm password is required.");
                    return;
                }

                if(password.isEmpty()){
                   textInputPassword.setError("Password is required.");
                    return;
                }

                if(confirmPasswordInput.isEmpty()){
                    textInputConfirmPassword.setError("Confirm Password is required.");
                    return;
                }

                if (password.length() < 8|confirmPasswordInput.length() < 8){
                    textInputPassword.setError("Password must be more than 8 characters.");
                    textInputConfirmPassword.setError("Confirm Password must be more than 8 characters.");
                    return;
                }

                if (password.equals(confirmPasswordInput)){
//                    Intent intent = new Intent(this,register_successfully_page.class);
//                    startActivity(intent);
                }
                else {
                    textInputPassword.setError("Does not Match");
                    textInputConfirmPassword.setError("Does not Match");
                }

                progressBar.setVisibility(View.VISIBLE);

                //-------------------------------------------------------------
                //register the user in firebase

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull final Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            Toast.makeText(register_page.this,"User Created.",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),register_successfully_page.class));
                        }
                        else
                        {
                            Toast.makeText(register_page.this,"Error!" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });

    }


//-------------------------------------------------------------------------------
//    public void confirmInput(View view) {
//        String emailInput = textInputEmail.getText().toString();
//        String usernameInput = textInputUsername.getText().toString();
//        String passwordInput = textInputPassword.getText().toString();
//        String confirmPasswordInput = textInputConfirmPassword.getText().toString();
//
//        if (emailInput.isEmpty() | usernameInput.isEmpty()) {
//            textInputUsername.setError("Field can't be empty");
//            textInputEmail.setError("Field can't be empty");
//        }
//        else {
//            textInputUsername.setError(null);
//            textInputEmail.setError(null);
//        }
//
//        if (passwordInput.isEmpty() | confirmPasswordInput.isEmpty()) {
//            textInputPassword.setError("Field can't be empty");
//            textInputConfirmPassword.setError("Field can't be empty");
//        }
//        else{
//            if (passwordInput.equals(confirmPasswordInput)){
//                Intent intent = new Intent(this, register_successfully_page.class);
//                startActivity(intent);
//            }
//            else {
//                textInputPassword.setError("Does not Match");
//                textInputConfirmPassword.setError("Does not Match");
//            }
//        }
//    }

    public void openactivity_main(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }




}

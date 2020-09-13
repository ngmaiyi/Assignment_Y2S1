package com.example.assignment_y2s1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
//facebook
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;


import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    //User information and forgot password
    EditText mEmail,mPassword,mUser;
    Button mLoginBtn,mCancelBtn;
    TextView mCreateBtn,forgotTextLink;
    ProgressBar progressBar;
    FirebaseAuth fAuth;
    private DatabaseReference ref;
    //---------------------------------------------

    // Google Button
    private SignInButton signInButton;
    private GoogleSignInClient mGoogleSignInClient;
    private String TAG = "MainActivity";
    private FirebaseAuth mAuth;
    //private Button btnSignOut;
    private int RC_SIGN_IN = 1;
    //private Button here_button;
    //---------------------------------------------

    //facebook button
    private CallbackManager mCallbackManager;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private TextView textViewUser;
    private ImageView mLogo;
    private LoginButton loginButton;
    private AccessTokenTracker accessTokenTracker;
    private static final String TAGFacebook = "FacebookAuthentication";
    //---------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //  User information and forgot password ------------------------
        mUser          = findViewById(R.id.username);
        // mEmail         = findViewById(R.id.Email);
        mPassword      = findViewById(R.id.password);
        progressBar   = findViewById(R.id.progressBar);
        fAuth          = FirebaseAuth.getInstance();
        mLoginBtn      = findViewById(R.id.btn_log_in);
        mCancelBtn     = findViewById(R.id.btn_cancel);
        // mCreateBtn     = findViewById(R.id.createText);
        forgotTextLink = findViewById(R.id.forgotPassword);


        //facebook button -----------------------------------------------
        mFirebaseAuth = FirebaseAuth.getInstance();
        FacebookSdk.sdkInitialize(getApplicationContext());
        textViewUser = findViewById(R.id.text_user);
        mLogo = findViewById(R.id.image_logo);
        loginButton = findViewById(R.id.btn_facebook);
        loginButton.setReadPermissions("email","public_profile");
        mCallbackManager = CallbackManager.Factory.create();

        //User information and forgot password --------------------------
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // String email    = mEmail.getText().toString().trim();
                String username = mUser.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if (TextUtils.isEmpty(username) && TextUtils.isEmpty(password) ){
                    mUser.setError("Username is required.");
                    mPassword.setError("Password is required.");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    mPassword.setError("Password is required.");
                    return;
                }

                if (password.length() < 8){
                    mPassword.setError("Password must be more than 8 characters.");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                //authenticate the user
                fAuth.signInWithEmailAndPassword(username,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {


                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Logged is Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), accountPage.class));

                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }

                    }
                });
            }
        });



        // Forgot Password function
        forgotTextLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText resetMail = new EditText(v.getContext());
                AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
                passwordResetDialog.setTitle("Reset Password?");
                passwordResetDialog.setMessage("Enter your email to received reset link.");
                passwordResetDialog.setView(resetMail);

                passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        //extract the email and send reset link

                        String mail = resetMail.getText().toString();
                        fAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(MainActivity.this,"Reset Link Sent To Your Email.",Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener(){
                            @Override
                            public void onFailure(@NonNull Exception e){
                                Toast.makeText(MainActivity.this,"Error! Reset Link is not sent" + e.getMessage(),Toast.LENGTH_SHORT).show();
                            }

                        });
                    }
                });

                passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //close the dialog
                    }
                });

                passwordResetDialog.create().show();
            }
        });

        //------------------------------------------------------------------------------------------------------
        // Google sign in
        signInButton = findViewById(R.id.btn_google);
        mAuth = FirebaseAuth.getInstance();
        //btnSignOut = findViewById(R.id.sign_out_button);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this,gso);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });

//        btnSignOut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mGoogleSignInClient.signOut();
//                btnSignOut.setVisibility(View.INVISIBLE);
//                Toast.makeText(MainActivity.this,"Your are Logged Out",Toast.LENGTH_SHORT).show();
//            }
//        });

        //--------------------------------------------------------------------------------------------------------
        //facebook button
        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAGFacebook,"onSuccess" + loginResult);
                handleFacebookToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d(TAGFacebook,"onCancel");

            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG,"onError" + error);
            }
        });

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user !=null){
                    updateUIFacebook(user);
                }
                else
                {
                    updateUIFacebook(null);
                }
            }
        };

        accessTokenTracker  = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                if (currentAccessToken == null){
                    mFirebaseAuth.signOut();

                }
            }
        };
        //-----------------------------------------------------------------------------------------------------------


    }

    //wai kit add
//    private void isUser() {
//        final String enteredEmail = mEmail.getText().toString().trim();
//        final String enteredPassword = mPassword.getText().toString().trim();
//
//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users");
//
//            ref.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//
//                if(dataSnapshot.exists()) {
//                    String passFromDB = dataSnapshot.child(enteredEmail).child("password").getValue(String.class);
//
//                    if(passFromDB.equals(enteredPassword)) {
//                        String usernameFromDB = dataSnapshot.child(enteredEmail).child("username").getValue(String.class);
//                        String phoneFromDB = dataSnapshot.child(enteredEmail).child("phone").getValue(String.class);
//                        String emailFromDB = dataSnapshot.child(enteredEmail).child("email").getValue(String.class);
//
//                        Intent intent = new Intent(getApplicationContext(),editProfile.class);
//
//                        intent.putExtra("username", usernameFromDB);
//                        intent.putExtra("phone", phoneFromDB);
//                        intent.putExtra("email", emailFromDB);
//
//                        startActivity(intent);
//                    }
////                    else {
////                        mPassword.setError("Wrong password");
////                    }
//                }
////                else {
////                    mEmail.setError("No such email exist");
////                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }
    //end OnCreate



    //user login email and password and facebook
    private  void signIn(){
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent,RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        //email and password login
        if(requestCode == RC_SIGN_IN){
            Task<GoogleSignInAccount>task=GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
        //facebook
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount acc = completedTask.getResult(ApiException.class);
            Toast.makeText(MainActivity.this,"Signed in successfully",Toast.LENGTH_SHORT).show();
            FirebaseGoogleAuth(acc);
        }
        catch (ApiException e){
            Toast.makeText(MainActivity.this,"Sign in failed",Toast.LENGTH_SHORT).show();
            FirebaseGoogleAuth(null);
        }
    }

    //sign in google
    private void FirebaseGoogleAuth(GoogleSignInAccount acct){
        if (acct != null) {
            AuthCredential authCredential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
            mAuth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(MainActivity.this, "Welcome to HTF!", Toast.LENGTH_SHORT).show();
                        FirebaseUser user = mAuth.getCurrentUser();
                        updateUI(user);
                        startActivity(new Intent(getApplicationContext(), accountPage.class));

                    }
                    else
                        {
                        Toast.makeText(MainActivity.this, "Please Login again", Toast.LENGTH_SHORT).show();
                        updateUI(null);
                    }
                }
            });
        }
        else{
            Toast.makeText(MainActivity.this, "Please Login again.", Toast.LENGTH_SHORT).show();
        }

    }

    //google
    private void updateUI(FirebaseUser fUser ) {
        //btnSignOut.setVisibility(View.VISIBLE);
        //google
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if (account != null) {
            String personName = account.getDisplayName();
//            String personGivenName = account.getGivenName();
//            String personFamilyName = account.getFamilyName();
//            String personEmail = account.getEmail();
//            String personID = account.getId();
//            Uri personPhoto = account.getPhotoUrl();

            Toast.makeText(MainActivity.this,"Welcome " + personName, Toast.LENGTH_SHORT).show();
        }
    }

    //facebook
    private void updateUIFacebook(FirebaseUser user){
        if(user != null){
            textViewUser.setText(user.getDisplayName());
            if(user.getPhotoUrl()!=null){
                String photoUr1 = user.getPhotoUrl().toString();
                photoUr1 = photoUr1 + "?type=large";
                Picasso.get().load(photoUr1).into(mLogo);
            }
            else
            {
                textViewUser.setText("");
                mLogo.setImageResource(R.drawable.background);
            }
        }

    }
    public void  openactivity_register_page(View view) {
        Intent intent = new Intent(this, register_page.class);
        startActivity(intent);
    }
    //-----------------------------------------------------------------------------------------------------
    //facebook button

    //facebook
    private void handleFacebookToken(AccessToken token){
        Log.d(TAGFacebook,"handleFacebookToken" + token);
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mFirebaseAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Log.d(TAGFacebook,"sign in with credential:successful");
                    FirebaseUser user = mFirebaseAuth.getCurrentUser();
                    updateUIFacebook(user);
                    startActivity(new Intent(getApplicationContext(), accountPage.class));
                }
                else
                {
                    Log.d(TAGFacebook,"sign in with credential:failure",task.getException());
                    Toast.makeText(MainActivity.this, "Authentication Failed",Toast.LENGTH_SHORT).show();
                    updateUIFacebook(null);

                }
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        mFirebaseAuth.addAuthStateListener(authStateListener);


    }

    @Override
    protected void onStop(){
        super.onStop();
        if (authStateListener !=null){
            mFirebaseAuth.removeAuthStateListener(authStateListener);
        }
    }
    //------------------------------------------------------------------------------------------------------
}
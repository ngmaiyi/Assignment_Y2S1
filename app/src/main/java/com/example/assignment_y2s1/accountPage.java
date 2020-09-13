package com.example.assignment_y2s1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.AccountPicker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

public class accountPage extends AppCompatActivity {

    //google
    private Button btnSignOut;
    private GoogleSignInClient mGoogleSignInClient;

    //facebook
    private CallbackManager mCallbackManager;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private TextView textViewUser;
    private ImageView mLogo;
    private LoginButton loginButton;
    private AccessTokenTracker accessTokenTracker;
    private static final String TAG = "FacebookAuthentication";
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_page);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.action_account);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_home:
                        startActivity(new Intent(getApplicationContext()
                                , MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;

//                    case R.id.action_product:
//                        startActivity(new Intent(getApplicationContext()
//                                , ProductHomePageActivity.class));
//                        overridePendingTransition(0,0);
//                        return true;

//                    case R.id.action_cart:
//                        startActivity(new Intent(getApplicationContext()
//                                , AccountActivity.class));
//                        overridePendingTransition(0,0);
//                        return true;

//                    case R.id.action_account:
//                        startActivity(new Intent(getApplicationContext()
//                                , AccountActivity.class));
//                        overridePendingTransition(0,0);
//                        return true;
                }
                return false;
            }
        });

        button = (Button) findViewById(R.id.btn_edit);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(accountPage.this, editProfile.class);
                startActivity(intent);
            }
        });

        button = (Button) findViewById(R.id.btn_cust_detail);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(accountPage.this, cust_main.class);
                startActivity(intent);
            }
        });


        //-------------------------------------------
        //google sign out
//        btnSignOut = findViewById(R.id.sign_out_button);
//
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken(getString(R.string.default_web_client_id))
//                .requestEmail()
//                .build();
//
//        mGoogleSignInClient = GoogleSignIn.getClient(this,gso);
//        btnSignOut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mGoogleSignInClient.signOut();
//                mFirebaseAuth.signOut();
//                btnSignOut.setVisibility(View.INVISIBLE);
//                Toast.makeText(accountPage.this,"Your are Logged Out",Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(getApplicationContext(), MainActivity.class));
//            }
//        });
        //---------------------------------------------
        //Facebook Sign Out
//         accessTokenTracker  = new AccessTokenTracker() {
//            @Override
//            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
//                if (currentAccessToken == null){
//                    mFirebaseAuth.signOut();
//                }
//            }
//        };

        //----------------------------------------------
        //facebook
//        mFirebaseAuth = FirebaseAuth.getInstance();
//        FacebookSdk.sdkInitialize(getApplicationContext());
//
//        textViewUser = findViewById(R.id.textView3);
//        mLogo = findViewById(R.id.image_logo);
//
//        loginButton = findViewById(R.id.login_button);
//        loginButton.setReadPermissions("email","public_profile");
//        mCallbackManager = CallbackManager.Factory.create();
//        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>()
//        {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//                Log.d(TAG,"onSuccess" + loginResult);
//                handleFacebookToken(loginResult.getAccessToken());
//            }
//
//            @Override
//            public void onCancel() {
//                Log.d(TAG,"onCancel");
//            }
//
//            @Override
//            public void onError(FacebookException error) {
//                Log.d(TAG,"onError" + error);
//            }
//        });
//
//        authStateListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                FirebaseUser user = firebaseAuth.getCurrentUser();
//                if (user !=null){
//                    updateUIFacebook(user);
//                }
//                else
//                {
//                    updateUIFacebook(null);
//                }
//            }
//        };
//
//        accessTokenTracker  = new AccessTokenTracker() {
//            @Override
//            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
//                if (currentAccessToken == null){
//                    mFirebaseAuth.signOut();
//                }
//            }
//        };
//    }
//    //End onCreate
//
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
//        super.onActivityResult(requestCode,resultCode,data);
//
//        //facebook
//        mCallbackManager.onActivityResult(requestCode, resultCode, data);
//        super.onActivityResult(requestCode, resultCode, data);
//
//    }
//
//    private void handleFacebookToken(AccessToken token){
//        Log.d(TAG,"handleFacebookToken" + token);
//
//        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
//        mFirebaseAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if(task.isSuccessful()){
//                    Log.d(TAG,"sign in with credential:successful");
//                    FirebaseUser user = mFirebaseAuth.getCurrentUser();
//                    updateUIFacebook(user);
//                }else
//                {
//                    Log.d(TAG,"sign in with credential:failure",task.getException());
//                    Toast.makeText(accountPage.this, "Authentication Failed",Toast.LENGTH_SHORT).show();
//                    updateUIFacebook(null);
//                }
//            }
//        });
//    }
//
//
//    private void updateUIFacebook(FirebaseUser user){
//        if(user != null){
//            textViewUser.setText(user.getDisplayName());
//            if(user.getPhotoUrl()!=null){
//                String photoUr1 = user.getPhotoUrl().toString();
//                photoUr1 = photoUr1 + "?type=large";
//                Picasso.get().load(photoUr1).into(mLogo);
//            }
//            else
//            {
//                textViewUser.setText("");
//                mLogo.setImageResource(R.drawable.background);
//            }
//        }
//
//    }


//    @Override
//    protected void onStart(){
//        super.onStart();
//        mFirebaseAuth.addAuthStateListener(authStateListener);
//    }
//
//    @Override
//    protected void onStop(){
//        super.onStop();
//        if (authStateListener !=null){
//            mFirebaseAuth.removeAuthStateListener(authStateListener);
//        }
//    }
        //-----------------------------------------------------
    }

}
package com.example.progga.anything;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class login extends AppCompatActivity implements View.OnClickListener,GoogleApiClient.OnConnectionFailedListener {
    GoogleSignInClient mGoogleSignInClient;
    public static String userno;
    String TAG="hhhhh";
    public static String KEY_PASSWORD="password";
    public static String KEY_EMAIL="email";
    public static String URL=database.login;
    String email,password;
    TextView textView;
    EditText email1,password1;
    private Button signout,Login;
    private SignInButton signInButton;
    private TextView email2,name2;
    GoogleSignInAccount account;
    private ImageView prof_pic;
    private GoogleApiClient googleApiClient;
    public static int RC_SIGN_IN=9001;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
         email1=findViewById(R.id.editText1);
         password1=findViewById(R.id.editText2);
         Login=findViewById(R.id.login);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email=(email1.getText().toString());
                password=(password1.getText().toString());
                volley();
                Toast.makeText(getApplicationContext(),email,Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(),password,Toast.LENGTH_LONG).show();


            }
        });







        //////////*************Google sign in*********/////////////////////
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        account = GoogleSignIn.getLastSignedInAccount(login.this);

        // Set the dimensions of the sign-in button.
        signInButton = findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setOnClickListener(this);
        //  prof_section=(LinearLayout)findViewById(R.id.prof_section);
       // signout=(Button)findViewById(R.id.button1);
        //  signInButton=(SignInButton)findViewById(R.id.button2);
        email2=(TextView)findViewById(R.id.email);
        name2=(TextView)findViewById(R.id.username);
        textView=(TextView)findViewById(R.id.textview);
        prof_pic=(ImageView)findViewById(R.id.prof_pic);
        signInButton.setOnClickListener(this);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(login.this,register.class);
                startActivity(intent);
            }
        });
     //   signout.setOnClickListener(this);
    }
////////////////////////////////////////////////////////



 private void  volley()
{


        final ProgressDialog progressDialog=new ProgressDialog(this);

        progressDialog.setMessage("Loading Data.........");
        progressDialog.show();
        //Toast.makeText(getApplicationContext(),"2",Toast.LENGTH_LONG).show();

        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                progressDialog.dismiss();
                try {
    if(s.equals("error"))
    Toast.makeText(getApplicationContext(),"login failed",Toast.LENGTH_LONG).show();
    else {
    Toast.makeText(getApplicationContext(), "You are sucessfully logged in", Toast.LENGTH_LONG).show();

    JSONObject jsonObject= new JSONObject(s);
    // Toast.makeText(getApplicationContext(),"3",Toast.LENGTH_LONG).show();

    JSONArray array=jsonObject.getJSONArray("userinfo");
    //Toast.makeText(getApplicationContext(),"3.1",Toast.LENGTH_LONG).show();

    for(int i=0;i<array.length();i++)
    {
        JSONObject o=array.getJSONObject(i);
        userno=(o.getString("Id"));
        Toast.makeText(getApplicationContext(),userno.toString(),Toast.LENGTH_LONG).show();

    }
    Intent intent=new Intent(login.this,Profile.class);
    startActivity(intent);
}
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
            }
        }
        ){

            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put(KEY_EMAIL,email);
                params.put(KEY_PASSWORD,password);

                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }












    //////////*************Google sign in*********/////////////////////
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                Toast.makeText(login.this, "This is my Toast message!0", Toast.LENGTH_LONG).show();

                signIn();
                break;
           /* case R.id.button1:
                signOut();
                break;*/
        }
    }
    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        updateUI();
                    }
                });
    }

    private void updateUI()
    {

        //prof_section.setVisibility(View.VISIBLE);
        Button button=findViewById(R.id.button1);
        button.setVisibility(View.GONE);


    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {            Toast.makeText(login.this, "This is my Toast message!1", Toast.LENGTH_LONG).show();
            account = completedTask.getResult(ApiException.class);

            // GoogleSignInAccount account=result.getSignInAccount();
            String name1=account.getDisplayName();
            String email1=account.getEmail();

            name2.setText(name1);
            email2.setText(email1);
            if(account.getPhotoUrl()!=null){
                String img_url=account.getPhotoUrl().toString();
                Log.e(TAG, "signInResult:failed code=" + img_url);
                Glide.with(login.this).load(img_url).placeholder(R.drawable.download).dontAnimate().into(prof_pic);}
           /* Glide.with(MainActivity.this).load(img_url)
                    .into(prof_pic);*/
            // Signed in successfully, show authenticated UI.
            Toast.makeText(login.this, "This is my Toast message!2", Toast.LENGTH_LONG).show();

        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());

        }
    }
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


}


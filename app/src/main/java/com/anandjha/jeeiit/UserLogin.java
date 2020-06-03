package com.anandjha.jeeiit;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UserLogin extends AppCompatActivity {
    private Button login, forgetPass, signup;
    private TextInputEditText username1, password1;
    private String server_url = "https://anandjhaweb.000webhostapp.com/android/login.php";
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("Test1 : ", "This is UserLogin Activity");
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_login);
        if (!isNetworkConnected()) {
            Toast.makeText(this, "NO Network", Toast.LENGTH_SHORT).show();
            new AlertDialog.Builder(this)
                    .setTitle("No Internet Connection")
                    .setMessage("Please connect to internet and try again..")
                    .setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            recreate();
                        }
                    })
                    .setCancelable(false)
                    .show();
        }
        username1 = findViewById(R.id.username);
        password1 = findViewById(R.id.password);
        login = findViewById(R.id.btLogin);
        forgetPass = findViewById(R.id.btForgetPass);
        signup = findViewById(R.id.btSignup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserLogin.this,Dashboard.class));
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = username1.getText().toString();
                final String password = password1.getText().toString();

                if (username.equals("")) {
                    username1.setError("enter username");
                    username1.setActivated(true);
                } else if (password.equals("")) {
                    password1.setError("enter password");
                    username1.setActivated(true);
                } else {
                    progressDialog = ProgressDialog.show(UserLogin.this, "", "Please Wait...", true);
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.dismiss();
                            Toast.makeText(UserLogin.this, response.toString(), Toast.LENGTH_SHORT).show();

                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String code = jsonObject.getString("code");
                                if (code.equals("1")) {
                                    Toast.makeText(UserLogin.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(UserLogin.this, Dashboard.class));
                                    finish();
                                } else {
                                    Toast.makeText(UserLogin.this, "Username and password is wrong", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                Toast.makeText(UserLogin.this, "Something Went wrong try again...", Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressDialog.dismiss();
                            Toast.makeText(UserLogin.this, "Request Failed"+error.toString(), Toast.LENGTH_SHORT).show();
                            error.printStackTrace();

                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("email", username);
                            params.put("password", password);
                            return params;
                        }
                    };

                    requestQueue.add(stringRequest);
                }
            }

        });


    }

    //Network status check class
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

}

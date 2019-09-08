package com.aprosoftech.bankhelper;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {


    EditText et_username, et_password;
    Button btn_submit,btn_signup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);
        btn_submit = (Button) findViewById(R.id.btn_submit);
        btn_signup = (Button) findViewById(R.id.btn_signup);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performLogin();
            }
        });
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performSignUp();
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        JSONObject userObject = BankHelperSingleton.getInstance().getUser(MainActivity.this);
        if (userObject.length() > 0) {
            Intent intent = new Intent(MainActivity.this,UserDashboard.class);
            startActivity(intent);
        }
    }

    public void performLogin() {
        String username = et_username.getText().toString();
        String password = et_password.getText().toString();


        if (username.trim().equalsIgnoreCase("") || password.trim().equalsIgnoreCase("")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Error");
            builder.setMessage("Please fill all fields and try again!");
            builder.setPositiveButton("Ok",null);
            builder.show();
            return;
        }


        String url = BankHelperSingleton.BASE_URL + BankHelperSingleton.LOGIN_URL;


        final ProgressDialog progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("Email",""+username);
            jsonObject.put("Password",""+password);
            jsonObject.put("Phone",""+username);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonRequest<JSONArray> jsonRequest = new JsonRequest<JSONArray>(Request.Method.POST, url, jsonObject.toString(), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("Res",response.toString());
                progress.dismiss();
                if (response.length() > 0) {
                    try {
                        BankHelperSingleton.getInstance().saveUser(MainActivity.this,response.getJSONObject(0));
                        Intent intent = new Intent(MainActivity.this,UserDashboard.class);
                        startActivity(intent);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                progress.dismiss();
            }
        }) {
            @Override
            protected Response<JSONArray> parseNetworkResponse(NetworkResponse response) {
                try {
                    String jsonString = new String(response.data,
                            HttpHeaderParser.parseCharset(response.headers, PROTOCOL_CHARSET));
                    return Response.success(new JSONArray(jsonString),
                            HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                } catch (JSONException je) {
                    return Response.error(new ParseError(je));
                }
//                return super.deliverResponse();
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(jsonRequest);

    }

    public void performSignUp() {
        Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
        startActivity(intent);
    }

}

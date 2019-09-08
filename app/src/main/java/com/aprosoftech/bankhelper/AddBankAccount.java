package com.aprosoftech.bankhelper;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
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


public class AddBankAccount extends AppCompatActivity {

    EditText et_bank_name,et_bank_account_holder,et_bank_account_no,
            et_confirm_bank_account_no,et_bank_branch,et_bank_ifsc;

    Button btn_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bank_account);


        et_bank_name = (EditText) findViewById(R.id.et_bank_name);
        et_bank_account_holder = (EditText) findViewById(R.id.et_bank_account_holder);
        et_bank_account_no = (EditText) findViewById(R.id.et_bank_account_no);
        et_confirm_bank_account_no = (EditText) findViewById(R.id.et_confirm_bank_account_no);
        et_bank_branch = (EditText) findViewById(R.id.et_bank_branch);
        et_bank_ifsc = (EditText) findViewById(R.id.et_bank_ifsc);

        btn_submit = (Button) findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkInput();
            }
        });
    }

    public void checkInput() {
        String bankName = et_bank_name.getText().toString();
        String accountNumber = et_bank_account_no.getText().toString();
        String branch = et_bank_branch.getText().toString();
        String ifsc = et_bank_ifsc.getText().toString();
        String accountHolder = et_bank_account_holder.getText().toString();
        String accountNumberConfirmation = et_confirm_bank_account_no.getText().toString();


        if (!checkEmpty(bankName)) {
            showSimpleAlert("Details", "Please Fill Bank Name and Try Again");
            return;
        }
        if (!checkEmpty(accountNumber)) {
            showSimpleAlert("Details", "Please Fill Bank Account Number and Try Again");
            return;
        }
        if (!checkEmpty(branch)) {
            showSimpleAlert("Details", "Please Fill Bank Branch and Try Again");
            return;
        }
        if (!checkEmpty(ifsc)) {
            showSimpleAlert("Details", "Please Fill Bank IFSC and Try Again");
            return;
        }
        if (!checkEmpty(accountHolder)) {
            showSimpleAlert("Details", "Please Fill Bank Account Holder Name and Try Again");
            return;
        }
        if (!checkEmpty(accountNumberConfirmation)) {
            showSimpleAlert("Details", "Please Fill Bank Account Number Confirmation and Try Again");
            return;
        }


        if (!accountNumber.equalsIgnoreCase(accountNumberConfirmation)) {
            showSimpleAlert("Details", "Account Number and Confirm Account Numbers donot match");
            return;
        }

        try {
            addBankAccount();
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public void showSimpleAlert(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(AddBankAccount.this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("OK",null);
        builder.show();
    }


    public boolean checkEmpty(String str ){
        if (str.trim().equalsIgnoreCase(""))
            return false;
        else
            return true;
    }


    public void addBankAccount() throws JSONException {
        String url = BankHelperSingleton.BASE_URL + BankHelperSingleton.ADD_BANK_ACCOUNT_URL;

        JSONObject userObject = BankHelperSingleton.getInstance().getUser(AddBankAccount.this);

        String userId = userObject.getString("uuid");



        String bankName = et_bank_name.getText().toString();
        String accountNumber = et_bank_account_no.getText().toString();
        String branch = et_bank_branch.getText().toString();
        String ifsc = et_bank_ifsc.getText().toString();
        String accountHolder = et_bank_account_holder.getText().toString();


        final ProgressDialog progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("UserId",""+userId);
            jsonObject.put("BankName",""+bankName);
            jsonObject.put("AccountNumber",""+accountNumber);
            jsonObject.put("Branch",""+branch);
            jsonObject.put("IFSC",""+ifsc);
            jsonObject.put("HolderName",""+accountHolder);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonRequest<JSONArray> jsonRequest = new JsonRequest<JSONArray>(Request.Method.POST, url, jsonObject.toString(), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("Res",response.toString());
                progress.dismiss();
                if (response.length() > 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(AddBankAccount.this);
                    builder.setTitle("Success");
                    builder.setMessage("Bank Account Added Successfully");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            AddBankAccount.this.finish();
                        }
                    });
                    builder.show();
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

        RequestQueue requestQueue = Volley.newRequestQueue(AddBankAccount.this);
        requestQueue.add(jsonRequest);
    }
}

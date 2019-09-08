package com.aprosoftech.bankhelper;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class DepositReceipt extends AppCompatActivity {

    SearchableSpinner ss_bank_accounts;
    JSONArray bankAccountsArray;
    EditText et_account_holder_name,et_account_holder_phone;

    EditText et_2000,et_500,et_200,et_100,et_50,et_20,et_10,et_5,et_2,et_1;
    TextView tv_2000,tv_500,tv_200,tv_100,tv_50,tv_20,tv_10,tv_5,tv_2,tv_1,tv_total;

    JSONObject moneyJSONObject;

    String bankId = null;
    Button btn_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit_receipt);

        ss_bank_accounts = (SearchableSpinner) findViewById(R.id.ss_bank_accounts);
        et_account_holder_name = (EditText) findViewById(R.id.et_account_holder_name);
        et_account_holder_phone = (EditText) findViewById(R.id.et_account_holder_phone);


        et_2000 = (EditText) findViewById(R.id.et_2000);
        et_500 = (EditText) findViewById(R.id.et_500);
        et_200 = (EditText) findViewById(R.id.et_200);
        et_100 = (EditText) findViewById(R.id.et_100);
        et_50 = (EditText) findViewById(R.id.et_50);
        et_20 = (EditText) findViewById(R.id.et_20);
        et_10 = (EditText) findViewById(R.id.et_10);
        et_5 = (EditText) findViewById(R.id.et_5);
        et_2 = (EditText) findViewById(R.id.et_2);
        et_1 = (EditText) findViewById(R.id.et_1);


        tv_2000 = (TextView) findViewById(R.id.tv_2000);
        tv_500 = (TextView) findViewById(R.id.tv_500);
        tv_200 = (TextView) findViewById(R.id.tv_200);
        tv_100 = (TextView) findViewById(R.id.tv_100);
        tv_50 = (TextView) findViewById(R.id.tv_50);
        tv_20 = (TextView) findViewById(R.id.tv_20);
        tv_10 = (TextView) findViewById(R.id.tv_10);
        tv_5 = (TextView) findViewById(R.id.tv_5);
        tv_2 = (TextView) findViewById(R.id.tv_2);
        tv_1 = (TextView) findViewById(R.id.tv_1);
        tv_total = (TextView) findViewById(R.id.tv_total);

        btn_submit = (Button) findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    saveReceipt();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


        et_2000.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d("Text Changed",s.toString());
                if (s.toString().equalsIgnoreCase("")) {
                    tv_2000.setText("=");
                    getTotalAndShow();
return;
                }
                int number = Integer.parseInt(et_2000.getText().toString());
                tv_2000.setText("= "+2000*number+"");
                getTotalAndShow();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et_500.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d("Text Changed",s.toString());
                if (s.toString().equalsIgnoreCase("")) {
                    tv_500.setText("=");
                    getTotalAndShow();
return;
                }
                int number = Integer.parseInt(et_500.getText().toString());
                tv_500.setText("= "+500*number+"");
                getTotalAndShow();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et_200.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d("Text Changed",s.toString());
                if (s.toString().equalsIgnoreCase("")) {
                    tv_200.setText("=");
                    getTotalAndShow();
return;
                }
                int number = Integer.parseInt(et_200.getText().toString());
                tv_200.setText("= "+200*number+"");
                getTotalAndShow();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et_100.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d("Text Changed",s.toString());
                if (s.toString().equalsIgnoreCase("")) {
                    tv_100.setText("=");
                    getTotalAndShow();
return;
                }
                int number = Integer.parseInt(et_100.getText().toString());
                tv_100.setText("= "+100*number+"");
                getTotalAndShow();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et_50.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d("Text Changed",s.toString());
                if (s.toString().equalsIgnoreCase("")) {
                    tv_50.setText("=");
                    getTotalAndShow();
return;
                }
                int number = Integer.parseInt(et_50.getText().toString());
                tv_50.setText("= "+50*number+"");
                getTotalAndShow();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et_20.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d("Text Changed",s.toString());
                if (s.toString().equalsIgnoreCase("")) {
                    tv_20.setText("=");
                    getTotalAndShow();
return;
                }
                int number = Integer.parseInt(et_20.getText().toString());
                tv_20.setText("= "+20*number+"");
                getTotalAndShow();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et_10.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d("Text Changed",s.toString());
                if (s.toString().equalsIgnoreCase("")) {
                    tv_10.setText("=");
                    getTotalAndShow();
return;
                }
                int number = Integer.parseInt(et_10.getText().toString());
                tv_10.setText("= "+10*number+"");
                getTotalAndShow();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et_5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d("Text Changed",s.toString());
                if (s.toString().equalsIgnoreCase("")) {
                    tv_5.setText("=");
                    getTotalAndShow();
return;
                }
                int number = Integer.parseInt(et_5.getText().toString());
                tv_5.setText("= "+5*number+"");
                getTotalAndShow();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et_2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d("Text Changed",s.toString());
                if (s.toString().equalsIgnoreCase("")) {
                    tv_2.setText("=");
                    getTotalAndShow();
return;
                }
                int number = Integer.parseInt(et_2.getText().toString());
                tv_2.setText("= "+2*number+"");
                getTotalAndShow();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et_1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d("Text Changed",s.toString());
                if (s.toString().equalsIgnoreCase("")) {
                    tv_1.setText("=");
                    getTotalAndShow();
return;
                }
                int number = Integer.parseInt(et_1.getText().toString());
                tv_1.setText("= "+1*number+"");
                getTotalAndShow();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });




    }


    @Override
    protected void onResume() {
        super.onResume();
        bankAccountsArray =  BankHelperSingleton.getInstance().getUserAccounts(DepositReceipt.this);
        if (bankAccountsArray.length() == 0) {
            Intent intent = new Intent(DepositReceipt.this,BankAccounts.class);
            startActivity(intent);
        } else {
            ss_bank_accounts.setTitle("Select Item");
            ss_bank_accounts.setPositiveButton("OK");

            ArrayList arrayList = new ArrayList();
            for (int i=0;i<bankAccountsArray.length();i++) {
                try {
                    JSONObject jsonObject = bankAccountsArray.getJSONObject(i);
                    String bank= jsonObject.getString("BankName") +"\n"+jsonObject.getString("AccountNumber");
                    arrayList.add(bank);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            ArrayAdapter arrayAdapter = new ArrayAdapter(DepositReceipt.this,android.R.layout.simple_spinner_item,arrayList);
            ss_bank_accounts.setAdapter(arrayAdapter);
            ss_bank_accounts.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    try {
                        JSONObject jsonObject = bankAccountsArray.getJSONObject(position);
                        Log.d("Selected Bank",jsonObject.toString());
                        bankId = jsonObject.getString("Bankid");
                        et_account_holder_name.setText(jsonObject.getString("HolderName"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
    }

    public void getTotalAndShow() {
        int number_2000 = Integer.parseInt(et_2000.getText().toString().equalsIgnoreCase("")?"0":et_2000.getText().toString()) * 2000;
        int number_500 = Integer.parseInt(et_500.getText().toString().equalsIgnoreCase("")?"0":et_500.getText().toString()) * 500;
        int number_200 = Integer.parseInt(et_200.getText().toString().equalsIgnoreCase("")?"0":et_200.getText().toString()) * 200;
        int number_100 = Integer.parseInt(et_100.getText().toString().equalsIgnoreCase("")?"0":et_100.getText().toString()) * 100;
        int number_50 = Integer.parseInt(et_50.getText().toString().equalsIgnoreCase("")?"0":et_50.getText().toString()) * 50;
        int number_20 = Integer.parseInt(et_20.getText().toString().equalsIgnoreCase("")?"0":et_20.getText().toString()) * 20;
        int number_10 = Integer.parseInt(et_10.getText().toString().equalsIgnoreCase("")?"0":et_10.getText().toString()) * 10;
        int number_5 = Integer.parseInt(et_5.getText().toString().equalsIgnoreCase("")?"0":et_5.getText().toString()) * 5;
        int number_2 = Integer.parseInt(et_2.getText().toString().equalsIgnoreCase("")?"0":et_2.getText().toString()) * 2;
        int number_1 = Integer.parseInt(et_1.getText().toString().equalsIgnoreCase("")?"0":et_1.getText().toString()) * 1;

        int sum = number_2000 + number_500 + number_200 + number_100 + number_50 + number_20 + number_10 + number_5 + number_2 + number_1 ;

        tv_total.setText(""+sum);

        moneyJSONObject = new JSONObject();
        try {
            moneyJSONObject.put("2000",et_2000.getText().toString().equalsIgnoreCase("")?"0":et_2000.getText().toString());
            moneyJSONObject.put("500",et_500.getText().toString().equalsIgnoreCase("")?"0":et_500.getText().toString());
            moneyJSONObject.put("200",et_200.getText().toString().equalsIgnoreCase("")?"0":et_200.getText().toString());
            moneyJSONObject.put("100",et_100.getText().toString().equalsIgnoreCase("")?"0":et_100.getText().toString());
            moneyJSONObject.put("50",et_50.getText().toString().equalsIgnoreCase("")?"0":et_50.getText().toString());
            moneyJSONObject.put("20",et_20.getText().toString().equalsIgnoreCase("")?"0":et_20.getText().toString());
            moneyJSONObject.put("10",et_10.getText().toString().equalsIgnoreCase("")?"0":et_10.getText().toString());
            moneyJSONObject.put("5",et_5.getText().toString().equalsIgnoreCase("")?"0":et_5.getText().toString());
            moneyJSONObject.put("2",et_2.getText().toString().equalsIgnoreCase("")?"0":et_2.getText().toString());
            moneyJSONObject.put("1",et_1.getText().toString().equalsIgnoreCase("")?"0":et_1.getText().toString());
            moneyJSONObject.put("total",""+sum);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    public void saveReceipt() throws JSONException {
        String url = BankHelperSingleton.BASE_URL + BankHelperSingleton.ADD_DEPOSIT_SLIP_URL;

        if (bankId == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(DepositReceipt.this);
            builder.setTitle("Bank Account Required");
            builder.setMessage("Bank Account has not been selected. Please select the account and try again!");
            builder.setPositiveButton("OK", null);
            builder.show();
            return;
        }

        final ProgressDialog progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();


        JSONObject userObject = BankHelperSingleton.getInstance().getUser(DepositReceipt.this);

        String userId = userObject.getString("uuid");

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("UserId",""+userId);
            jsonObject.put("BankId",""+bankId);
            jsonObject.put("MoneyDetail",""+moneyJSONObject);
            jsonObject.put("ReceiverUserId","");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonRequest<JSONArray> jsonRequest = new JsonRequest<JSONArray>(Request.Method.POST, url, jsonObject.toString(), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(final JSONArray response) {
                Log.d("Res",response.toString());
                progress.dismiss();
                if (response.length() > 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(DepositReceipt.this);
                    builder.setTitle("Success");
                    builder.setMessage("Receipt Added Successfully");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            try {
                                Intent intent = new Intent(DepositReceipt.this,ReceiptBarCode.class);
                                intent.putExtra("SLIP",response.getJSONObject(0).toString());
                                startActivity(intent);
                                DepositReceipt.this.finish();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

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

        RequestQueue requestQueue = Volley.newRequestQueue(DepositReceipt.this);
        requestQueue.add(jsonRequest);
    }
}

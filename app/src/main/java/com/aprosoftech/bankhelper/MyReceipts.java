package com.aprosoftech.bankhelper;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.aprosoftech.bankhelper.Adapters.BankAccountsAdapter;
import com.aprosoftech.bankhelper.Adapters.DepositSlipsAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class MyReceipts extends AppCompatActivity {


    ListView lv_my_receipts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_receipts);
        lv_my_receipts = (ListView) findViewById(R.id.lv_my_receipts);
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            getMyReceipts();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_accounts,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.item_add) {
            Intent intent= new Intent(MyReceipts.this,DepositReceipt.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void getMyReceipts() throws JSONException {
        JSONObject userObject = BankHelperSingleton.getInstance().getUser(MyReceipts.this);

        String userId = userObject.getString("uuid");

        String url = BankHelperSingleton.BASE_URL + BankHelperSingleton.DEPOSIT_RECEIPTS_URL;

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("UserId",userId);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final ProgressDialog progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();

        JsonRequest<JSONArray> jsonRequest = new JsonRequest<JSONArray>(Request.Method.POST, url, jsonObject.toString(), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(final JSONArray response) {
                Log.d("Res",response.toString());
                progress.dismiss();
                if (response.length() > 0) {
                    DepositSlipsAdapter bankAccountsAdapter = new DepositSlipsAdapter(MyReceipts.this,response);
                    lv_my_receipts.setAdapter(bankAccountsAdapter);
                    lv_my_receipts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            try {
                                Intent intent = new Intent(MyReceipts.this,ReceiptBarCode.class);
                                intent.putExtra("SLIP",response.getJSONObject(0).toString());
                                startActivity(intent);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
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

        RequestQueue requestQueue = Volley.newRequestQueue(MyReceipts.this);
        requestQueue.add(jsonRequest);
    }

}

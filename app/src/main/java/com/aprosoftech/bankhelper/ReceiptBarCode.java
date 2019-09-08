package com.aprosoftech.bankhelper;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

public class ReceiptBarCode extends AppCompatActivity {

    ImageView iv_bar_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt_bar_code);

        iv_bar_code = (ImageView) findViewById(R.id.iv_bar_code);


        String slipString = getIntent().getExtras().getString("SLIP");
        try {
            JSONObject jsonObject = new JSONObject(slipString);
            String slipId = jsonObject.getString("SlipId");
            Glide.with(this).load("https://api.qrserver.com/v1/create-qr-code/?size=150x150&data="+slipId).into(iv_bar_code);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

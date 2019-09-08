package com.aprosoftech.bankhelper.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aprosoftech.bankhelper.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BankAccountsAdapter extends BaseAdapter {


    Context context;
    JSONArray bankAccountsArray;
    LayoutInflater layoutInflater;

    public BankAccountsAdapter(Context context, JSONArray bankAccountsArray) {
        this.context = context;
        this.bankAccountsArray = bankAccountsArray;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return this.bankAccountsArray.length();
    }

    @Override
    public Object getItem(int position) {
        try {
            return this.bankAccountsArray.getJSONObject(position);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null)
            view = layoutInflater.inflate(R.layout.custom_bank_accounts,null);


        TextView tv_bank_name = (TextView) view.findViewById(R.id.tv_bank_name);
        TextView tv_bank_account_no = (TextView) view.findViewById(R.id.tv_bank_account_no);
        TextView tv_bank_location = (TextView) view.findViewById(R.id.tv_bank_location);

        try {
            JSONObject jsonObject = this.bankAccountsArray.getJSONObject(position);
            tv_bank_name.setText(jsonObject.getString("BankName"));
            tv_bank_account_no.setText(jsonObject.getString("AccountNumber"));
            tv_bank_location.setText(jsonObject.getString("Branch"));
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return view;
    }
}

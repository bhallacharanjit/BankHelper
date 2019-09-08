package com.aprosoftech.bankhelper.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aprosoftech.bankhelper.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DepositSlipsAdapter extends BaseAdapter {

    Context context;
    JSONArray slipsArray;
    LayoutInflater layoutInflater;

    public DepositSlipsAdapter(Context context, JSONArray jsonArray) {
        this.context = context;
        this.slipsArray = jsonArray;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return this.slipsArray.length();
    }

    @Override
    public Object getItem(int position) {
        try {
            return this.slipsArray.getJSONObject(position);
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
        View depositView = convertView;
        if (depositView == null)
            depositView = this.layoutInflater.inflate(R.layout.custom_deposit_slips,null);

        TextView tv_date =  (TextView) depositView.findViewById(R.id.tv_date);
        TextView tv_bank_name =  (TextView) depositView.findViewById(R.id.tv_bank_name);
        TextView tv_bank_account_no =  (TextView) depositView.findViewById(R.id.tv_bank_account_no);
        TextView tv_total_amount =  (TextView) depositView.findViewById(R.id.tv_total_amount);
        LinearLayout view_status = (LinearLayout) depositView.findViewById(R.id.view_status);
        RelativeLayout view_deposit_status = (RelativeLayout) depositView.findViewById(R.id.view_deposit_status);

        try {
            JSONObject jsonObject = this.slipsArray.getJSONObject(position);
            tv_date.setText(jsonObject.getString("Date").split(" ")[0]);
            tv_bank_name.setText(jsonObject.getString("BankName"));
            tv_bank_account_no.setText(jsonObject.getString("AccountNumber"));
            String moneyDetails = jsonObject.getString("MoneyDetail");
            if (moneyDetails.equalsIgnoreCase("")){

            } else {
                JSONObject jsonObject1 = new JSONObject(moneyDetails);
                if (jsonObject1.has("total")) {
                    tv_total_amount.setText("Total Amount Depositted: ₹ "+jsonObject1.getString("total"));
                }
            }
            boolean isApproved  = jsonObject.getBoolean("isApproved");
            if (isApproved)
                view_deposit_status.setBackgroundColor(Color.GREEN);
            else
                view_deposit_status.setBackgroundColor(Color.RED);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return depositView;
    }
}

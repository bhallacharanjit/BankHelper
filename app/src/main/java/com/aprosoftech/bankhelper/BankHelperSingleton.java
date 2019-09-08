package com.aprosoftech.bankhelper;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class BankHelperSingleton {

    public static String BASE_URL               =       "http://bankhelper.aprosoftech.com//api/BankHolder/";
    public static String LOGIN_URL              =       "LoginUser";
    public static String SIGNUP_URL             =       "RegisterUser";

    public static String BANK_ACCOUNT_URL       =       "ShowBankAccount";
    public static String ADD_BANK_ACCOUNT_URL   =       "AddBankAccount";


    public static String DEPOSIT_RECEIPTS_URL   =       "ShowDepositSlip";
    public static String ADD_DEPOSIT_SLIP_URL   =       "AddDepositSlip";





    public static String PREF_NAME              =       "BANKHOLDER_PREFS";
    public static String USER_PREF              =       "USER";
    public static String USER_ACCOUNTS_PREF     =       "USER_ACCOUNTS";





    private static final BankHelperSingleton ourInstance = new BankHelperSingleton();

    static BankHelperSingleton getInstance() {
        return ourInstance;
    }

    private BankHelperSingleton() {
    }

    // USER

    public void saveUser(Context context, JSONObject jsonObject) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(USER_PREF,jsonObject.toString()).apply();
    }

    public JSONObject getUser(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        String userString = sharedPreferences.getString(USER_PREF,"");
        if (userString.equalsIgnoreCase("")) {
            return new JSONObject();
        } else {
            try {
                return new JSONObject(userString);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return new JSONObject();
        }
    }

    public void removeUser(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        sharedPreferences.edit().remove(USER_PREF).apply();
    }

    // END USER


    //USER ACCOUNTS
    public void saveUserAccounts(Context context,JSONArray userAccountsArray) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(USER_ACCOUNTS_PREF,userAccountsArray.toString()).apply();
    }

    public JSONArray getUserAccounts(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        String userAccountsString = sharedPreferences.getString(USER_ACCOUNTS_PREF,"");
        if (userAccountsString.equalsIgnoreCase("")) {
            return new JSONArray();
        } else {
            try {
                return new JSONArray(userAccountsString);
            } catch (Exception ae) {
                ae.printStackTrace();
            }
        }
        return new JSONArray();
    }

    //END USER ACCOUNTS


}

package com.aprosoftech.bankhelper;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class UserDashboard extends AppCompatActivity {


    Button btn_logout;

    RelativeLayout rl_bank_accounts,rl_slips;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);

        rl_bank_accounts = (RelativeLayout) findViewById(R.id.rl_bank_accounts);
        rl_slips = (RelativeLayout) findViewById(R.id.rl_slips);

        rl_bank_accounts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserDashboard.this,BankAccounts.class);
                startActivity(intent);
            }
        });

        rl_slips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserDashboard.this,MyReceipts.class);
                startActivity(intent);
            }
        });



        btn_logout = (Button) findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(UserDashboard.this);
                builder.setTitle("Confirmation");
                builder.setMessage("Are you sure you want to logout?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        BankHelperSingleton.getInstance().removeUser(UserDashboard.this);
                        Intent intent = new Intent(UserDashboard.this,MainActivity.class);
                        startActivity(intent);
                        UserDashboard.this.finish();
                    }
                });
                builder.setNegativeButton("NO",null);
                builder.show();
            }
        });
    }
}

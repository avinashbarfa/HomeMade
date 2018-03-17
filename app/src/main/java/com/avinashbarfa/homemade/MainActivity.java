package com.avinashbarfa.homemade;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.accountkit.Account;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitCallback;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.AccountKitLoginResult;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSignOut = (Button)findViewById(R.id.btnLogOut);
        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccountKit.logOut();
                finish();

                //eerororr
            }
        });
    }



    @Override
    protected void onResume() {
        super.onResume();

        AccountKit.getCurrentAccount(new AccountKitCallback<Account>() {
            @Override
            public void onSuccess(Account account) {
                TextView userid , phone ,email;

            /*    userid = (TextView)findViewById(R.id.editUserId);
                userid.setText(account.getId());
                phone = (TextView) findViewById(R.id.editPhoneNumber);
                phone.setText(account.getPhoneNumber().toString());
                email = (TextView)findViewById(R.id.editEmailId);

                email.setText(account.getEmail());
            */}

            @Override
            public void onError(AccountKitError accountKitError) {

            }
        });
    }

}

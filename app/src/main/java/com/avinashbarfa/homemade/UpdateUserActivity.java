package com.avinashbarfa.homemade;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.avinashbarfa.homemade.Data.Links;
import com.avinashbarfa.homemade.Data.UserDataBean;
import com.facebook.accountkit.Account;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitCallback;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.AccountKitLoginResult;

import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Thread.sleep;

public class UpdateUserActivity extends AppCompatActivity {

    EditText edtUserName, edtEmailId, edtDOB;
    Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);

        edtUserName = (EditText) findViewById(R.id.edtUserName);
        edtEmailId = (EditText) findViewById(R.id.edtEmailId);
        edtDOB = (EditText) findViewById(R.id.edtDOB);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
       /* AccountKit.getCurrentAccount(new AccountKitCallback<Account>() {
                                         @Override
                                         public void onSuccess(Account account) {
                                             Log.v("contact number", account.getPhoneNumber().toString());
                                             Toast.makeText(UpdateUserActivity.this, account.getPhoneNumber().toString(), Toast.LENGTH_LONG).show();

                                         }

                                         @Override
                                         public void onError(AccountKitError accountKitError) {

                                         }
                                     });*/
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateUserData();
            }
        });
    }

    public void UpdateUserData(){
        Account account;
        final UserDataBean userDataBean = new UserDataBean();
        userDataBean.setFullName(edtUserName.getText().toString());
        userDataBean.setEmailId(edtEmailId.getText().toString());
        userDataBean.setDateOfBirth(edtDOB.getText().toString());

        Links links = new Links();
        String url = links.getDataBaseIPAddress() +"/home-made/UpdateUserInfo.php";

        RequestQueue queue = Volley.newRequestQueue(UpdateUserActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if("True".equals(response)) {
                            Toast.makeText(UpdateUserActivity.this, "Account Created Successfully" , Toast.LENGTH_LONG).show();
                            startActivity(new Intent(UpdateUserActivity.this, MainActivity.class));
                        } else if("False".equals(response)) {
                            Toast.makeText(UpdateUserActivity.this, "Error in Updating data, Please try Again" , Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(UpdateUserActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<>();
                params.put("userName", userDataBean.getFullName());
                params.put("userEmailId", userDataBean.getEmailId());
                params.put("userDOB", userDataBean.getDateOfBirth());
                return params;
            }
        };
        queue.add(stringRequest);

    }
}


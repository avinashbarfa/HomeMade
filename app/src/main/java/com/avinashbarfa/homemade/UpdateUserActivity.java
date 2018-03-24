package com.avinashbarfa.homemade;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UpdateUserActivity extends AppCompatActivity {

    EditText edtUserName, edtEmailId;
    Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);

        edtUserName = (EditText) findViewById(R.id.edtUserName);
        edtEmailId = (EditText) findViewById(R.id.edtEmailId);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateUserData();
            }
        });
    }

    public void UpdateUserData(){

        final UserDataBean userDataBean = new UserDataBean();
        userDataBean.setFullName(edtUserName.getText().toString());
        userDataBean.setEmailId(edtEmailId.getText().toString());

        Links links = new Links();
        String url = links.getDataBaseIPAddress() + "/Snap-Tickets/cxc.php";

        RequestQueue queue = Volley.newRequestQueue(UpdateUserActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(UpdateUserActivity.this, response, Toast.LENGTH_LONG).show();
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
                return params;
            }
        };
        queue.add(stringRequest);

    }
}


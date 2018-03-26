package com.avinashbarfa.homemade;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class FeedBack extends AppCompatActivity {

    EditText txtFeedBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txtFeedBack = (EditText) findViewById(R.id.txtfeedback);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == fab) {
                    //    Data db = new Data();
                    String url = "";
                    RequestQueue queue = Volley.newRequestQueue(FeedBack.this);
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Toast.makeText(FeedBack.this, response, Toast.LENGTH_LONG).show();
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(FeedBack.this, error.toString(), Toast.LENGTH_LONG).show();
                                }
                            }
                    ) {
                        @Override
                        protected Map<String, String> getParams() {

                            Map<String, String> params = new HashMap<>();
                            params.put("feedBackMessage", txtFeedBack.getText().toString());
                            params.put("sessionID", "avibarfa98@gmail.com");
                            return params;
                        }
                    };
                    queue.add(stringRequest);
                }
            }
        });
    }
}

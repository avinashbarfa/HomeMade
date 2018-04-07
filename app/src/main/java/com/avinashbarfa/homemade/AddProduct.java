package com.avinashbarfa.homemade;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.avinashbarfa.homemade.Data.Functions;
import com.avinashbarfa.homemade.Data.Links;
import com.google.firebase.auth.FirebaseAuth;

import java.util.HashMap;
import java.util.Map;

public class AddProduct extends AppCompatActivity {

    EditText editProductName, editPrice, editQuantity, editDescription, editContactNumber;
    Button btnAddProd;

    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;
    Links urlLinks = new Links();
    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        Functions functions = new Functions();
        if(!functions.isConnected(AddProduct.this)) builderDialog(AddProduct.this).show();

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() == null ) {
                    startActivity(new Intent(AddProduct.this , LoginActivity.class));
                }
            }
        };
        editProductName = (EditText) findViewById(R.id.editProductName);
        editPrice = (EditText) findViewById(R.id.editPrice);
        editQuantity = (EditText) findViewById(R.id.editQuantity);
        editDescription = (EditText) findViewById(R.id.editDescription);
        editContactNumber = (EditText) findViewById(R.id.editContactNumber);
        btnAddProd = (Button) findViewById(R.id.btnAddProd);
        btnAddProd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addProduct();
            }
        });

    }

    private void addProduct() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlLinks.getAddProductURL() , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                    Toast.makeText(getApplicationContext(), response , Toast.LENGTH_LONG).show();
                    startActivity(new Intent(AddProduct.this, MainActivity.class));
                    finish();
            }
        },  new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage() , Toast.LENGTH_LONG).show();
                    }
                }
        )
        {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("ProdName" , editProductName.getText().toString());
                params.put("ProdPrice" , editPrice.getText().toString());
                params.put("ProdQuantity", editQuantity.getText().toString());
                params.put("ProdDescription", editDescription.getText().toString());
                params.put("ContactNumber" , editContactNumber.getText().toString());
                params.put("addedBy", mAuth.getCurrentUser().getEmail());
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public AlertDialog.Builder builderDialog(Context context) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("No Internet Connection");
        builder.setMessage("You need To have Internet Connection");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        return builder;
    }
}

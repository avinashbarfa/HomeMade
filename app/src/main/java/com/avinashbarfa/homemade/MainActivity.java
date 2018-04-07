package com.avinashbarfa.homemade;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.avinashbarfa.homemade.Adapters.MyCategoriesAdapter;
import com.avinashbarfa.homemade.Data.CategoriesData;
import com.avinashbarfa.homemade.Data.Functions;
import com.avinashbarfa.homemade.Data.Links;
import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Button btnArrowDown;
    TextView locationMain;
    TextView txtDisplayName, txtemailID;
    ImageView userProfileImg;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<CategoriesData> categoriesData;
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
        setContentView(R.layout.activity_main);

        Functions functions = new Functions();
        if(!functions.isConnected(MainActivity.this)) builderDialog(MainActivity.this).show();

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() == null ) {
                    startActivity(new Intent(MainActivity.this , LoginActivity.class));
                }
            }
        };

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headView  = navigationView.getHeaderView(0);
        txtDisplayName = (TextView) headView.findViewById(R.id.txtDisplayName);
        txtemailID = (TextView) headView.findViewById(R.id.txtemailID);
        userProfileImg = (ImageView) headView.findViewById(R.id.userProfileImg);


        locationMain = (TextView) findViewById(R.id.location);
        //locationMain.setText();
        btnArrowDown =(Button) findViewById(R.id.btnArrowDown);
        btnArrowDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SelectLocation.class));
            }
        });

        categoriesData = new ArrayList<>();
        loadNavigationUserData();
        loadRecyclerViewData();

    }

    private void loadNavigationUserData() {
        mAuth = FirebaseAuth.getInstance();
        txtDisplayName.setText(mAuth.getCurrentUser().getDisplayName());
        txtemailID.setText(mAuth.getCurrentUser().getEmail());
        Glide.with(this).load(mAuth.getCurrentUser().getPhotoUrl()).into(userProfileImg);

    }

    private void loadRecyclerViewData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Categories...");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlLinks.getRetrieveCategoriesURL() , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("result");

                    for(int i =0; i<array.length();i++){
                        JSONObject object = array.getJSONObject(i);
                        CategoriesData list = new CategoriesData(
                                object.getString("category_id"),
                                object.getString("category_name"),
                                object.getString("category_subtitle"),
                                object.getString("category_imageUrl"));

                        categoriesData.add(list);
                    }
                    adapter = new MyCategoriesAdapter(categoriesData, getApplicationContext());
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), error.getMessage() , Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home){
            startActivity(new Intent(MainActivity.this, MainActivity.class));
        } else if (id == R.id.nav_add_product) {
            startActivity(new Intent(MainActivity.this, AddProduct.class));
        } else if (id == R.id.nav_share) {
            try {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_SUBJECT, "Home Made");
                String sAux = "\nLet me recommend you this application\n\n";
                sAux = sAux + "https://play.google.com/store/apps/details?id=Orion.Soft \n\n";
                i.putExtra(Intent.EXTRA_TEXT, sAux);
                startActivity(Intent.createChooser(i, "choose one"));
            } catch (Exception e) {
                e.toString();
            }
        } else if (id == R.id.nav_feedback) {
            startActivity(new Intent(MainActivity.this, FeedBack.class));
        } else if(id == R.id.nav_aboutus) {
            startActivity(new Intent(MainActivity.this, AboutUs.class));
        } else if (id == R.id.nav_logout) {
            mAuth.signOut();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
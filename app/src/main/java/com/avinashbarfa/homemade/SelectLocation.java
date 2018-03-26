package com.avinashbarfa.homemade;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SelectLocation extends AppCompatActivity {

    private static final int REQUEST_LOCATION = 1;
    Button gpsFixed;
    TextView locationTxtView;
    LocationManager locationManager;
    String latitude,longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_location);
        getLocationAccessPermission();    // to call on activity open
        gpsFixed = (Button)findViewById(R.id.gps_fixed);
        gpsFixed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLocationAccessPermission(); // to refresh on btn click
            }
        });

    }

    private void getLocationAccessPermission() {
        ActivityCompat.requestPermissions(this,new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        locationTxtView = (TextView)findViewById(R.id.location);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if(!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            //   buildAlertMessageNoGps();
            Toast.makeText(SelectLocation.this, "Error in jkl", Toast.LENGTH_LONG).show();
        } else if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            getLocation();
        }
    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(SelectLocation.this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(SelectLocation.this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(SelectLocation.this,new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        } else {
            android.location.Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if (location != null) {
                double lati = location.getLatitude();
                double lon = location.getLongitude();
                latitude = String.valueOf(lati);
                longitude = String.valueOf(lon);
                locationTxtView.setText(latitude+" "+longitude);
            } else {
                locationTxtView.setText("Unable To Trace Location");
            }
        }
    }
}

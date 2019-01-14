package com.wjf.ivy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

public class GoogleMapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private FusedLocationProviderClient mapClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_map);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.getMyLocation();
    }
}

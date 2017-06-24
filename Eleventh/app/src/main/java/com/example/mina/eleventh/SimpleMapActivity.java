package com.example.mina.eleventh;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class SimpleMapActivity extends AppCompatActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_map);

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.simplemap);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng SYR = new LatLng(43.0481, -76.1474);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(SYR);
        markerOptions.title("Syracuse, NY");
        markerOptions.snippet("Center of the Universe");
        googleMap.addMarker(markerOptions);

        googleMap.moveCamera(CameraUpdateFactory.newLatLng(SYR));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(10));
    }
}

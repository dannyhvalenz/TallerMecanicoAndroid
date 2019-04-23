package com.serviciomecanico.serviciomecanico.Mapas;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DatabaseReference;
import com.serviciomecanico.serviciomecanico.Conexion.Conexion;
import com.serviciomecanico.serviciomecanico.R;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Marker marker;
    String lat,log;

    //Creando variable de referencia para la base de datos (Firebase)
    DatabaseReference firebase;
    //Llamando a la clase conexion para la creacion de la referencia
    Conexion conexion = new Conexion();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        //Una vez se inicia la actividad se manda a llamar a firebase
        firebase = conexion.conexion();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng xalapa = new LatLng(19.541773, -96.923179);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(xalapa));
        mMap.setMinZoomPreference(12.0f);
        mMap.setMaxZoomPreference(50.0f);

        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                mMap.clear();
                mMap.addMarker(new MarkerOptions()
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                        .anchor(0.0f,1.0f)
                        .position(latLng));
            }
        });

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                lat = Double.toString(marker.getPosition().latitude);
                log = Double.toString(marker.getPosition().longitude);
                return false;
            }
        });

    }

    public void btn_guardarMapa(View view){
        firebase.child("Posicion").child("latitude").setValue(lat);
        firebase.child("Posicion").child("longitude").setValue(log);
        finish();
    }
}

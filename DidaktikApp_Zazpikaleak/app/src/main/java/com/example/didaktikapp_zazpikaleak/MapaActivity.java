package com.example.didaktikapp_zazpikaleak;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.didaktikapp_zazpikaleak.databinding.ActivityMapaBinding;

public class MapaActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapaBinding binding;
    private Marker marker;
    private int cont1 = 0, cont2 = 0, cont3 = 0, cont4 = 0, cont5 = 0, cont6 = 0, cont7 = 0;
    private String titulo = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        // Add a marker in Sydney and move the camera
        CameraUpdate cam = CameraUpdateFactory.newLatLngZoom(new LatLng(43.25894162873006, -2.9228788867459294), 16);
        mMap.moveCamera(cam);
        insertarMarcador();

        //Al pulsar sobre el marcador
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                marker.showInfoWindow();//Visualizar info marcador
                //marker.hideInfoWindow(); //Ocultar info marcador
                mMap.getUiSettings();

                if (marker.getTitle().equals(titulo)) {
                    if (marker.getTitle().equals("Mercado de la Ribera")) {
                        Intent intent = new Intent(MapaActivity.this, Zona1_1.class);
                        startActivity(intent);
                        finish();
                    }

                    if (marker.getTitle().equals("Iglesia y Puente de San Antón")) {
                        Intent intent = new Intent(MapaActivity.this, Zona1_3.class);
                        startActivity(intent);
                        finish();
                    }

                    if (marker.getTitle().equals("El Arenal")) {
                        Intent intent = new Intent(MapaActivity.this, Zona2_1.class);
                        startActivity(intent);
                        finish();
                    }

                    if (marker.getTitle().equals("Ría de Bilbao")) {
                        Intent intent = new Intent(MapaActivity.this, Zona3_1.class);
                        startActivity(intent);
                        finish();
                    }

//                    if (marker.getTitle().equals("Iglesia y Puente de San Antón")) {
//                        Intent intent = new Intent(MapaActivity.this, Zona1_3.class);
//                        startActivity(intent)
                }else{
                    titulo = marker.getTitle();
                }
                return true;
            }
        });
    }

    private void insertarMarcador(){

        // Marcamos la actividad como hecha en la base de datos pasandole el nombre de la base de datos
        ZazpiKaleakSQLiteHelper zazpidbh = new ZazpiKaleakSQLiteHelper(getBaseContext(), "ZazpikaleakDB", null, 1);
        ProgresoDao pd = new ProgresoDao();

        // Consulta a la bbdd si está hecho o no para crearlo de un color o de otro
        if  (pd.isHecha(zazpidbh,"Actividad 1")){
            marker = mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_verde)).position(new LatLng(43.25597973436736, -2.923720327217282))
                    .title("Mercado de la Ribera"));
            marker.setVisible(true);
        } else {
            marker = mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_rojo)).position(new LatLng(43.25597973436736, -2.923720327217282))
                    .title("Mercado de la Ribera"));
            marker.setVisible(true);
        }


        marker.showInfoWindow();
        if  (pd.isHecha(zazpidbh,"Actividad 11")){
            marker = mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_verde)).position(new LatLng(43.255491548570184, -2.922557026304906))
                    .title("Iglesia y Puente de San Antón")
            );
            marker.setVisible(true);
        }else{
            marker = mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_rojo)).position(new LatLng(43.255491548570184, -2.922557026304906))
                    .title("Iglesia y Puente de San Antón")
            );
            marker.setVisible(true);
        }

        marker.showInfoWindow();
        marker = mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_rojo)).position(new LatLng(43.26019374692103, -2.922419205181956))
                .title("Iglesia de San Nicolás")
        );
        marker.setVisible(true);

        marker.showInfoWindow();
        marker = mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_rojo)).position(new LatLng(43.260974434681735, -2.9239248139077127))
                .title("Ría de Bilbao")
        );
        marker.setVisible(true);

        marker.showInfoWindow();
        marker = mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_rojo)).position(new LatLng(43.260196631277886, -2.92369518968737))
                .title("El Arenal")
        );
        marker.setVisible(true);

        marker.showInfoWindow();
        marker = mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_rojo)).position(new LatLng(43.25911003682046, -2.922602431313634))
                .title("Plaza Nueva")
        );
        marker.setVisible(true);

        marker.showInfoWindow();
        marker = mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_rojo)).position(new LatLng(43.25970944619022, -2.9246468490538122))
                .title("Teatro Arriaga")
        );
        marker.setVisible(true);

        marker.showInfoWindow();

        marker = mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_rojo)).position(new LatLng(43.257733010151746, -2.9250813643974927))
                .title("Calle Pelota")
        );
        marker.setVisible(true);

        marker.showInfoWindow();
    }

}
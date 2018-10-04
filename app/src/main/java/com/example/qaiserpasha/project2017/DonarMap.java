package com.example.qaiserpasha.project2017;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qaiserpasha.project2017.Fragments.Tab1;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class DonarMap extends FragmentActivity implements OnMapReadyCallback {

    Button btnsettlite , btnUseLocation;
    TextView txtMapLocal;
    String adrss1,adrss2,adrss3;
    int count =0;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donar_map);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        btnsettlite=(Button)findViewById(R.id.btnSatteliteView);
        btnUseLocation=(Button)findViewById(R.id.buttonUseLocationn);
        final TextView txtLoc = (TextView) findViewById(R.id.textView1stLocation);




            //Satelite View Button
        btnsettlite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mMap.getMapType()==GoogleMap.MAP_TYPE_NORMAL)
                {
                    mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                }
               else if (mMap.getMapType()==GoogleMap.MAP_TYPE_HYBRID)
                {
                    mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                }
            }
        });


        btnUseLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getBaseContext(), WelcomeDonar.class);
                intent.putExtra("mapValues", txtLoc.getText().toString());
                startActivity(intent);

            }
        });

            // Search the Location

        Button btnserch = (Button) findViewById(R.id.button);
        btnserch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText etLocat = (EditText) findViewById(R.id.search_location);
                String location = etLocat.getText().toString();
                List<Address> addressList = null;
                if (location != null || location.equals("")) {
                    Geocoder geocoder = new Geocoder(getApplicationContext());
                    try {
                        addressList = geocoder.getFromLocationName(location, 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Address address = addressList.get(0);
                    LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                    mMap.addMarker(new MarkerOptions().position(latLng).title(location));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                }

            }
        });
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
        LatLng sydney = new LatLng(33.63944333, 73.0635483);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Paskistan"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);

        // Getting Adress

        // Ontouch map and get adress
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng point) {

                MarkerOptions marker = new MarkerOptions().position(new LatLng(point.latitude, point.longitude)).title(point.toString());  // on marker click it show lat long
                mMap.addMarker(marker);

                double touchLat = point.latitude;
                double touchLong = point.longitude;

                ///here is reverse GeoCoding which helps in getting address from latlng

                try {
                    Geocoder geo = new Geocoder(DonarMap.this.getApplicationContext(), Locale.getDefault());
                    List<Address> addresses = geo.getFromLocation(touchLat, touchLong, 1);
                    if (addresses.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Waiting for Location", Toast.LENGTH_SHORT).show();
                    } else {

                        String address;
                        if (addresses.size() > 0) {
                            address = addresses.get(0).getFeatureName()
                                    + "," + addresses.get(0).getLocality()
                                    + "," + addresses.get(0).getAdminArea()
                                    + "," + addresses.get(0).getCountryName();
                           // Toast.makeText(getApplicationContext(), "Address:- " + address, Toast.LENGTH_LONG).show();
                           if(count == 0)
                           {
                               adrss1=address;
                               Toast.makeText(getApplicationContext(), "Address:- " + address, Toast.LENGTH_LONG).show();
                               count++;
                           }
//                           else if (count == 1)
//                           {
//                               adrss2=address;
//                               Toast.makeText(getApplicationContext(), "Address:- " + address, Toast.LENGTH_LONG).show();
//                               count++;
//                           }
//                           else if(count == 2) {
//                               adrss3 = address;
//                               Toast.makeText(getApplicationContext(), "Address:- " + address, Toast.LENGTH_LONG).show();
//                               count++;
//
//                           }
                           else {
                               Toast.makeText(getApplicationContext(),"You may not select more than 3 locations", Toast.LENGTH_LONG).show();
                           }
                            TextView tv1stLoc=(TextView)findViewById(R.id.textView1stLocation);
                            tv1stLoc.setText(adrss1);
//                            TextView tv2ndLoc=(TextView)findViewById(R.id.textView2ndLocation);
//                            tv2ndLoc.setText(adrss2);
//                            TextView tv3rdLoc=(TextView)findViewById(R.id.textView3rdLocation);
//                            tv3rdLoc.setText(adrss3);

                        }

                        // draws the marker at the currently touched location
                        // drawMarker(point,"Your Touched Position",address+"");


                    }
                } catch (Exception e) {
                    e.printStackTrace(); // getFromLocation() may sometimes fail
                }


            }
        });
    }
}

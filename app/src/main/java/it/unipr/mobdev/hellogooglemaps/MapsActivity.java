package it.unipr.mobdev.hellogooglemaps;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Marco Picone (picone.m@gmail.com) 20/03/2020
 * Simple Activity and application to show how to use Google Maps API and UI components
 */
public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    public static final String TAG = "MobDev-MapExample";

    private GoogleMap mMap;

    private MarkerOptions myMarkerOptions = null;
    private Marker myMarker = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        PoiDescriptor pA = new PoiDescriptor(44.72, 10.21);
        PoiDescriptor pB = new PoiDescriptor(44.76, 10.22);

        if(pA == pB)
            Log.d(TAG, "UGUALI !");
        else
            Log.d(TAG, "DIVERSI");

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

        initMap();

        /*
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        */
    }

    private void initMap(){

        if(mMap != null)
        {
            //Create a geographic point starting from latitude and longitude coordinate
            LatLng parmaUniversity = new LatLng(44.76516282282244,10.311720371246338);

            //Enable or disable user location layer
            //mMap.setMyLocationEnabled(true);

            //Move the map to a specific point
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(parmaUniversity, 13));

            //Change map Type
            //mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

            //Create new marker options
            myMarkerOptions = new MarkerOptions();
            myMarkerOptions.title("Unipr - 1");
            myMarkerOptions.snippet("Universita' degli Studi di Parma - Facolta' di Ingegneria Sede Didattica");
            myMarkerOptions.position(parmaUniversity);

            //Change the default icon using a drawable resource
            myMarkerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_location_place));

            //Example about how to use a default marker with a different color
            //myMarkerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));

            //Add the marker to the Map and return the created marker object
            myMarker = mMap.addMarker(myMarkerOptions);

            //The same kind of marker definition and add operation in one line
            //map.addMarker(new MarkerOptions().title("Unipr - A").snippet("Universita' degli Studi di Parma - Facolta' di Ingegneria Sede Didattica").position(parmaUniversity));

            //Create and Assign the listener for onClick event on the Map
            mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

                //Listener method to receive the LatLng object associated to the clicked point on the map
                @Override
                public void onMapClick(LatLng point) {
                    Log.d(TAG, "MainActivity ---> onMapClick: " + point.latitude + ";" + point.longitude);
                }
            });

            //Create and Assign the listener for onLongClick event on the Map
            mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {

                @Override
                public void onMapLongClick(LatLng point) {
                    Log.d(TAG, "MainActivity ---> onMapLongClick: " + point.latitude + ";" + point.longitude);
                }
            });

            //Create and Assign the listener for available markers
            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {

                @Override
                public boolean onMarkerClick(Marker marker) {

                    if (myMarker != null && marker.equals(myMarker))
                        Log.d(TAG, "MainActivity ---> onInfoWindowClick of MyMarker");

                    //True if we want that the event has been consumed or false to propagate it
                    return false;
                }
            });

            //Create and Assign the listener for InfoWindows of existing markers
            mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {

                @Override
                public void onInfoWindowClick(Marker marker) {
                    if (myMarker != null && marker.equals(myMarker))
                        Log.d(TAG, "MainActivity ---> onInfoWindowClickListener of MyMarker");
                }
            });
        }

    }
}

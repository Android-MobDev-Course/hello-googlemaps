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

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Marco Picone (picone.m@gmail.com) 20/03/2020
 * Simple Activity and application to show how to use Google Maps API and UI components
 */
public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    public static final String TAG = "MobDev-MapExample";

    private GoogleMap mMap;

    private Map<Marker, PoiDescriptor> poiMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //Init Map to keep track of Marker and PoiDescriptor Classes
        this.poiMap = new HashMap<>();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        initMap();
        initWithSimpleMarkerList();
    }

    private void initMap(){
        if(mMap != null)
        {
            //Create a geographic point starting from latitude and longitude coordinate
            LatLng mapCenter = new LatLng(44.76516282282244,10.311720371246338);

            //Enable or disable user location layer
            //mMap.setMyLocationEnabled(true);

            //Move the map to a specific point
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mapCenter, 13));

            //Change map Type
            //mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

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

                    if (marker != null && poiMap.containsKey(marker)) {
                        PoiDescriptor myPoiDescriptor = poiMap.get(marker);
                        Log.d(TAG, "MainActivity ---> onMarkerClick of PoiDescriptor: " + myPoiDescriptor.getName());
                    }

                    //True if we want that the event has been consumed or false to propagate it
                    return false;
                }
            });

            //Create and Assign the listener for InfoWindows of existing markers
            mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {

                @Override
                public void onInfoWindowClick(Marker marker) {

                    if (marker != null && poiMap.containsKey(marker)) {
                        PoiDescriptor myPoiDescriptor = poiMap.get(marker);
                        Log.d(TAG, "MainActivity ---> onInfoWindowClickListener of PoiDescriptor: " + myPoiDescriptor.getName());
                    }

                }
            });
        }
    }

    private void initWithSimpleMarkerList(){

        if(mMap != null)
        {
            //Create a geographic point starting from latitude and longitude coordinate
            PoiDescriptor poiUniprEngClassrooms = new PoiDescriptor("Engineering Classrooms", "Unipr Engineering Classrooms", 44.765138, 10.312608);
            PoiDescriptor poiUniprEngLaboratories = new PoiDescriptor("Engineering Laboratories", "Unipr Engineering Laboratories",44.764843, 10.307982);

            addPoiToMap(poiUniprEngClassrooms);
            addPoiToMap(poiUniprEngLaboratories);
        }

    }

    private void addPoiToMap(PoiDescriptor poiDescriptor){

        if (poiDescriptor != null) {

            //Create new marker options
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.title(poiDescriptor.getName());
            markerOptions.snippet(poiDescriptor.getDescription());
            markerOptions.position(new LatLng(poiDescriptor.getLat(), poiDescriptor.getLng()));

            //Change the default icon using a drawable resource
            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_location_place));

            //Example about how to use a default marker with a different color
            //myMarkerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));

            //Add the marker to the Map and return the created marker object
            Marker marker = mMap.addMarker(markerOptions);
            this.poiMap.put(marker, poiDescriptor);
        }
    }
}

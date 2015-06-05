package com.dchen93.deliverysystem;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class MapsActivity extends FragmentActivity implements GoogleApiClient.ConnectionCallbacks, GoogleMap.OnInfoWindowClickListener,GoogleApiClient.OnConnectionFailedListener, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private double mLatitude;
    private double mLongitude;
    private LatLng currentLocation = null;
    private LatLng currentLocation2 = null;
    double xcoord = 0;
    double ycoord = 0;
    String deliveryFriend = "";
    int delivery = 0;
    String fileName;
    PolylineOptions options;

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation != null) {
            mLatitude = mLastLocation.getLatitude();
            mLongitude = mLastLocation.getLongitude();

            currentLocation = new LatLng(mLatitude+.01, mLongitude+.5);
            currentLocation2 = new LatLng(mLatitude, mLongitude);
            if (mMap != null) {
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation2, 10));

                Marker initialMarker = mMap.addMarker(new MarkerOptions().position(currentLocation).title("Tap Map For Directions"));
                initialMarker.showInfoWindow();
            } else {
                setUpMapIfNeeded();
            }
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        // only need to connect once for now
        Log.d("MapActivity: ", "ConnectionSuspended");
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.d("MapsActivity: ", "ConnectionFailed");
    }


    @Override
    public boolean onMarkerClick(Marker marker) {
        LatLng dest = marker.getPosition();
        if (dest == currentLocation) return true; // no need for directions to pin at your location

        String uri =
                "http://maps.google.com/maps?f=d&hl=en&saddr="
                        + Double.toString(currentLocation.latitude)
                        + ","
                        + Double.toString(currentLocation.longitude)
                        + "&daddr="
                        + Double.toString(dest.latitude)
                        + ","
                        + Double.toString(dest.longitude);

        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(Intent.createChooser(intent, "Select an application"));

        return true;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        LatLng dest = marker.getPosition();
        if (dest == currentLocation) return;

        String uri =
                "http://maps.google.com/maps?f=d&hl=en&saddr="
                        + Double.toString(currentLocation.latitude)
                        + ","
                        + Double.toString(currentLocation.longitude)
                        + "&daddr="
                        + Double.toString(dest.latitude)
                        + ","
                        + Double.toString(dest.longitude);

        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(Intent.createChooser(intent, "Select an application"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        buildGoogleApiClient();
        setUpMapIfNeeded();
        mMap.setMyLocationEnabled(true);
        mMap.setTrafficEnabled(true);

        // for marker clicks
        //mMap.setOnMarkerClickListener(this);
        mMap.setOnInfoWindowClickListener(this);

        fileName = getFilesDir()+"data.txt";
        String line;

        try {

            FileReader fileReader1 = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader1);
            int flag = 0;
            while ((line = bufferedReader.readLine()) != null) {
                //System.out.println(line);
                if(line.equals("-"))
                {
                    flag = 1;
                    continue;
                }
                if(flag == 1)
                {
                    if(line.equals("false"))
                    {
                        delivery = 0;
                        break;
                    }
                    else
                    {
                        delivery = 1;
                        flag++;
                    }
                    continue;
                }
                if(flag == 2)
                {
                    deliveryFriend = line;
                    flag++;
                    continue;
                }
                if(flag == 3)
                {
                    xcoord = Double.parseDouble(line);
                    flag++;
                    continue;
                }
                if(flag == 4)
                {
                    ycoord = Double.parseDouble(line);
                    flag++;
                    break;
                }
                //friends.add(line);
            }
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            try{
                PrintWriter writer = new PrintWriter("data.txt");
                writer.println("Daniel");
                writer.println("Ittai");
                writer.println("Ricky");
                writer.println("Kristijonas");
                writer.println("Bob");
                writer.println("Jane");
                writer.println("Joe");
                writer.println("Mary");
                writer.println("Elizabeth");
                writer.println("Larry");
                writer.println("Harry");
                writer.println("John");
                writer.println("Liz");
                writer.println("-");
                writer.println("false");
                writer.println(" ");//Deliveryfriend
                writer.println("38.532247");//xcoord
                writer.println("-121.577208");//ycoord
                writer.close();
            } catch(FileNotFoundException ex2) {

            }

        } catch (IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }


        // for map clicks
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                markerOptions.title("Tap Here");
                markerOptions.snippet("For Delivery Directions");

                mMap.clear(); // remove previous markers

                Marker directions = mMap.addMarker(markerOptions);
                directions.showInfoWindow();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        LocationManager locMan = (LocationManager) getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String provider = locMan.getBestProvider(criteria, false);
        Location location = locMan.getLastKnownLocation(locMan.getBestProvider(criteria, false));

        if (currentLocation != null) {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation,10));
        } else {
            LatLngBounds SOUTH_BAY = new LatLngBounds(new LatLng(37.195331, -122.33139), new LatLng(location.getLatitude(), location.getLongitude()));
            // Set the camera to the greatest possible zoom level that includes the bounds
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SOUTH_BAY.getCenter(),10));
        }

        mMap.setMapType(1);
        options = new PolylineOptions()
                .add(new LatLng(location.getLatitude()+.01, location.getLongitude()+.5))
                .add(new LatLng(location.getLatitude(), location.getLongitude()))
                .width(5)
                .visible(true)
                        //.zIndex(30)
                .color(Color.RED);

        mMap.addPolyline(options);




    }
}

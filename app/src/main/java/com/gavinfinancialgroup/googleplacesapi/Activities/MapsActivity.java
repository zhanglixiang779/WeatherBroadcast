package com.gavinfinancialgroup.googleplacesapi.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.gavinfinancialgroup.googleplacesapi.Interface.ApiInterface;
import com.gavinfinancialgroup.googleplacesapi.Pojo.Broadcast;
import com.gavinfinancialgroup.googleplacesapi.R;
import com.gavinfinancialgroup.googleplacesapi.Retrofit.RestClient;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.LinkedHashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.gavinfinancialgroup.googleplacesapi.R.id.map;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback
        , GoogleApiClient.OnConnectionFailedListener
        , GoogleApiClient.ConnectionCallbacks {

    private static final int DEFAULT_ZOOM_NUMBER = 12;
    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private Place place;
    public static final int AUTO_COMPLETE_REQUEST_CODE = 1;
    public static final String api = "c2fee7582157b23c85a8e79de8e8db40";
    private Location mCurrentLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .build();
        mGoogleApiClient.connect();

        Button weather = (Button) findViewById(R.id.weather);
        weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> data = new LinkedHashMap<String, String>();
//                data.put("lat", "-34");
//                data.put("lon", "151");
                if (place != null){
                    data.put("lat", String.valueOf(place.getLatLng().latitude));
                    data.put("lon", String.valueOf(place.getLatLng().longitude));
                } else {
                    data.put("lat", String.valueOf(mCurrentLocation.getLatitude()));
                    data.put("lon", String.valueOf(mCurrentLocation.getLongitude()));
                }
                data.put("cnt", "1");
                data.put("appid", api);

//                Map<String, String> data = new TreeMap<String, String>(hmap);
//                Set set = data.entrySet();
//                Iterator iterator = set.iterator();
//                while(iterator.hasNext()) {
//                    Map.Entry me = (Map.Entry)iterator.next();
//                    Log.d("test", me.getKey() + ":" + me.getValue());
//                    System.out.print(me.getKey() + ": ");
//                    System.out.println(me.getValue());

                ApiInterface apiInterface = RestClient.getInstance().create(ApiInterface.class);
                Call<Broadcast> call = apiInterface.getBroadcast(data);
                call.enqueue(new Callback<Broadcast>() {
                    @Override
                    public void onResponse(Call<Broadcast> call, Response<Broadcast> response) {
                        Log.d("test", call.request().url().toString());
                        Broadcast broadcast = response.body();
                        if (broadcast != null) {
                            //Toast.makeText(MapsActivity.this, broadcast.getName(), Toast.LENGTH_SHORT).show();
                            //Toast.makeText(MapsActivity.this, broadcast.getWeather().get(0).getDescription(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MapsActivity.this, WeatherActivity.class);
                            //intent.putExtra("country", broadcast.getList().get(0).getSys().getCountry());
                            intent.putExtra("city", broadcast.getList().get(0).getName());
                            intent.putExtra("min", String.valueOf(broadcast.getList().get(0).getMain().getTempMin()));
                            intent.putExtra("max", String.valueOf(broadcast.getList().get(0).getMain().getTempMax()));
                            intent.putExtra("description", broadcast.getList().get(0).getWeather().get(0).getDescription());
                            startActivity(intent);
                        } else {
                            Log.e("test", "null pointer");
                        }
                    }

                    @Override
                    public void onFailure(Call<Broadcast> call, Throwable t) {
                        Log.e("test", t.getMessage());
                        Log.e("test", call.request().url().toString());
                    }
                });
            }
        });
        Button search = (Button) findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY)
                            .build(MapsActivity.this);
                    startActivityForResult(intent, AUTO_COMPLETE_REQUEST_CODE);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AUTO_COMPLETE_REQUEST_CODE && resultCode == RESULT_OK) {
            place = PlaceAutocomplete.getPlace(this, data);
            mMap.addMarker(new MarkerOptions().title(String.valueOf(place.getName())).position(place.getLatLng()));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(place.getLatLng(), DEFAULT_ZOOM_NUMBER));
        }
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        //return super.onCreateOptionsMenu(menu);
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu_list, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        //return super.onOptionsItemSelected(item);
//        switch (item.getItemId()){
//            case R.id.search:
//                try {
//                    Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY)
//                            .build(this);
//                    startActivityForResult(intent, AUTO_COMPLETE_REQUEST_CODE);
//                } catch (GooglePlayServicesRepairableException e) {
//                    e.printStackTrace();
//                } catch (GooglePlayServicesNotAvailableException e) {
//                    e.printStackTrace();
//                }
//                break;
//        }
//        return true;
//    }


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
        LatLng currentLatLng = null;
        // Add a marker in Sydney and move the camera
        if (mCurrentLocation != null){
            currentLatLng = new LatLng(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude());
        } else {
            currentLatLng = new LatLng(41, 121);
        }
        //Log.d("ready", String.valueOf(mCurrentLocation.getLatitude()));
        mMap.addMarker(new MarkerOptions().position(currentLatLng).title("Current Location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 10));
        Log.d("test", "onMapReady: ");
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(map);
        mapFragment.getMapAsync(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mCurrentLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        Log.d("test", "onConnected: ");
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

}

package com.gavinfinancialgroup.googleplacesapi.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.gavinfinancialgroup.googleplacesapi.Interface.ApiInterface;
import com.gavinfinancialgroup.googleplacesapi.Next5Days.Broadcast5;
import com.gavinfinancialgroup.googleplacesapi.R;
import com.gavinfinancialgroup.googleplacesapi.RecyclerView.CustomAdapter;
import com.gavinfinancialgroup.googleplacesapi.Retrofit.RestClient;

import java.util.LinkedHashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.gavinfinancialgroup.googleplacesapi.Activities.MapsActivity.api;
import static com.gavinfinancialgroup.googleplacesapi.Activities.MapsActivity.mCurrentLocation;
import static com.gavinfinancialgroup.googleplacesapi.Activities.MapsActivity.place;

public class Next5Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next5);

        Map<String, String> data5 = new LinkedHashMap<String, String>();
        if (place != null){
            data5.put("lat", String.valueOf(place.getLatLng().latitude));
            data5.put("lon", String.valueOf(place.getLatLng().longitude));
        } else {
            data5.put("lat", String.valueOf(mCurrentLocation.getLatitude()));
            data5.put("lon", String.valueOf(mCurrentLocation.getLongitude()));
        }
        data5.put("appid", api);

        ApiInterface apiInterface = RestClient.getInstance().create(ApiInterface.class);
        Call<Broadcast5> call = apiInterface.getBroadcast5(data5);
        call.enqueue(new Callback<Broadcast5>() {
            @Override
            public void onResponse(Call<Broadcast5> call, Response<Broadcast5> response) {
                Log.d("test", "entered");
                Log.d("test", call.request().toString());
                Broadcast5 broadCast5 = response.body();
                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Next5Activity.this);
                recyclerView.setLayoutManager(linearLayoutManager);
                CustomAdapter customAdapter = new CustomAdapter(broadCast5.getList());
                recyclerView.setAdapter(customAdapter);
                DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                        linearLayoutManager.getOrientation());
                recyclerView.addItemDecoration(dividerItemDecoration);
            }

            @Override
            public void onFailure(Call<Broadcast5> call, Throwable t) {
                Log.e("test", t.getMessage());
                Log.e("test", call.request().url().toString());
            }
        });
    }
}

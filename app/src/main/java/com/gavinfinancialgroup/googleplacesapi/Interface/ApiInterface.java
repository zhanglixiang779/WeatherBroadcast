package com.gavinfinancialgroup.googleplacesapi.Interface;

import com.gavinfinancialgroup.googleplacesapi.CurrentWeather.Broadcast;
import com.gavinfinancialgroup.googleplacesapi.Next5Days.Broadcast5;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by zhang on 4/21/2017.
 */

public interface ApiInterface {
    @GET("data/2.5/find")
    Call<Broadcast> getBroadcast(@QueryMap Map<String,String> queries);

    @GET("data/2.5/forecast")
    Call<Broadcast5> getBroadcast5 (@QueryMap Map<String,String> queries);
}

package com.gavinfinancialgroup.googleplacesapi.Interface;

import com.gavinfinancialgroup.googleplacesapi.Pojo.Broadcast;

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
}

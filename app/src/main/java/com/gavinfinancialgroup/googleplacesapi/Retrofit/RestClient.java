package com.gavinfinancialgroup.googleplacesapi.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zhang on 4/21/2017.
 */

public class RestClient {
    private static final String baseUrl = "http://api.openweathermap.org/";
    private static Retrofit retrofit;

    public static Retrofit getInstance (){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}

package com.example.android.myapplication;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InstantiateRetrofitInstance {

    private static Retrofit retrofit;
    private static final String BASE_URL="https://jsonplaceholder.typicode.com";

    public static Retrofit getRetrofit(){
        if (retrofit ==null){
            retrofit=new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}

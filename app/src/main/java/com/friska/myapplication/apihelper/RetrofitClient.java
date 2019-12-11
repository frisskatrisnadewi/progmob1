package com.friska.myapplication.apihelper;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//deklarasi retrofit 2
public class RetrofitClient {
    private static Retrofit retrofit = null;


    public static Retrofit getClient (String baseURL){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        if (retrofit==null){
            retrofit=new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
                    .client(client)
                    .build();
        } return retrofit;
    }
}


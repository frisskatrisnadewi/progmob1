package com.friska.myapplication.apihelper;

public class UtilsApi {

    // 10.0.2.2 ini adalah localhost.
    public static final String BASE_URL_API = "http://192.168.1.47/Android2/";
   // public static final String BASE_URL_API = "http://friskatrisnadewi2.000webhostapp.com";

    // Mendeklarasikan Interface BaseApiService
    public static BaseApiService getAPIService(){
        return RetrofitClient.getClient(BASE_URL_API).create(BaseApiService.class);
    }
}

package com.friska.myapplication.adapter_controller_data_util_volley;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class Controller_Volley extends Application {
    // Request Queue, digunakan untuk mengirimkan permintaan ke jaringan, dapat membuat antrian permintaan pada permintaan
    // Request, berisi rincian yang diperlukan untuk membuat panggilan web API. Seperti metode yang digunakan (POST atau GET) melewatkan data, penerima respon dan error.

    //class tunggal yang menginisialisasi class global yang diperlukan. smeua objek berhubungan dengan volley diinisialisai disini
    public static final String TAG = Controller_Volley.class.getSimpleName();
    private RequestQueue mRequestQueue;
    private static Controller_Volley mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized Controller_Volley getInstance(){
        return mInstance;
    }

    public RequestQueue getRequestQueue(){
        if (mRequestQueue == null){
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag){
        req.setTag(TextUtils.isEmpty(tag) ? TAG:tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req){
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequest(Object tag){
        if(mRequestQueue != null){
            mRequestQueue.cancelAll(tag);
        }
    }
}

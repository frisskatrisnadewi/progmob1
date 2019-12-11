package com.friska.myapplication.adapter_controller_data_util_volley;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class Firebase_Volley {
    private  static Firebase_Volley instance;
    private RequestQueue requestQueue;
    private Context ctx;

    private Firebase_Volley(Context context) {
        ctx = context;
        requestQueue = getRequestQueue();
    }

    public static synchronized Firebase_Volley getInstance(Context context) {
        if (instance == null) {
            instance = new Firebase_Volley(context);
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }
}

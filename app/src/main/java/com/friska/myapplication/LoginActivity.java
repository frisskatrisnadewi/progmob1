package com.friska.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
/*import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;*/
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.preference.Preference;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.friska.myapplication.adapter_controller_data_util_volley.Controller_Volley;
import com.friska.myapplication.adapter_controller_data_util_volley.Firebase_Volley;
import com.friska.myapplication.adapter_controller_data_util_volley.Server_Volley;
import com.friska.myapplication.apihelper.BaseApiService;
import com.friska.myapplication.apihelper.UtilsApi;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

public class LoginActivity extends AppCompatActivity {

    Button gologin;
    EditText email_l, pass_l;
    TextView goregis;
    ProgressDialog loading;
    Context mContext;
    BaseApiService mApiService;
    int success;
    ConnectivityManager connectivityManager;
    private String url_login = Server_Volley.URL + "login.php";
    private static  final String TAG = LoginActivity.class.getSimpleName();
    private static final String TAG_SUCESS = "success";
    private String TAG_MESSAGE = "message";
    private String TAG_ID = "id";
    private String TAG_EMAIL = "email";
    private String TAG_NAMA = "nama";
    String tag_json_object="json_obj_req";
    SharedPreferences sharedPreferences;
    Preferences preferences;
    Boolean session = false;
    String id,email;
    public static final String my_shared_preferences="my_shared_preferences";
    public static final String session_status = "session_status";


    final private String FCM_API = "https://fcm.googleapis.com/fcm/send";
    final private String serverKey = "key=" + "AAAAT-TYXtY:APA91bFJyR4_A2_utWOPDv7q9BwFabkoC5gapyrhlTMlkiZsdSlUZ6vjPQ4aQY64bUZ45IDvZa1wM4rSz3TcMNCAb3q75IWNgmy73vj1h40BiWoguXZNnxebntWm1Uw7ooAgAHbQbLwI";
    final private String contentType = "application/json";
    final String TAG_NOTIF = "NOTIFICATION TAG";

    String NOTIFICATION_TITLE;
    String NOTIFICATION_EMAIL;
    String TOPIC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        preferences =  new Preferences(this);
        mContext = this;
        mApiService = UtilsApi.getAPIService(); // meng-init yang ada di package apihelper

        connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        {
            if (connectivityManager.getActiveNetworkInfo()!=null
                    && connectivityManager.getActiveNetworkInfo().isAvailable()
                    && connectivityManager.getActiveNetworkInfo().isConnected()){
            }else {
                Toast.makeText(getApplicationContext(),"No Internet Access",Toast.LENGTH_LONG).show();
            }
        }




        //deklarasi
        email_l =  findViewById(R.id.emailedittextinput);
        pass_l = findViewById(R.id.inputpassedittext);
        gologin =  findViewById(R.id.login1);
        goregis = findViewById(R.id.Register1);

        gologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);
                gotologin();
            }
        });

        goregis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoregister();
            }
        });
        //

        ceksesiion();


    }

    public void ceksesiion(){
/*        sharedPreferences = getSharedPreferences(my_shared_preferences,Context.MODE_PRIVATE);
        session = sharedPreferences.getBoolean(session_status,false);
        id = sharedPreferences.getString(TAG_ID,null);
        email = sharedPreferences.getString(TAG_EMAIL,null);

        if (session){
            Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
            intent.putExtra(TAG_EMAIL,email);
            intent.putExtra(TAG_ID,id);
            finish();
            startActivity(intent);
        }*/

        if (preferences.getSPSudahLogin()){
            startActivity(new Intent(LoginActivity.this, HomeActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }
    }

    private void ceklogin(final String email, final String password){
        StringRequest stringRequest= new StringRequest(Request.Method.POST, url_login, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e(TAG, "Login Response: " + response.toString());
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    success = jsonObject.getInt(TAG_SUCESS);
                    //cek eror di node json
                    if (success == 1) {
                        //String nama = jsonObject.getString(TAG_NAMA);
                        String email = jsonObject.getString(TAG_EMAIL);
                        String id = jsonObject.getString(TAG_ID);
                        Log.e("Login sukses", jsonObject.toString());
                        Toast.makeText(getApplicationContext(), jsonObject.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                        //menyimpan login ke session
                      /*  SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean(session_status, true);
                        editor.putString(TAG_ID, id);
                        editor.putString(TAG_EMAIL, email);
                        editor.commit();*/
                        preferences.saveSPString(preferences.SP_EMAIL, email);
                        preferences.saveSPBoolean(Preferences.SP_SUDAH_LOGIN, true);

                        //memanggil home activity
                      /*  Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        intent.putExtra(TAG_ID, id);
                        intent.putExtra(TAG_EMAIL, email);
                        finish();
                        startActivity(intent);*/
                        startActivity(new Intent(mContext, HomeActivity.class)
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                        finish();


                    } else {
                        Toast.makeText(getApplicationContext(), jsonObject.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG,"Eror Login"+error.getMessage());
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params= new HashMap<String, String>();
                params.put("email",email);
                params.put("password",password);
                params.put("Authorization", serverKey);
                params.put("Content-Type", contentType);

                return params;
            }
        };
        Controller_Volley.getInstance().addToRequestQueue(stringRequest,tag_json_object);
    }

    private void gotologin(){

        // login
        String email = email_l.getText().toString();
        String password = pass_l.getText().toString();

        //notif
            TOPIC = "/topics/userABC"; //topic must match with what the receiver subscribed to
            NOTIFICATION_TITLE = email_l.getText().toString();
            NOTIFICATION_EMAIL = pass_l.getText().toString();

            //buat string json notif
            JSONObject notification = new JSONObject();
            JSONObject notificationBody = new JSONObject();

        //mengecek kolom kosong
        if (email.trim().length() > 0 && password.trim().length()>0){
            if (connectivityManager.getActiveNetworkInfo()!=null
            && connectivityManager.getActiveNetworkInfo().isAvailable()
            && connectivityManager.getActiveNetworkInfo().isConnected()){
                ceklogin(email,password);
            }else {
                Toast.makeText(getApplicationContext(),"No internet", Toast.LENGTH_LONG).show();
            }
        }else {
            Toast.makeText(getApplicationContext(),"kolom tidak boleh kosong", Toast.LENGTH_LONG).show();
        }

        // untuk notif
        try {
            notificationBody.put("title", NOTIFICATION_TITLE);
            notificationBody.put("message", NOTIFICATION_EMAIL);

            notification.put("to", TOPIC);
            notification.put("data", notificationBody);
        } catch (JSONException e) {
            Log.e(TAG, "onCreate: " + e.getMessage() );
        }
        sendNotification(notification);
    }



    private void sendNotification(JSONObject notification){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(FCM_API, notification,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i(TAG, "onResponse: " + response.toString());
                        email_l.setText("");
                        pass_l.setText("");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this, "Request error", Toast.LENGTH_LONG).show();
                        Log.i(TAG, "onErrorResponse: Didn't work");
                    }
                }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Authorization", serverKey);
                params.put("Content-Type", contentType);
                return params;
            }
        };
        Firebase_Volley.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);
    }












     /* mApiService.loginRequest(email_l.getText().toString(), pass_l.getText().toString())
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                           // loading.dismiss();
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                if (jsonRESULTS.getString("error").equals("false")){
                                    // Jika login berhasil maka data nama yang ada di response API
                                    // akan diparsing ke activity selanjutnya.
                                    Toast.makeText(mContext, "BERHASIL LOGIN", Toast.LENGTH_SHORT).show();
                                    String nama = jsonRESULTS.getJSONObject("user").getString("nama");
                                    Intent intent = new Intent(mContext, HomeActivity.class);
                                    intent.putExtra("result_nama", nama);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    // Jika login gagal
                                    String error_message = jsonRESULTS.getString("error_msg");
                                    Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                           // loading.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("debug", "onFailure: kenapa sih kamu muncul eror > " + t.toString());
                       // loading.dismiss();
                    }
                });*/



      /* Intent intentLogin = new Intent(this, HomeActivity.class);
        startActivity(intentLogin);
        finish();*/


    private void gotoregister(){
        Intent intentLogin = new Intent(this, RegisterActivity.class);
        startActivity(intentLogin);
        finish();
    }
}

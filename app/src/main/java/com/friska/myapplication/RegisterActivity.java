package com.friska.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
/*import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;*/
import android.net.ConnectivityManager;
import android.os.Bundle;
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
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

public class RegisterActivity extends AppCompatActivity {

    EditText email_r, pass_r,nama_r;
    Button register2,notif;
    TextView login2;
    ProgressDialog loading;
    Context mContext;
    BaseApiService mApiService;
    int success;
    ConnectivityManager connectivityManager;
    private String url_register = Server_Volley.URL + "register.php";
    private static  final String TAG = RegisterActivity.class.getSimpleName();
    private static final String TAG_SUCESS = "success";
    private String TAG_MESSAGE = "message";
    String tag_json_object="json_obj_req";


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
        setContentView(R.layout.activity_register);

        mContext = this;

        mApiService = UtilsApi.getAPIService();

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
        notif = (Button) findViewById(R.id.notif);
        register2 = (Button) findViewById(R.id.Register2);
        login2=(TextView) findViewById(R.id.login2);
        nama_r= (EditText) findViewById(R.id.namaregistext);
        //alamat= (TextInputEditText) findViewById(R.id.alamatregisinput);
        email_r= (EditText) findViewById(R.id.emailregisinput);
        pass_r= (EditText) findViewById(R.id.passregisinput);

        //KLIK BUTTON
        register2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 //loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);
                savedata();
            }
        });

        notif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TOPIC = "/topics/userABC"; //topic must match with what the receiver subscribed to
                NOTIFICATION_TITLE = nama_r.getText().toString();
                NOTIFICATION_EMAIL = email_r.getText().toString();

                JSONObject notification = new JSONObject();
                JSONObject notificationBody = new JSONObject();
                try {
                    notificationBody.put("title", NOTIFICATION_TITLE);
                    notificationBody.put("message", NOTIFICATION_EMAIL);

                    notification.put("to", TOPIC);
                    notification.put("data", notificationBody);
                } catch (JSONException e) {
                    Log.e(TAG, "onCreate: " + e.getMessage() );
                }
                sendNotification(notification);
            }});




        login2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotologin_r();
            }
        });



    }




    private void sendNotification(JSONObject notification){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(FCM_API, notification,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i(TAG, "onResponse: " + response.toString());
                        nama_r.setText("");
                        email_r.setText("");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegisterActivity.this, "Request error", Toast.LENGTH_LONG).show();
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






    public void gotologin_r(){
        Intent loginintent = new Intent(this, LoginActivity.class);
        startActivity(loginintent);
        finish();
    }

    private void cekRegister(final String email, final String password, final String nama){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_register, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e(TAG, "Register Response: " + response.toString());
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    success = jsonObject.getInt(TAG_SUCESS);

                    //cek eror di json
                    if (success == 1) {
                        Log.e("Register sukses", jsonObject.toString());
                        Toast.makeText(getApplicationContext(), jsonObject.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                        email_r.setText("");
                        pass_r.setText("");
                        nama_r.setText("");
                    } else {
                        Toast.makeText(getApplicationContext(), jsonObject.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    //JSON EROR
                    e.getStackTrace();
                }
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG,"Eror Register"+error.getMessage());
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
               Map<String, String> params= new HashMap<String, String>();
                // Posting parameters to register url
               params.put("email",email);
               params.put("password",password);
               params.put("nama",nama);

                return params;
            }
        };

        //tambah request ke request queue
        Controller_Volley.getInstance().addToRequestQueue(stringRequest,tag_json_object);

    }

        public void savedata(){
        String email = email_r.getText().toString();
        String password = pass_r.getText().toString();
        String nama = nama_r.getText().toString();

        if(connectivityManager.getActiveNetworkInfo()!=null
                && connectivityManager.getActiveNetworkInfo().isAvailable()
                && connectivityManager.getActiveNetworkInfo().isConnected()){
            cekRegister(email,password,nama);
        }else {
            Toast.makeText(getApplicationContext(),"No Internet Access",Toast.LENGTH_SHORT).show();
        }















      /*  mApiService.registerRequest(nama_r.getText().toString(),
                email_r.getText().toString(),
                pass_r.getText().toString())
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            Log.i("debug", "onResponse: BERHASIL");
                            //loading.dismiss();
                            try {

                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                if (jsonRESULTS.getString("error").equals("false")){
                                    Toast.makeText(mContext, "BERHASIL REGISTRASI", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(mContext, LoginActivity.class));
                                } else {
                                    String error_message = jsonRESULTS.getString("error_msg");
                                    Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Log.i("debug", "onResponse: GA BERHASIL");
                            //loading.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.getMessage());
                        Toast.makeText(mContext, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
                    }
                });
*/




        /*
        user.setNama(nama.getText().toString().trim());
        user.setPassword(pass.getText().toString().trim());
        user.setAlamat(alamat.getText().toString().trim());
        user.setEmail(email.getText().toString().trim());

        databaseHelper.addData(user);

        Toast.makeText(getApplicationContext(),"data saved", Toast.LENGTH_LONG).show();

        nama.getText().toString().trim();
        alamat.getText().toString().trim();
        email.getText().toString().trim();
        pass.getText().toString().trim();
*/


      /* class AddUser extends AsyncTask<Void,Void,String>{

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(RegisterActivity.this,"Menambahkan ...","Tunggu...",false,false);

            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(RegisterActivity.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... voids) {
                HashMap<String, TextInputEditText> params = new HashMap<>();
                params.put(ConfigToMySQL.KEY_USER_NAMA,nama);
                params.put(ConfigToMySQL.KEY_USER_ALAMAT,alamat);
                params.put(ConfigToMySQL.KEY_USER_EMAIL,email);
                params.put(ConfigToMySQL.KEY_USER_PASS,pass);

        RequestHandler rh = new RequestHandler();
        String res = rh.sendPostRequest(ConfigToMySQL.URL_ADD,params);
        return res;

    }*/
    }
    /*AddUser au = new AddUser();
        au.execute();*/


    //  }
}

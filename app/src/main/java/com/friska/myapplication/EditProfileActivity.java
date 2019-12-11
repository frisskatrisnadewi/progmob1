package com.friska.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.friska.myapplication.adapter_controller_data_util_volley.Controller_Volley;
import com.friska.myapplication.adapter_controller_data_util_volley.Server_Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class EditProfileActivity extends AppCompatActivity {

    EditText nama_e,alamat_e,nohp_e;
    Button edit;
    private String url_edit2="http://wardrobebyus.000webhostapp.com/Android3/edit1.php?email=";
    private String url_update="http://wardrobebyus.000webhostapp.com/Android3/update.php?email=";
    private String url_edit= Server_Volley.URL + "edit.php";
    private static  final String TAG = EditProfileActivity.class.getSimpleName();
    private static final String TAG_SUCESS = "success";
    public static final String TAG_NAMA_NEW     = "name";
    public static final String TAG_ALAMAT_NEW   = "alamat";
    public static final String TAG_NOHP_NEW   = "nohp";
    public static final String TAG_NAMA     = "nama";
    public static final String TAG_EMAIL  = "email";
    public static final String TAG_ALAMAT   = "alamat";
    public static final String TAG_NOHP   = "nohp";
    public static final String TAG_JSON_ARRAY   = "result";
    private String TAG_MESSAGE = "message";
    String tag_json_object="json_obj_req";

    int success;
    Preferences preferences;
    ConnectivityManager connectivityManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        preferences = new Preferences(this);
        nama_e= findViewById(R.id.namalengkapedittextinput);
        alamat_e= findViewById(R.id.alamatlengkapinputedittext);
        nohp_e= findViewById(R.id.nohpinputedittext);
        edit = findViewById(R.id.save);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updateMitra();
            }
        });

        getMitra();
    }


    private void loadData(String json){
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(TAG_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String nama = jo.getString(TAG_NAMA);
                String alamat = jo.getString(TAG_ALAMAT);
                String nohp = jo.getString(TAG_NOHP);

                HashMap<String,String> mitra = new HashMap<>();

                nama_e.setText(mitra.put(TAG_NAMA,nama));
                alamat_e.setText(mitra.put(TAG_ALAMAT,alamat));
                nohp_e.setText(mitra.put(TAG_NOHP,nohp));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private void getMitra() {
        class GetMitra extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(EditProfileActivity.this, "Fetching...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                loadData(s);
                showMitra(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(url_edit2,preferences.getSPEmail());
                Log.d("TAG email", preferences.getSPEmail());
                return s;
            }
        }
        GetMitra gm = new GetMitra();
        gm.execute();
    }

    private void showMitra(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);;
            String nama = c.getString(TAG_NAMA);
            String alamat = c.getString(TAG_ALAMAT);
            String nohp = c.getString(TAG_NOHP);
            nama_e.setText(nama);
            alamat_e.setText(alamat);
            nohp_e.setText(nohp);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void updateMitra() {
        final String name = nama_e.getText().toString().trim();
        final String alamat = alamat_e.getText().toString().trim();
        final String nohp = nohp_e.getText().toString().trim();
        class UpdateMitra extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(EditProfileActivity.this, "Updating...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(EditProfileActivity.this, s, Toast.LENGTH_LONG).show();

            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String, String> hashMap = new HashMap<>();
                Log.d("TAG email upadate", preferences.getSPEmail());
                hashMap.put(TAG_EMAIL, preferences.getSPEmail() );
                hashMap.put(TAG_NAMA_NEW, name);
                hashMap.put(TAG_ALAMAT_NEW,alamat);
                hashMap.put(TAG_NOHP_NEW,nohp);
                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest(url_update, hashMap);

                return s;
            }
        }

        UpdateMitra um = new UpdateMitra();
        um.execute();
    }
    public void onClick(View v) {
        updateMitra();
    }


 /*   public void editprofile1(){
        String ambil= preferences.getSPEmail();

        if (connectivityManager.getActiveNetworkInfo()!=null
                && connectivityManager.getActiveNetworkInfo().isAvailable()
                && connectivityManager.getActiveNetworkInfo().isConnected()){
            editprofile(ambil);
        }else {
            Toast.makeText(getApplicationContext(),"No internet", Toast.LENGTH_LONG).show();
        }
    }

    public void editprofile(final String ambil){
        StringRequest strReq = new StringRequest(Request.Method.POST, url_edit, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response: " + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCESS);

                    // Cek error node pada json
                    if (success == 1) {
                        Log.d("get edit data", jObj.toString());
                        String nama_e2    = jObj.getString(TAG_NAMA);
                        String alamat_e2 = jObj.getString(TAG_ALAMAT);
                        String nohp_e2  = jObj.getString(TAG_NOHP);


                    } else {
                        Toast.makeText(EditProfileActivity.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(EditProfileActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", ambil);

                return params;
            }

        };

        Controller_Volley.getInstance().addToRequestQueue(strReq, tag_json_object);

    }*/
}

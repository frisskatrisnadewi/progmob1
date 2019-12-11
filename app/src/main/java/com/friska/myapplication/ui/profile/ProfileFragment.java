package com.friska.myapplication.ui.profile;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
//import android.support.design.widget.TextInputEditText;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
//import android.support.annotation.Nullable;
//import android.support.annotation.NonNull;
//import android.support.v4.app.Fragment;
//import android.arch.lifecycle.Observer;
//import android.arch.lifecycle.ViewModelProviders;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.friska.myapplication.DressListActivity;
import com.friska.myapplication.EditProfileActivity;
import com.friska.myapplication.LoginActivity;
import com.friska.myapplication.ModelBiodata;
import com.friska.myapplication.Preferences;
import com.friska.myapplication.R;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import androidx.fragment.app.Fragment;

//import org.jetbrains.annotations.Nullable;

public class ProfileFragment extends Fragment {

    private ProfileViewModel notificationsViewModel;
    Button editprofile,logout;
    RadioGroup gender_p;
    RadioButton laki, perempuan;
    EditText nama_p, alamat_p;
    TextView  nama_profile;
    private String action_flag="add";
    private String refreshFlag="0";
    private static final String TAG="ProfilActivity";
    private ProgressDialog pDialog;
    private ModelBiodata modelBiodata;
    String id,email;
    SharedPreferences sharedPreferences;
    Preferences preferences;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
      /*  notificationsViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);*/
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        editprofile = (Button) root.findViewById(R.id.edit_profile);
        nama_p = (EditText) root.findViewById(R.id.namalengkapedittextinput);
        alamat_p = (EditText) root.findViewById(R.id.alamatlengkapinputedittext);
        gender_p = (RadioGroup) root.findViewById(R.id.gender);
        logout = (Button) root.findViewById(R.id.logout);
        nama_profile = (TextView) root.findViewById(R.id.name_profile);
        preferences = new Preferences(getContext());
        nama_profile.setText(preferences.getSPEmail());


       // sharedPreferences =getSharedPreferences(LoginActivity.my_shared_preferences, Context.MODE_PRIVATE);

        gotoedit();
        logout();
//        laki= (RadioButton) getView().findViewById(R.id.radiolaki);
   //     perempuan = (RadioButton)getView().findViewById(R.id.radioperempuan);
/*        final TextView textView = root.findViewById(R.id.text_profile);*/
/*        notificationsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

        modelBiodata = new ModelBiodata();

        //initEvent();
       /* Intent intent = View.getIntent();
        if (intent.hasExtra("biodata")) {
            modelBiodata = (ModelBiodata) intent.getSerializableExtra("biodata");
            Log.d(TAG, "Barang : " + modelBiodata.toString());
            setData(modelBiodata);
            action_flag = "edit";

        }else{
            modelBiodata = new ModelBiodata();
        }*/


       /* save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savedbutton();
            }
        });*/


        return root;
    }

    private void gotoedit(){
        editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLogin = new Intent(requireContext(), EditProfileActivity.class);
                startActivity(intentLogin);
            }
        });
    }

    public void logout(){
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferences.saveSPBoolean(preferences.SP_SUDAH_LOGIN, false);
                startActivity(new Intent(requireActivity(), LoginActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                requireActivity().finish();

            }
        });
    }

    private void setData(ModelBiodata modelBiodata) {
                nama_p.setText(modelBiodata.getNama());
                alamat_p.setText(modelBiodata.getAlamat());

    }


  /*  public void savedbutton(){
        refreshFlag="1";
        final String nama_p = nama_p.getText().toString();
        final String ala = editTextKode.getText().toString();
        final String harga = editTextHarga.getText().toString();


        String url = AppConfig.IP_SERVER+"/barang/savedata.php";

        pDialog.setMessage("Save Data Barang...");

        showDialog();



        StringRequest postRequest = new StringRequest(Request.Method.POST, url,

                new Response.Listener<String>() {

                    @Override

                    public void onResponse(String response) {

                        hideDialog();

                        Log.d("BarangActivity", "response :" + response);

                        // Toast.makeText(getBaseContext(),"response: "+response, Toast.LENGTH_SHORT).show();

                        processResponse("Save Data",response);

                        finish();



                    }

                },

                new Response.ErrorListener() {

                    @Override

                    public void onErrorResponse(VolleyError error) {

                        hideDialog();

                        error.printStackTrace();

                    }

                }

        ) {

            @Override

            protected Map<String, String> getParams()

            {

                Map<String, String> params = new HashMap<>();

                // the POST parameters:



                params.put("nama",nama);

                params.put("kode",kode);

                params.put("harga",harga);

                if (action_flag.equals("add")){

                    params.put("id","0");

                }else{

                    params.put("id",modelBiodata.getId());

                }

                return params;

            }

        };

        Volley.newRequestQueue(this).add(postRequest);

    }

    private void processResponse(String paction, String response){

        try {

            JSONObject jsonObj = new JSONObject(response);

            String errormsg = jsonObj.getString("errormsg");

            Toast.makeText(getContext(),paction+" "+errormsg,Toast.LENGTH_SHORT).show();

        } catch (JSONException e) {
            Log.d("BarangActivity", "errorJSON");
        }


    }*/


}